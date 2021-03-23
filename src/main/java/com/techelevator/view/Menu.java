package com.techelevator.view;

import java.io.FileNotFoundException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import com.techelevator.CateringSystem;
import com.techelevator.CateringSystemCLI;
import com.techelevator.inventory.Item;
import com.techelevator.money.CashRegister;
import com.techelevator.readerandwriter.InventoryReader;

/*
 * This is the only class that should have any usage of System.out or System.in
 */
public class Menu {
	
	
	private static final Scanner in = new Scanner(System.in);

	//Print original menu 
	public void printMainMenu() {
		System.out.println("(1) Display Catering Items");
		System.out.println("(2) Order");
		System.out.println("(3) Quit");
	}
	
	//Print secondary menu
	public void purchaseProcessMenu() {
		System.out.println("(1) Add Money");
		System.out.println("(2) Select Products");
		System.out.println("(3) Complete Transaction");
		System.out.print("Current account balance: $");
		System.out.printf("%.2f", CashRegister.balance);
		System.out.println();
	}
	
	//Print request for user to enter key of item to purchase
	public void askForItem() {
		System.out.println("Which item would you like to purchase?");
	}
	
	//Print request for user to enter # of items to purchase
	public void askForQuantity() {
		System.out.println("How many would you like to purchase?");
	}
	
	//Print request for how much money user would like to add to their balance
	public void addMoneyOutput() {
		System.out.println("How much would you like to add? (Please select whole dollar incriments): ");
	}

	//Prints change amount given in various increments (when transaction is completed)
	public void returnedChangeMessage(int[] arrayOfChange) {
		System.out.println("\nThank you for your order. Returned change: " + arrayOfChange[0] + "-Twenties, " + 
				arrayOfChange[1] + "-Tens, " +
				arrayOfChange[2] + "-Fives, " +
				arrayOfChange[3] + "-Ones, " +
				arrayOfChange[4] + "-Quarters, " +
				arrayOfChange[5] + "-Dimes, " +
				arrayOfChange[6] + "-Nickles. ");
		System.out.println();
	}
	
	//Print the log for a single, completed transaction
	public void printLog(List<String[]> listToPrint) {
		System.out.println("Purchase details: ");
		double total = 0;
		for(String[] s: listToPrint) {
			System.out.printf("%-7s %-10s %-20s $%-7.2f $%-7.2f %n", s[0], s[1], s[2], Double.parseDouble(s[3]), Double.parseDouble(s[4]));
			total += Double.parseDouble(s[4]);
		}
		System.out.printf("Total: $%.2f", total);
	}
	
	//Error message for user selecting an item key that does not exist in the map
	public void itemDoesNotExistMessage() {
		System.out.println("Item does not exist.");
		System.out.println();
	}
	
	//Error message for user selecting an item that is out of stock
	public void outOfStockMessage() {
		System.out.println("Item out of stock.");
		System.out.println();
	}
	
	//Error message for user trying to buy more than the remaining stock
	public void notEnoughQuantityMessage() {
		System.out.println("Not enough stock remaining to complete transaction.");
		System.out.println();
	}
	
	//Error message for user spending more money than is available
	public void notEnoughFundsMessage() {
		System.out.println("Not enough funds to complete transaction.");
		System.out.println();
	}
	
	//Message for successfully completed purchase
	public void transactionSuccessfulMessage() {
		System.out.println("Item successfully added to cart.");
		System.out.println();
	}
	
	//Message for catch statement
	public void inventoryErrorMessage() {
		System.out.println("Error, inventory file not found");
	}
	
	//Message for catch statement
	public void logErrorMessage() {
		System.out.println("Error, log file not found");
	}
	
	//Used when getting menu choices
	public int getInt() {
		String userInput = in.nextLine();
		int userInputAsInt = 0;
		try {
		    userInputAsInt = Integer.parseInt(userInput);
		}catch (NumberFormatException e) {
		    System.out.println("Invalid entry, please try again.");
		    return userInputAsInt;
		}
		if (userInputAsInt < 1 || userInputAsInt > 3) {
			System.out.println("Invalid entry, please try again.");
		}
		return userInputAsInt;
	}
	
	//Used when getting other integer inputs from user (amount of items to purchase)
	public int getQuantity() {
		String userInput = in.nextLine();
		int userInputAsInt = 0;
		try {
			userInputAsInt = Integer.parseInt(userInput);
		} catch (NumberFormatException e) {
			System.out.println("Invalid entry, please try again.");
		}
		return userInputAsInt;
	}
	
	//Gets String from user	
	public String getString() {
		String userInput = in.nextLine();
		return userInput;
	}
	
	//Gets double from user (when adding money)
	public Double getDouble() {
		String userInput = in.nextLine();
		double userInputAsDouble = 0;
		try{
		    userInputAsDouble = Double.parseDouble(userInput);
		}catch (NumberFormatException e) {
		    System.out.println("Invalid entry, please try again.");
		    return userInputAsDouble;
		}
		if (userInputAsDouble <= 0) {
			System.out.println("Invalid entry, please try again.");
		}
		return userInputAsDouble;
	}
	
	//Prints full list of items available
	public void printItemMenu() {
		Map<String, Item> itemMenu = CateringSystemCLI.itemMap;
		for(Entry<String, Item> i : itemMenu.entrySet()) {
			System.out.print(i.getKey() + " " + i.getValue().getName() 
					+ " -- $" + String.format("%.2f",i.getValue().getPrice()));
			if(i.getValue().getStock() > 0) {
				System.out.println(" x" + i.getValue().getStock());
			} else {
				System.out.println(" SOLD OUT");
			}
		}
	}	
	
	public void printGoodbyeMessage() {
		System.out.print("Thank you! Goodbye.");
	}
}
