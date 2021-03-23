package com.techelevator.inventory;

public class Dessert extends Item{
	

	public Dessert(String name, String price) {
		super(name, price);
	}
	
	@Override
	public String getType() {
		return "Dessert";
	}
	
}
