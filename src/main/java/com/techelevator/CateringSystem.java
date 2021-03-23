package com.techelevator;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.techelevator.inventory.Appetizer;
import com.techelevator.inventory.Beverage;
import com.techelevator.inventory.Dessert;
import com.techelevator.inventory.Entree;
import com.techelevator.inventory.Item;
import com.techelevator.money.CashRegister;
import com.techelevator.readerandwriter.InventoryReader;
import com.techelevator.readerandwriter.Log;

public class CateringSystem {

	private Map<String, Item> itemMap;
	//private InventoryReader inventoryReader = new InventoryReader();
	CashRegister cashRegister = new CashRegister();
	Log log = new Log();
	
	
	public CateringSystem(Map<String, Item> itemMap) {
		this.itemMap = itemMap;
	}

	
	public boolean checkIfItemExists(String selection){
		if(itemMap.containsKey(selection)) {
			itemMap.get(selection);
			return true;
		}
		return false;		
	}
	
	public boolean checkIfStockAvailable(String selection){
		if(itemMap.get(selection).getStock() > 0) {
			return true;
		}
		return false;		
	}
	
	public boolean checkIfQuantityAvailable(String selection, int quantity){
		if(itemMap.get(selection).getStock() >= quantity) {
			return true;
		}
		return false;		
	}
	
	public boolean checkIfFundsAvailable(String selection, int quantity){
		if(quantity * itemMap.get(selection).getPrice() <= cashRegister.getBalance()) {
			return true;
		}
		return false;		
	}
	
	//Calls every method needed to log/track the final individual purchase
	public void completePurchase(String selection, int quantity) throws IOException {
		itemMap.get(selection).decreaseStock(quantity);
		cashRegister.subtractMoney(quantity * itemMap.get(selection).getPrice());
		Log.logPurchaseToPrint(quantity, itemMap.get(selection));
		log.addPurchaseToFileLog(selection, quantity, itemMap.get(selection));
		//Log.createNewLinesForSalesReport(itemMap.get(selection).getName(), quantity, (quantity * itemMap.get(selection).getPrice()));
	}
	
	public List<String[]> getPurchaseLog() throws IOException{
		return log.getListToPrint();
	}
	
	public double getFinalBalance() {
		return cashRegister.getBalance();
	}
	
	public boolean addMoney(double amountToAdd) throws IOException {
		if(cashRegister.getBalance() + amountToAdd <= 5000 && amountToAdd % 1 == 0 && amountToAdd > 0) {
			cashRegister.addMoneyToRegister(amountToAdd);
			log.addAddMoneyToFileLog((int)amountToAdd);
			return true;
		} else {
			return false;
		}
	}
	
	public int[] returnedChangeFrom20sToNickles() throws IOException {
		double remainingBalance = cashRegister.getBalance();
		log.addGiveChangeToFileLog();
		cashRegister.resetBalance();
		return cashRegister.makeChange(remainingBalance);
	}
	
	
	
}
