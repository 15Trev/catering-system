//package com.techelevator.ReaderAndWriter;
//
//import java.io.BufferedWriter;
//import java.io.File;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//import java.io.PrintWriter;
//import java.util.LinkedList;
//import java.util.List;
//import java.util.Scanner;
//
//import com.techelevator.inventory.Item;
//
//public class SalesReport {
//
//	double totalSales = 0.0;
//	
//	//listOfNewPurchases at [2], (int)[0], and (double)[4] will be the printed items
//	public void setUpReoprtAndPrint(List<String[]> listOfNewPurchases) throws IOException {
//		List<String[]> pastItems = new LinkedList<String[]>();
//		List<String[]> newItems = new LinkedList<String[]>();
//		//create list of new items in the same order as mentioned above as strings
//		for(String[] l: listOfNewPurchases) {
//			newItems.add(new String[] {l[2], l[0], l[4]});
//		}
//		
//		File totalSalesFile = new File("TotalSales2.rpt");
//		
//		if(totalSalesFile.exists() && !totalSalesFile.isDirectory()) { 
//		//setup file writer
//			try(PrintWriter printWriter = new PrintWriter(totalSalesFile);
//					BufferedWriter bufferedWriter = new BufferedWriter(printWriter)) {
//				//Write doc title
////				bufferedWriter.write("Total System Sales Report" + System.getProperty("line.separator") + 
////						"----------------------------" + System.getProperty("line.separator"));
//				
//					//read and copy existing lines onto List<String[]> of all items
//					pastItems = readListOfPastSales(totalSalesFile);
//
//					//scan list for duplicate items to update, otherwise add to list
//					List<String[]> finalLines = pastItems;
//					finalLines.addAll(compareUpdateAndAddLines(finalLines, newItems));
//					
//					for(String[] i: finalLines) {
//						totalSales += Double.parseDouble(i[2]);
//						bufferedWriter.write(i[0] + "|" + i[1] + "|" + i[2] + System.getProperty("line.separator"));
//					}
//					
//					bufferedWriter.write("**Total Sales** $" + totalSales);
//			}	
//		} else {
//			totalSalesFile.createNewFile();
//			
//			try(PrintWriter printWriter = new PrintWriter(totalSalesFile);
//						BufferedWriter bufferedWriter = new BufferedWriter(printWriter)) {
//				bufferedWriter.write("Total System Sales Report" + System.getProperty("line.separator") + 
//						"----------------------------" + System.getProperty("line.separator"));
//				//create new list with newItems
//				
//				
//				for(String[] i: newItems) {
//					double newTotal = Double.valueOf(i[2]);
//					totalSales += newTotal;
//					bufferedWriter.write(i[0] + "|" + i[1] + "|" + i[2] + System.getProperty("line.separator"));
//				}
//				
//				bufferedWriter.write("**Total Sales** $" + totalSales);
//			}
//			
//			
//		}
//	}
//
//	public List<String[]> readListOfPastSales(File totalSalesFile) throws FileNotFoundException{
//		List<String[]> oldLines = new LinkedList<String[]>();
//		try(Scanner fileReader = new Scanner(totalSalesFile)) {
//			while(fileReader.hasNextLine()) {
//				String readLine = fileReader.nextLine();
//				if(readLine.contains("\\|")) {
//					String[] oldLineAsArray = readLine.split("\\|");
//					oldLines.add(oldLineAsArray);
//				}
//			}
//		}
//		return oldLines;
//	}
//	
//	private List<String[]> compareUpdateAndAddLines(List<String[]> pastItems, List<String[]> newItems) {
//		List<String[]> returnedLines = new LinkedList<String[]>();
//		while(returnedLines.size() <= pastItems.size() && returnedLines.size() <= newItems.size()) {
//			int i = 0;
//			//check if first index in each string array is equal
//			if(pastItems.contains(newItems.indexOf(i))) {
//				returnedLines.add(breakLinesToAddValues(pastItems.get(i), newItems.get(i)));
//			} else {
//				returnedLines.add(newItems.get(i));
//			}
//		}
//		return returnedLines;
//	}
//	
//	private String[] breakLinesToAddValues(String[] oldVals, String[] newVals) {
//		int oldQ = Integer.parseInt(oldVals[1]);
//		double oldT = Double.parseDouble(oldVals[2]);
//		int newQ = Integer.parseInt(newVals[2]);
//		double newT = Double.parseDouble(oldVals[2]);
//		totalSales += newT;
//		int finalQ = oldQ + newQ;
//		double finalT = oldT + newT;
//		return new String[] {oldVals[0], String.valueOf(finalQ), String.valueOf(finalT)};
//	}
//
//}
