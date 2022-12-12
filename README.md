# Vendo-Matic 800
Java Console Vending Machine App

# Folder Structure

***application*** - holds the functionality for navigating menu options
***exceptions**** - holds custom exceptions for insufficient funds, insufficient stock, invalid funds, and invalid option selection.

***io***
  ***logs*** - holds functionality for creating timestamped logs
  ***report*** - holds functionality for reading and generating a persistent sales report CSV file
  ***ui*** - user input and output seperated into designated classes
  ProductLoader

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
  if (selection != null
     && isInStock(selection)
     && isItemSelectionValid(itemID)
     && hasEnoughMoney(selection)
  ) {
     Inventory.updateInventory(selection, ITEM_QUANTITY_PER_SELECTION);
     updatePurchases(selection);
     spendMoney(selection);
     SalesReport.update(selection);
     // ... (get name, id, price, remainingFunds)
     TransactionLog.createLogEntry(name + " " + id + " " + price + " " + remainingFunds);
     LoadingBar.displayLoadingBar();
     UserOutput.displayPurchaseSuccess(name);
     UserOutput.displayItemTypeReturnMessage(type);
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
     
- If selected product costs more than available funds, an error is displayed and transaction does not complete    
  ![image](https://user-images.githubusercontent.com/47723396/203185477-40d2f7b6-c386-4f82-a187-40febce78f99.png)
  ```java
  public boolean hasEnoughMoney(Product product) {
     BigDecimal zero = new BigDecimal("0.00");
     BigDecimal itemCost = product.getPrice();
     boolean isValid = false;

     if (itemCost != null) {
        try {
           BigDecimal totalFunds = getRemainingFunds();
           if (totalFunds.compareTo(itemCost) >= 0) isValid = true;
           else {
              UserOutput.displayErrorMessage("Insufficient funds! \nPlease add money to purchase item.");
              throw new InsufficientFundsException("Insufficient funds.", remainingFunds, itemCost);
           }
        } catch (InsufficientFundsException exception) Logger.createLogEntry(exception.getMessage());
     } return isValid;
  }
  ```
- Displayed inventory reflects current stock   
  ![image](https://user-images.githubusercontent.com/47723396/203185706-be67d70e-0979-4394-87f8-782ae4baff47.png)
     
  - If an item is out of stock, stock quantity in display is replaced by 'SOLD OUT', and trying to purchase an out of stock item will display an error   
  ![image](https://user-images.githubusercontent.com/47723396/203185858-179e5b7c-8fa0-4004-80be-dc70ebebf6e3.png)
  ```java
  public boolean isInStock(Product product) {
     boolean isInStock = false;
     int quantity = 0;
     if (product != null) quantity = product.getQuantity();
     try {
        if (quantity > 0) isInStock = true;
        else {
           UserOutput.displayErrorMessage("This item is out of stock! \nPlease select a different item.");
           throw new InsufficientStockException("This product is not available", quantity);
        }
     } catch (InsufficientStockException exception) Logger.createLogEntry(exception.getMessage());
     return isInStock;
  }
  ```
    
## SALES REPORT
- A hidden menu option displays a current sales report
- An up-to-date sales report is written to a .csv file whenever the hidden menu option is called or the user selects the option to exit the program.
- When the program is next run, the information from the .csv file is read and stored in memory until a new sales report is printed to overwrite the old file.   
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

