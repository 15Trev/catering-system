package com.techelevator.inventory;

public abstract class Item{

	private String name;
	private String price;
	private String type;
	private int stock;
	
	public Item(String name, String price) {
		this.name = name;
		this.price = price;
		this.stock = 50;
	}
	
	
	
	public String getName() {
		return name;
	}
	
	public Double getPrice() {
		return Double.parseDouble(price);
	}
	
	public void decreaseStock(int quantity) {
		stock -= quantity;
	}
	
	public int getStock() {
		return stock;
	}
	
	//Each class with return their type as a String (e.g. Beverage)
	public abstract String getType();
}
