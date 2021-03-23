package com.techelevator;

import org.junit.*;

import Money.CashRegister;

public class CashRegisterTest {

	private CashRegister target;
	
	@Before
	public void setup() {
		target = new CashRegister();
	}
	
	@Test
	public void cashRegister_add_moeney() {
		target.addMoneyToRegister(25);
		double result = target.getBalance();
		Assert.assertEquals(25, result, 0.009);
		target.resetBalance();
	}
	
	@Test
	public void make_change_returns_string_array_with_zeroes_for_change_zero() {
		int[] resultArray= new int[7];
		resultArray = target.makeChange(0.05);
		Assert.assertEquals(1, resultArray[0]);
		Assert.assertEquals(0, resultArray[1]);
		Assert.assertEquals(0, resultArray[2]);
		Assert.assertEquals(0, resultArray[3]);
		Assert.assertEquals(0, resultArray[4]);
		Assert.assertEquals(0, resultArray[5]);
		Assert.assertEquals(0, resultArray[6]);
	}
	
	@Test
	public void make_change_returns_string_array_with_zeroes_for_change_73_45() {
		int[] resultArray= new int[7];
		resultArray = target.makeChange(73.45);
		Assert.assertEquals(3, resultArray[0]);
		Assert.assertEquals(1, resultArray[1]);
		Assert.assertEquals(0, resultArray[2]);
		Assert.assertEquals(3, resultArray[3]);
		Assert.assertEquals(1, resultArray[4]);
		Assert.assertEquals(2, resultArray[5]);
		Assert.assertEquals(0, resultArray[6]);
	}
	
	@Test
	public void make_change_returns_string_array_with_zeroes_for_negative_remaining_balance() {
		int[] resultArray= new int[7];
		resultArray = target.makeChange(-5.00);
		Assert.assertEquals(0, resultArray[0]);
		Assert.assertEquals(0, resultArray[1]);
		Assert.assertEquals(0, resultArray[2]);
		Assert.assertEquals(0, resultArray[3]);
		Assert.assertEquals(0, resultArray[4]);
		Assert.assertEquals(0, resultArray[5]);
		Assert.assertEquals(0, resultArray[6]);
	}
}