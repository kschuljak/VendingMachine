# Vendo-Matic 800
Java Console Vending Machine App

- Intro and main menu   
  ![image](https://user-images.githubusercontent.com/47723396/203184846-86a43f6e-2167-4c21-974d-f77188ca5fbb.png)
- Current funds is displayed over transaction menu
  ```java
  BigDecimal remainingFunds = transaction.getRemainingFunds();
  String funds = remainingFunds.toString();
  displayRemainingFunds(funds);
  ```
  ![image](https://user-images.githubusercontent.com/47723396/203184956-10a2dcb5-f676-406d-bd1d-88ca979cf31c.png)
- Inventory display includes item cost and current stock   
  ![image](https://user-images.githubusercontent.com/47723396/203185032-104382dd-7593-4e8b-941b-10771a33a8ff.png)
- Adding money updates current funds   
  ![image](https://user-images.githubusercontent.com/47723396/203185135-fd158f03-27f2-4fd3-aef6-5f28e27df11f.png)
- Selecting a product prompts a dispensing loading bar and purchased item, and updates current funds   
  ![image](https://user-images.githubusercontent.com/47723396/203185389-3059fbb6-fe1f-4eaf-b905-9375759058d0.png)
- If selected product costs more than available funds, error is displayed and transaction does not complete   
  ![image](https://user-images.githubusercontent.com/47723396/203185477-40d2f7b6-c386-4f82-a187-40febce78f99.png)
- Displayed inventory reflects current stock   
  ![image](https://user-images.githubusercontent.com/47723396/203185706-be67d70e-0979-4394-87f8-782ae4baff47.png)
- If an item is out of stock, stock in display is replaced by 'SOLD OUT', and trying to purchase an out of stock item will display an error   
  ![image](https://user-images.githubusercontent.com/47723396/203185858-179e5b7c-8fa0-4004-80be-dc70ebebf6e3.png)
- Finishing the transaction promps a list of items purchased and change dispensed to display along with a thank you message   
  ![image](https://user-images.githubusercontent.com/47723396/203186349-0109b11c-5a1e-4cb7-837d-e1734dcd7ce6.png)
- Current stock persists between transactions   
  ![image](https://user-images.githubusercontent.com/47723396/203186555-660d8356-2781-4b76-999a-db4aee0653b7.png)
- A hidden menu option displays a current sales report   
  ![image](https://user-images.githubusercontent.com/47723396/203186694-95e7ff9e-de3b-42f4-bb7e-8c718fa7dc53.png)
- Another hidden menu option promps the vending machine to dispense all remaining products for free   
  ![image](https://user-images.githubusercontent.com/47723396/203186857-3202450a-95aa-4737-b54b-289d3bb3281d.png)


### Initial planning 
![image](https://user-images.githubusercontent.com/47723396/203187111-b9a87bf0-9eac-47da-9064-8646a941114c.png)
(*diagram by Daniel Duque*)

