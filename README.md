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

## Menu
![image](https://user-images.githubusercontent.com/47723396/206981927-1eab7a34-1136-431d-81eb-222d577c1751.png)     
After choosing to browse the vending machine (and thus start a new transaction), the available funds are displayed over the transaction menu.  Adding money updates the funds available.

## Inventory Display
Inventory display includes item cost and current stock.  The display is formatted such that product categories display evenly for easier visability. 
   
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
      
Selecting a product prompts a dispensing loading bar and purchased item to display, updates current stock of item, and updates current funds.  Item stock persists between transactions.
   
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
        
Finishing the transaction promps a list of items purchased and change dispensed to display along with a thank you message   
     
![image](https://user-images.githubusercontent.com/47723396/206982934-c560d2fe-a749-4bce-8c1f-04a32b6cf8aa.png)      
     
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

     
## Error Handling
Program has custom exceptions for insufficient funds, invalid funds, invalid selection, and insufficient stock. 
   
![Untitled design (1)](https://user-images.githubusercontent.com/47723396/206980855-751f2c53-30fd-46e7-bd2a-89e62a9a8f90.png)      

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
        
// if exception, display error message for user and write exception to log file
} catch (InvalidOptionException ex) {     // ... if user does not enter a valid product code
} catch (InsufficientStockException ex) { // ... if user tries to purchase an item that's out of stock
} catch (InsufficientFundsException ex) { // ... if user does not have enough money to purchase item
} catch (Exception ex) {                  // generic exception
        Logger.createLogEntry(ex.getMessage());
}
```
      
 
    
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

