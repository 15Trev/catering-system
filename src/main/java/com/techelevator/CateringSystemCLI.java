package com.techelevator;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import com.techelevator.inventory.Item;
import com.techelevator.money.CashRegister;
import com.techelevator.readerandwriter.InventoryReader;
import com.techelevator.readerandwriter.Log;
import com.techelevator.view.Menu;

/*
 * This class should control the workflow of the application, but not do any other work
 * 
 * The menu class should communicate with the user, but do no other work
 * 
 * The work of the Catering System should be in other classes that you build and 
 * call from here. 
 */
public class CateringSystemCLI {

	/*
	 * The menu class is instantiated in the main() method at the bottom of this file.  
	 * It is the only class instantiated in the starter code.  
	 * You will need to instantiate all other classes using the new keyword before you can use them.
	 * 
	 * Remember every class and data structure is a data types and can be passed as arguments to methods or constructors.
	 */
	private Menu menu;
	private CateringSystem cateringSystem;
	private CashRegister cashRegister;
	public static Map<String, Item> itemMap;
	
	public CateringSystemCLI(Menu menu) {
		this.menu = menu;
	}

	/*
	 * Your application starts here
	 */
	public void run() throws IOException {
		InventoryReader inventoryReader = new InventoryReader();
		try {
			itemMap = inventoryReader.getMapWithItems();
		} catch (FileNotFoundException e) {
			menu.inventoryErrorMessage();
		}
		cateringSystem = new CateringSystem(itemMap);
		
		int choice = 0;
		while (true) {
			menu.printMainMenu();
			choice = menu.getInt();
			
			if(choice == 1) {
				menu.printItemMenu();
				
			} else if(choice == 2) {

				while(true) {
					menu.purchaseProcessMenu();

					int purchaseMenuChoice = menu.getInt();
					if(purchaseMenuChoice == 1) {

						menu.addMoneyOutput();
						double amountOfMoneyToAdd = menu.getDouble();
						try {
							cateringSystem.addMoney(amountOfMoneyToAdd);
						} catch (IOException e) {
							menu.logErrorMessage();
						}
					} else if (purchaseMenuChoice == 2) {
						menu.askForItem();
						String itemChoice = menu.getString();
						boolean itemExists = cateringSystem.checkIfItemExists(itemChoice);
						if(!itemExists) {
							menu.itemDoesNotExistMessage();
						} else { 
							menu.askForQuantity();
							int quantity = menu.getQuantity();
							if(!cateringSystem.checkIfStockAvailable(itemChoice)) {
								menu.outOfStockMessage();
							} else if(!cateringSystem.checkIfQuantityAvailable(itemChoice, quantity)) {
								menu.notEnoughQuantityMessage();
							} else {
								if(!cateringSystem.checkIfFundsAvailable(itemChoice, quantity)){
									menu.notEnoughFundsMessage();
								} else {
									try {
										cateringSystem.completePurchase(itemChoice, quantity);
									} catch (IOException e) {
										menu.logErrorMessage();
									}
									menu.transactionSuccessfulMessage();
								}
							}
						}
						
					} else if (purchaseMenuChoice == 3) {
						menu.printLog(cateringSystem.getPurchaseLog());

//						try {
//							Log.getTransactionsForTotalSalesReport();
//							Log.salesReportWriter();
//						} catch (IOException e) {
//							menu.logErrorMessage();
//						}

						Log.resetPurchaseLog();
						int[] changeToPrint = null;
						try {
							changeToPrint = cateringSystem.returnedChangeFrom20sToNickles();
						} catch (IOException e) {
							menu.logErrorMessage();
						}
						menu.returnedChangeMessage(changeToPrint);
						
						break; 
					}	
				}
			} else if(choice == 3) {
				menu.printGoodbyeMessage();
				break;
			}	
		}
	}

	/*
	 * This starts the application, but you shouldn't need to change it.  
	 */
	public static void main(String[] args) throws IOException {
		Menu menu = new Menu();
		CateringSystemCLI cli = new CateringSystemCLI(menu);
		cli.run();	
	}
}
