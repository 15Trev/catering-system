package com.techelevator;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.junit.*;

import com.techelevator.inventory.Appetizer;
import com.techelevator.inventory.Item;
import com.techelevator.readerandwriter.InventoryReader;

public class InventoryReaderTest {

	InventoryReader inventoryReader;
	
	@Before
	public void setup() {
		inventoryReader = new InventoryReader();
		
	}
	
	
	@Test
	public void key_setup_returns_correct_map() throws FileNotFoundException {
		Map<String, String[]> resultMap = new LinkedHashMap<String, String[]>();
		resultMap = inventoryReader.keySetup();
		
		Assert.assertEquals(resultMap.get("A1")[0], "A");
		Assert.assertEquals(resultMap.get("B2")[1], "Wine");
		Assert.assertEquals(resultMap.get("D3")[2], "1.10");
		Assert.assertEquals(resultMap.get("E4")[0], "E");
		
		
	}
	
	@Test
	public void get_map_with_items_returns_correct_map() throws FileNotFoundException {
		Map<String, Item> resultMap = new LinkedHashMap<String, Item>();
		resultMap = inventoryReader.getMapWithItems();
		
		Assert.assertEquals(resultMap.get("A1").getType(), "Appetizer");
		Assert.assertEquals(resultMap.get("B2").getType(), "Beverage");
		Assert.assertEquals(resultMap.get("D3").getName(), "Brownies");
		Assert.assertEquals(resultMap.get("E2").getPrice(), 9.45,.009);
		
		
		
	}
	
	
}
