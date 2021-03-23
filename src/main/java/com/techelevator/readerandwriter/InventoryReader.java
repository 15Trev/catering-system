package com.techelevator.readerandwriter;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

import com.techelevator.inventory.Appetizer;
import com.techelevator.inventory.Beverage;
import com.techelevator.inventory.Dessert;
import com.techelevator.inventory.Entree;
import com.techelevator.inventory.Item;


public class InventoryReader {

	private File inventoryFile = new File("cateringsystem.csv");
	
	//Gets list of full lines from inventory file
	private List<String> readInventoryFile() throws FileNotFoundException {
		List<String>  inventoryList = new ArrayList<String>();
		try(Scanner fileReader = new Scanner(inventoryFile)) {
			while(fileReader.hasNextLine()) {
				String inventoryLine = fileReader.nextLine();
				inventoryList.add(inventoryLine);
			}
		}
		return inventoryList;
	}
	
	//Creates map with item ID as key and item details as values (in an array of Strings)
	public Map<String, String[]> keySetup() throws FileNotFoundException{
		List<String> inventoryList = readInventoryFile();
		Map<String, String[]> mapOfInventory = new LinkedHashMap<String, String[]>();
		for (String i : inventoryList) {
			String[] allParts = i.split("\\|");
			mapOfInventory.put(allParts[0], new String[] {allParts[3], allParts[1], allParts[2]});
		}
		return mapOfInventory;
	}
	
	
	//(Map to use) Recreates map with item ID as key and Items as values
	public Map<String, Item> getMapWithItems() throws FileNotFoundException{
		Map<String, String[]> setupMap = keySetup();
		Map<String, Item> itemMap = new LinkedHashMap<String, Item>();
		
		for(Entry<String, String[]> i : setupMap.entrySet()) {
			String[] itemInfo = i.getValue();
			String itemKey = i.getKey();
		
			if(itemInfo[0].equals("B")) {
				Item newItem = new Beverage(itemInfo[1], itemInfo[2]);
				itemMap.put(itemKey, newItem);
			} else if(itemInfo[0].equals("A")) {
				Item newItem = new Appetizer(itemInfo[1], itemInfo[2]);
				itemMap.put(itemKey, newItem);
			} else if(itemInfo[0].equals("E")) {
				Item newItem = new Entree(itemInfo[1], itemInfo[2]);
				itemMap.put(itemKey, newItem);
			} else if(itemInfo[0].equals("D")) {
				Item newItem = new Dessert(itemInfo[1], itemInfo[2]);
				itemMap.put(itemKey, newItem);
			}
		}
		return itemMap;
	}
}
