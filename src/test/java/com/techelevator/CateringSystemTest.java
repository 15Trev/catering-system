package com.techelevator;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.*;

import com.techelevator.ReaderAndWriter.InventoryReader;
import com.techelevator.inventory.Appetizer;
import com.techelevator.inventory.Item;



public class CateringSystemTest {

	private CateringSystem target;
	
	@Before
	public void setup() {
		Map<String, Item> testMap = new LinkedHashMap<String, Item>();
		testMap.put("A1", new Appetizer("Chicken Wings", "1.50"));
		target = new CateringSystem(testMap);
	}
	
	@Test
	public void cateringSystem_add_moeney() throws IOException {
		target.addMoney(25);
		double result = target.getFinalBalance();
		Assert.assertEquals(25, result, 0.009);
		Assert.assertTrue(target.addMoney(25));
	}
	
	@Test
	public void cateringSystem_add_too_much_money() throws IOException {
		target.addMoney(5001);
		double result = target.getFinalBalance();
		Assert.assertEquals(0, result, 0.009);
		Assert.assertFalse(target.addMoney(5001));
	}
	@Test
	public void cateringSystem_add_negative_money() throws IOException {
		target.addMoney(-1);
		double result = target.getFinalBalance();
		Assert.assertEquals(0, result, 0.009);
		Assert.assertFalse(target.addMoney(-1));
	}
	
	@Test
	public void cateringSystem_item_exists_retruns_true_if_correct_item_entered() {
		boolean result = target.checkIfItemExists("A1");
		Assert.assertTrue(result);
		
	}
	
	@Test
	public void cateringSystem_item_exists_retruns_false_if_incorrect_item_entered() {
		boolean result = target.checkIfItemExists("H1");
		Assert.assertFalse(result);
		
	}
	
	@Test
	public void cateringSystem_has_stock_returns_true_for_new_item() {
		boolean result = target.checkIfStockAvailable("A1");
		Assert.assertTrue(result);
	}
	
	@Test
	public void cateringSystem_quantity_available_returns_false_when_tried_to_purchase_51() {
		boolean result = target.checkIfQuantityAvailable("A1", 51);
		Assert.assertFalse(result);
	}
	
	@Test
	public void cateringSystem_quantity_available_returns_true_when_tried_to_purchase_50() {
		boolean result = target.checkIfQuantityAvailable("A1", 50);
		Assert.assertTrue(result);
	}
	
}
