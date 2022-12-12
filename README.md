# Vendo-Matic 800
Java Console Vending Machine App

# Folder Structure

- ***application*** - holds the functionality for navigating menu options   
- ***exceptions*** - holds custom exceptions

- ***io*** - holds user input and output for both Java console and files
  - ***logs*** - holds functionality for creating timestamped logs   
  - ***report*** - holds functionality for reading and generating a persistent sales report CSV file   
  - ***ui*** - user input and output seperated into designated classes   
- ***models*** - contains a product model used for creating product objects from CSV file information
- ***services*** - contains functionality for generating inventntory list and completing user transactions
- ***validators*** - contain functionality for checking the validity of user input
- ***view*** - holds functionality for manipulating Java console display

## Splash Screen & Main Menu   
  ![image](https://user-images.githubusercontent.com/47723396/203184846-86a43f6e-2167-4c21-974d-f77188ca5fbb.png)
     
## Transaction Menu
- Current funds are displayed over transaction menu   
  ![image](https://user-images.githubusercontent.com/47723396/203184956-10a2dcb5-f676-406d-bd1d-88ca979cf31c.png)
  ```java
  BigDecimal remainingFunds = transaction.getRemainingFunds();
  String funds = remainingFunds.toString();
  displayRemainingFunds(funds);
  ```
  - Adding money updates current funds   
  ![image](https://user-images.githubusercontent.com/47723396/203185135-fd158f03-27f2-4fd3-aef6-5f28e27df11f.png)      
  ```java
  public void addMoney(BigDecimal amount){
     if (isMoneyValid(amount)) {
        remainingFunds = remainingFunds.add(amount);
        TransactionLog.createLogEntry("FEED MONEY: " + amount + " " + remainingFunds);
     }
  }
  ```
- Inventory display includes item cost and current stock   
![image](https://user-images.githubusercontent.com/47723396/203185032-104382dd-7593-4e8b-941b-10771a33a8ff.png) 
  - Display is formatted such that product categories display evenly for easier visibility   
  ```java
  for (Product product : productList) {
            String id = product.getSlotID();
            String name = product.getName() + "*";
            String nameNoSpaces = name.replace(' ', '*');
            String formattedName = String.format("%-20s", nameNoSpaces)
                                         .replace(' ', '-')
                                         .replace('*', ' ');
            BigDecimal price = product.getPrice();
            int quantity = product.getQuantity();
            if (quantity == 0) UserOutput.displayProductSoldOut(id, formattedName, price);
            else UserOutput.displayProductWithStock(id, formattedName, price, quantity);
   }
   ```     
- Selecting a product prompts a dispensing loading bar and purchased item to display, and updates current funds   
```java
if (selection == null) throw new Exception("Selection is null");
else if (!Validator.isInStock(selection)) {
        throw new InsufficientStockException("This item is out of stock",  selection.getQuantity());
} else if (!Validator.hasEnoughMoney(selection, remainingFunds)) {
        throw new InsufficientFundsException("Insufficient funds", getRemainingFunds(), selection.getPrice());
} else {  // selected item found in menu, item is in stock, and user has enough money to purchase item
        Inventory.updateInventory(selection, ITEM_QUANTITY_PER_SELECTION);  // update inventory
        updatePurchases(selection);  // add item to list of user purchases for this transaction
        spendMoney(selection);  // subtract item cost from availabe funds
        SalesReport.update(selection);  // add purchase to sales report in memory

        String type = selection.getType();
        String name = selection.getName();
        String id = selection.getSlotID();
        BigDecimal price = selection.getPrice();
        // use values from getter functions to create an entry in the transaction log
        TransactionLog.createLogEntry(name + " " + id + " " + price + " " + remainingFunds);

        // display loading bar, item purchased, and a purchase success message to the user
        LoadingBar.displayLoadingBar();
        userOutput.displayPurchaseSuccess(name);
        userOutput.displayItemTypeReturnMessage(type);
}
```
  ![image](https://user-images.githubusercontent.com/47723396/203185389-3059fbb6-fe1f-4eaf-b905-9375759058d0.png)
  
- Finishing the transaction promps a list of items purchased and change dispensed to display along with a thank you message   
![image](https://user-images.githubusercontent.com/47723396/203186349-0109b11c-5a1e-4cb7-837d-e1734dcd7ce6.png)   
  ```java
  public Map<Product, Integer> finishTransaction() {
        UserOutput.displaySummaryIntro();
        getItemSummary(productsPurchased);
        returnChange(remainingFunds);
        // ...
  }
  
  public void getItemSummary(Map<Product, Integer> productsPurchased) {
        for (Map.Entry<Product, Integer> product: productsPurchased.entrySet()) {
              String productName = product.getKey().getName();
              String amountPurchased = product.getValue().toString();
              UserOutput.displayItemSummary(productName, amountPurchased);
        }
  }

  public void returnChange(BigDecimal remainingFunds) {
        int remaining = remainingFunds.multiply(new BigDecimal("100")).intValue();
        // set constants QUARTER, DIME, NICKEL
        int quartersReturned = remaining / QUARTER;
        int lessQuarters = remaining % QUARTER;
        int dimesReturned = lessQuarters / DIME;
        int lessDimes = lessQuarters % DIME;
        int nicklesReturned = lessDimes / NICKEL;
        UserOutput.displayChange(quartersReturned, dimesReturned, nicklesReturned);
  }
  ```

     
- Current stock persists between transactions   
  ![image](https://user-images.githubusercontent.com/47723396/203186555-660d8356-2781-4b76-999a-db4aee0653b7.png)
     
## Error Handling
Program has custom exceptions for insufficient funds, invalid funds, invalid selection, and insufficient stock. 
```java
// if user enters $1, $5, $10, or $20 add money, otherwise throw Invalid Funds Exception
try {
        transaction.addMoney(userFunds);
} catch (InvalidFundsException ex) {
        // display error message for user and write exception to log file
}
```
```java
try {
        userOutput.displayEnterProduct();
        String productSelection = userInput.getSelection();
        transaction.purchaseItem(productSelection);
        
} catch (InvalidOptionException ex) {       // if user does not enter a valid product code
        // display error message for user and write exception to log file
} catch (InsufficientStockException ex) {   // if user tries to purchase an item that's out of stock
        // display error message for user and write exception to log file
} catch (InsufficientFundsException ex) {   // if user does not have enough money to purchase item
        // display error message for user and write exception to log file
} catch (Exception ex) {                    // generic exception
        Logger.createLogEntry(ex.getMessage());
}
```

     
- If selected product costs more than available funds, an error is displayed and transaction does not complete    
- Displayed inventory reflects current stock  
  - If an item is out of stock, stock quantity in display is replaced by 'SOLD OUT', and trying to purchase an out of stock item will display an error   
  ![image](https://user-images.githubusercontent.com/47723396/203185858-179e5b7c-8fa0-4004-80be-dc70ebebf6e3.png)
 
    
## SALES REPORT
- A hidden menu option displays a current sales report
- An up-to-date sales report is written to a CSV file whenever the hidden menu option is called or the user selects the option to exit the program.
- When the program is next run, the information from the CSV file is read and stored in memory until a new sales report is printed to overwrite the old file.   
  ![image](https://user-images.githubusercontent.com/47723396/203186694-95e7ff9e-de3b-42f4-bb7e-8c718fa7dc53.png)
     
- Another hidden menu option promps the vending machine to dispense all remaining products for free   
  ```java
  public static void kickTheVendingMachine() {
        for (Product product : productList) product.setQuantity(0);
  }
  ```
  ![image](https://user-images.githubusercontent.com/47723396/203186857-3202450a-95aa-4737-b54b-289d3bb3281d.png)


### Initial planning 
![image](https://user-images.githubusercontent.com/47723396/203187111-b9a87bf0-9eac-47da-9064-8646a941114c.png)
(*diagram by Daniel Duque*)

