package org.ratcm;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.ratcm.exceptions.InventoryException;

public class CoffeeMakerTest {

	private CoffeeMaker cm;
	private Recipe r1;
	private Recipe r2;
	private Recipe r3;
	private Recipe r4;

	@BeforeEach
	public void setUp() throws Exception {
		cm = new CoffeeMaker();
		
		//Set up for r1
		r1 = new Recipe();
		r1.setName("Coffee");
		r1.setAmtChocolate("0");
		r1.setAmtCoffee("3");
		r1.setAmtMilk("1");
		r1.setAmtSugar("1");
		r1.setPrice("50");
		
		//Set up for r2
		r2 = new Recipe();
		r2.setName("Mocha");
		r2.setAmtChocolate("20");
		r2.setAmtCoffee("3");
		r2.setAmtMilk("1");
		r2.setAmtSugar("1");
		r2.setPrice("75");
		
		//Set up for r3
		r3 = new Recipe();
		r3.setName("Latte");
		r3.setAmtChocolate("0");
		r3.setAmtCoffee("3");
		r3.setAmtMilk("3");
		r3.setAmtSugar("1");
		r3.setPrice("100");
		
		//Set up for r4
		r4 = new Recipe();
		r4.setName("Hot Chocolate");
		r4.setAmtChocolate("4");
		r4.setAmtCoffee("0");
		r4.setAmtMilk("1");
		r4.setAmtSugar("1");
		r4.setPrice("65");
		
	}

	@Test
	public void testAddInventory() throws InventoryException {
		assertThrows(InventoryException.class,
				() -> { cm.addInventory("4", "-1", "asdf", "3"); });
	}
	
	@Test
	public void testMakeCoffee() {
		cm.addRecipe(r1);
		assertEquals(25, cm.makeCoffee(0, 75));
	}
	
	@Test
	public void testMakeCoffeeWithoutEnoughMoney() {
		cm.addRecipe(r1);
		final int moneyAmount = 25;
		assertTrue(moneyAmount < cm.getRecipes()[0].getPrice());
		assertEquals(moneyAmount, cm.makeCoffee(0, moneyAmount));
	}
	
	@Test
	public void testMakeMochaWithNoSufficientChocolate() {
		cm.addRecipe(r2);
		assertEquals(75, cm.makeCoffee(0, 75));
		
	}
	
	@Test
	public void testMakingManyCoffeesEmptiesStock() {
		cm.addRecipe(r1);
		assertEquals(0,cm.makeCoffee(0, 50));
		assertEquals(0,cm.makeCoffee(0, 50));
		assertEquals(0,cm.makeCoffee(0, 50));
		assertEquals(0,cm.makeCoffee(0, 50));
		assertEquals(0,cm.makeCoffee(0, 50));
		assertEquals(50,cm.makeCoffee(0, 50));
	}
}
