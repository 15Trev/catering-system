package com.techelevator.readerandwriter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import com.techelevator.inventory.Item;
import com.techelevator.money.CashRegister; 

public class Log {
	
	private static List<String[]> listToPrint;
	private static List<String> finalTotalSalesReport = new LinkedList<String>();

	
	//static SalesReport salesReport = new SalesReport();

	private static List<String> tempPurchaseList = new LinkedList<String>();


	public Log() {
		Log.listToPrint = new LinkedList();
	}
	
	//Add each individual purchase to a list to print at end of transaction (called by Catering System complete purchase)
	public static void logPurchaseToPrint(int quantity, Item itemID) throws IOException{
		int c = 0;
		boolean hasItem = false;
		for(String[] i: listToPrint) {
			if(i[2].equals(itemID.getName())) {
				String[] duplicateItemInfoArray = {String.valueOf(quantity + Integer.parseInt(i[0])), 
						itemID.getType(), 
						itemID.getName(), 
						String.valueOf(itemID.getPrice()), 
						String.valueOf((itemID.getPrice() * (quantity + Integer.parseInt(i[0]))))};
				listToPrint.set(listToPrint.indexOf(i), duplicateItemInfoArray);
				c++;
				hasItem = true;
			}
		} 
		if(!hasItem) {
			String[] newItemInfoArray = {String.valueOf(quantity), 
					itemID.getType(), 
					itemID.getName(), 
					String.valueOf(itemID.getPrice()), 
					String.valueOf((itemID.getPrice() * quantity))};
			listToPrint.add(newItemInfoArray);
		}
	}
	

	//Get the list of each purchase that will be printed to the screen - list gets generated one-by-one from logPurchaseToPrint
	public static List<String[]> getListToPrint() throws IOException {
		//salesReport.setUpReoprtAndPrint(listToPrint);
		return listToPrint;
	}

	
	//Reset the purchase log after each customer
	public static void resetPurchaseLog() {
		List<String[]> blankList = new LinkedList();
		listToPrint = blankList;

	}

	

	//Creates a log of each individual transaction in the Log.txt file 
	//(called by one of the below three methods depending on type of transaction)
	public void createFileLog(String logInfo) throws IOException {
		File transactionLog = new File("Log.txt");
		if(!(transactionLog.exists())) {
			transactionLog.createNewFile();
		} 
		Date date = new Date();
		try(FileWriter fileWriter = new FileWriter(transactionLog, true);
				BufferedWriter bufferedWriter = new BufferedWriter(fileWriter)) {
				bufferedWriter.write(DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT).format(date) + " " 
										+ logInfo + "\n");
		}
		
	}
	
	//initiated in Catering System to add a purchase to log file
	public void addPurchaseToFileLog(String idString, int quantity, Item item) throws IOException {
		createFileLog(quantity + " " + item.getName() + " " + idString + " " 
					+ (quantity * item.getPrice()) + " $" + String.format("%.2f", CashRegister.balance));
		
	}
	
	//initiated in Catering System to add an add money transaction to log file
	public void addAddMoneyToFileLog(int amountToAdd) throws IOException {
		createFileLog("ADD MONEY: $" + amountToAdd + ".00  $" + String.format("%.2f", CashRegister.balance));
		
	}
	
	//initiated in Catering System to add a giving change transaction to log file
	public void addGiveChangeToFileLog() throws IOException {
		createFileLog("GIVE CHANGE: $" + String.format("%.2f", CashRegister.balance) + " $0.00");
		
	}
	
	
	
	

//	/*
//	 * Attempt at bonus - tracking total sales
//	 * We are getting repeated lines
//	 */
//	
//	private static double totalSales = 0.0;
//	
//	
//	//Reads old lines and creates file if needed
//	public static List<String> readTotalSalesReport() throws IOException {
//		File transactionLog = new File("TotalSales.rpt");
//		List<String> oldLines = new LinkedList<String>();
//		if(!(transactionLog.exists())) {
//			transactionLog.createNewFile();
//		}
//		try(Scanner fileReader = new Scanner(transactionLog)) {
//			while(fileReader.hasNextLine()) {
//				oldLines.add(fileReader.nextLine());
//			}
//		}
//		return oldLines;
//	}
//	
//	//Create new and updated lines, adds all lines to report (PROBLEM: this is duplicating entries)
//	//Called in Catering System in completePurchase
//	public static void createNewLinesForSalesReport(String name, int quantity, double totalPrice) throws IOException {
//		List<String> newLines = new LinkedList<String>();
//		for(String line: readTotalSalesReport()) {
//			newLines.add(line);
//		}
//		if(newLines.size() > 0) {
//			newLines.remove(newLines.size() - 1);
//		}
//		boolean doesExist = false;
//		
//		for(int i = 0; i < newLines.size(); i++) {
//			
//			if (newLines.get(i).contains(name)) {
//				String[] splitLines = newLines.get(i).split("\\|");
//				int splitLinesQuantity = Integer.parseInt(splitLines[1]);
//				double splitLinesPrice = Double.parseDouble(splitLines[2]);
//				splitLinesQuantity += quantity;
//				splitLinesPrice += totalPrice;
//				splitLines[1] = String.valueOf(splitLinesQuantity);
//				splitLines[2] = String.valueOf(splitLinesPrice);
//				newLines.set(i, splitLines[0] + "|" + splitLines [1] + "|" + splitLines[2]);
//				totalSales += splitLinesPrice;
//				doesExist = true;
//				
//			}
//		}
//		if(!doesExist) {
//			newLines.add(name + "|" + quantity + "|" + totalPrice);
//		}
//		//PROBLEM: This is adding every line from the original file every single time a new item is added to the cart
//		for(String line: newLines) {
//			finalTotalSalesReport.add(line);
//		}
//	}
//	
//	
//	//Add up the final sales amount by splitting the individual log lines
//	public static void getFinalTotalSalesAmount() {
//		for(String line : finalTotalSalesReport) {
//			String[] splitLine = line.split("\\|");
//			double doubleToAdd = Double.parseDouble(splitLine[2]);
//			totalSales += doubleToAdd;
//		}
//	}
//	
//	//Write the final total report to the file
//	public static void salesReportWriter() throws IOException {
//		getFinalTotalSalesAmount();
//		File transactionLog = new File("TotalSales.rpt");
//		try(PrintWriter printWriter = new PrintWriter(transactionLog);
//		BufferedWriter bufferedWriter = new BufferedWriter(printWriter)) {
//			for(String line: finalTotalSalesReport) {
//				bufferedWriter.write(line + System.getProperty("line.separator"));
//			}
//			bufferedWriter.write("**Total Sales** $" + totalSales);
//		}
//		finalTotalSalesReport = Collections.emptyList();
//	}

	/*
	 * Attempt at bonus - tracking total sales
	 * We are getting repeated lines
	 */
//	
//	private static double totalSales = 0.0;
//	
//	
//	//Reads old lines and creates file if needed
//	public static List<String> readTotalSalesReport() throws IOException {
//		File transactionLog = new File("TotalSales.rpt");
//		List<String> oldLines = new LinkedList<String>();
//		if(!(transactionLog.exists())) {
//			transactionLog.createNewFile();
//		}
//		try(Scanner fileReader = new Scanner(transactionLog)) {
//			while(fileReader.hasNextLine()) {
//				oldLines.add(fileReader.nextLine());
//			}
//		}
//		return oldLines;
//	}
//	
//	//Create new and updated lines, adds all lines to report (PROBLEM: this is duplicating entries)
//	//Called in Catering System in completePurchase
//	public static void createNewLinesForSalesReport(String name, int quantity, double totalPrice) throws IOException {
//		List<String> newLines = new LinkedList<String>();
//		for(String line: readTotalSalesReport()) {
//			newLines.add(line);
//		}
//		if(newLines.size() > 0) {
//			newLines.remove(newLines.size() - 1);
//		}
//		boolean doesExist = false;
//		
//		
//		for(int i = 0; i < newLines.size(); i++) {
//			
//			if (newLines.get(i).contains(name)) {
//				String[] splitLines = newLines.get(i).split("\\|");
//				int splitLinesQuantity = Integer.parseInt(splitLines[1]);
//				double splitLinesPrice = Double.parseDouble(splitLines[2]);
//				splitLinesQuantity += quantity;
//				splitLinesPrice += totalPrice;
//				splitLines[1] = String.valueOf(splitLinesQuantity);
//				splitLines[2] = String.valueOf(splitLinesPrice);
//				//newLines.set(i, splitLines[0] + "|" + splitLines [1] + "|" + splitLines[2]);
//				tempPurchaseList.add(splitLines[0] + "|" + splitLines [1] + "|" + splitLines[2]);
//				//totalSales += splitLinesPrice;
//				doesExist = true;
//				
//			} 
//		}
//		
//		if(!doesExist) {
//			tempPurchaseList.add(name + "|" + quantity + "|" + totalPrice);
//		}
//		
//		
//		for(String line : finalTotalSalesReport) {
//			if(line.contains(name)) {
//				finalTotalSalesReport.remove(line);
//			}
//		}
//		
//		//This line re-adds every line since the program began
//		for(String line: newLines) {
//			finalTotalSalesReport.add(line);
//		}
//		
//		
//		
//	}
//	
//	
//	//Add up the final sales amount by splitting the individual log lines
//	public static void getFinalTotalSalesAmount() {
//		
//		for(String line : tempPurchaseList) {
//			finalTotalSalesReport.add(line);
//		}
//		
//		//tempPurchaseList= Collections.emptyList();
//		
//		for(String line : finalTotalSalesReport) {
//			String[] splitLine = line.split("\\|");
//			double doubleToAdd = Double.parseDouble(splitLine[2]);
//			totalSales += doubleToAdd;
//		}
//	}
//	
//	//Write the final total report to the file
//	public static void salesReportWriter() throws IOException {
//		getFinalTotalSalesAmount();
//		File transactionLog = new File("TotalSales.rpt");
//		try(PrintWriter printWriter = new PrintWriter(transactionLog);
//		BufferedWriter bufferedWriter = new BufferedWriter(printWriter)) {
//			for(String line: finalTotalSalesReport) {
//				bufferedWriter.write(line + System.getProperty("line.separator"));
//			}
//			bufferedWriter.write("**Total Sales** $" + totalSales);
//		}
//		finalTotalSalesReport = Collections.emptyList();
//	}


	
	
	
}
