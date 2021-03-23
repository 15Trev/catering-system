package com.techelevator.inventory;

public class Beverage extends Item{

	
	public Beverage(String name, String price) {
		super(name, price);
	}
	
	@Override
	public String getType() {
		return "Beverage";
	}
}
