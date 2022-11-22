package org.ratcm;

import org.ratcm.exceptions.RecipeException;

public class GWT_Scenarios {
	private CoffeeMaker cm;
	private Recipe r1;
	private int monnaieObtenue;

	private void givenACoffeeMachineWithCoffeeRecipe(){
		cm = new CoffeeMaker();

		// Set up for r1
		r1 = new Recipe();
		r1.setName("Coffee");
		try {
			r1.setAmtChocolate("0");
			r1.setAmtCoffee("3");
			r1.setAmtMilk("1");
			r1.setAmtSugar("1");
			r1.setPrice("50");
		} catch (RecipeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void whenACoffeeIsOrderedWith75Cts() {
		monnaieObtenue = cm.makeCoffee(0, 75);
	}

	private void thenLaMonnaieObtenueEstDe25() {
		assert (monnaieObtenue == 25);
		System.out.println("Test passed");
	}

	public static void main(String[] args) {
		GWT_Scenarios t = new GWT_Scenarios();
		t.givenACoffeeMachineWithCoffeeRecipe();
		t.whenACoffeeIsOrderedWith75Cts();
		t.thenLaMonnaieObtenueEstDe25();	
	}

}
