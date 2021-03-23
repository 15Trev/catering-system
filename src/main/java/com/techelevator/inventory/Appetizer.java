package com.techelevator.inventory;

public class Appetizer extends Item{


	public Appetizer(String name, String price) {
		super(name, price);
	}
	
	@Override
	public String getType() {
		return "Appetizer";
	}
}
