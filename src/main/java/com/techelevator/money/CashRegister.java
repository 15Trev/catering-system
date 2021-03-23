package com.techelevator.money;

public class CashRegister {
	
	public static double balance;
	
	public CashRegister() {
		this.balance = 0;
	}

	public double getBalance() {
		return balance;
	}

	public void resetBalance() {
		this.balance = 0;
	}
	
	public void addMoneyToRegister(double addedFunds) {
		balance += addedFunds;
	}
	
	public void subtractMoney(double subtractedFunds) {
			balance -= subtractedFunds;
	}

	public int[] makeChange(double remainingBalance) {
		int[] changeArray = new int[7];
		while(remainingBalance >= 20) {
			changeArray[0] += 1;
			remainingBalance -= 20;
		}
		while(remainingBalance >= 10) {
			changeArray[1] += 1;
			remainingBalance -= 10;
		}
		while(remainingBalance >= 5) {
			changeArray[2] += 1;
			remainingBalance -= 5;
		}
		while(remainingBalance >= 1) {
			changeArray[3] += 1;
			remainingBalance -= 1;
		}
		while(remainingBalance >= .25) {
			changeArray[4] += 1;
			remainingBalance -= .25;
		}
		while(remainingBalance >= .1) {
			changeArray[5] += 1;
			remainingBalance -= .1;
		}
		while(remainingBalance >= .05) {
			changeArray[6] += 1;
			remainingBalance -= .05;
		}
		return changeArray;
	}
	
}
