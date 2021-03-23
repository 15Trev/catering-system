package com.techelevator.inventory;

public class Entree extends Item{

	private int stock;

	
	public Entree(String name, String price) {
		super(name, price);
	}
	
	@Override
	public String getType() {
		return "Entree";
	}
}
