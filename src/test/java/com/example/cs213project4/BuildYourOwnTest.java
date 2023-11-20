package com.example.cs213project4;

import org.junit.Test;

import static org.junit.Assert.*;

public class BuildYourOwnTest {

    @Test
    public void smallPizza_ThreeToppings() {
        BuildYourOwn pizza = new BuildYourOwn();
        pizza.size = Size.SMALL;
        pizza.toppings.add(Topping.BEEF);
        pizza.toppings.add(Topping.SAUSAGE);
        pizza.toppings.add(Topping.HAM);
        assertTrue(pizza.price() == 8.99);
    }

    @Test
    public void smallPizza_FourToppings() {
        BuildYourOwn pizza = new BuildYourOwn();
        pizza.size = Size.SMALL;
        pizza.toppings.add(Topping.BEEF);
        pizza.toppings.add(Topping.SAUSAGE);
        pizza.toppings.add(Topping.HAM);
        pizza.toppings.add(Topping.GREEN_PEPPERS);
        assertTrue(pizza.price() == 10.48);
    }

    @Test
    public void mediumPizza_ThreeToppings() {
        BuildYourOwn pizza = new BuildYourOwn();
        pizza.size = Size.MEDIUM;
        pizza.toppings.add(Topping.BEEF);
        pizza.toppings.add(Topping.SAUSAGE);
        pizza.toppings.add(Topping.HAM);
        assertTrue(pizza.price() == 10.99);
    }

    @Test
    public void mediumPizza_FourToppings() {
        BuildYourOwn pizza = new BuildYourOwn();
        pizza.size = Size.MEDIUM;
        pizza.toppings.add(Topping.BEEF);
        pizza.toppings.add(Topping.SAUSAGE);
        pizza.toppings.add(Topping.HAM);
        pizza.toppings.add(Topping.PINEAPPLE);
        assertTrue(pizza.price() == 12.48);
    }

    @Test
    public void largePizza_ThreeToppings() {
        BuildYourOwn pizza = new BuildYourOwn();
        pizza.size = Size.LARGE;
        pizza.toppings.add(Topping.BEEF);
        pizza.toppings.add(Topping.SAUSAGE);
        pizza.toppings.add(Topping.HAM);
        assertTrue(pizza.price() == 12.99);
    }

    @Test
    public void largePizza_FourToppings() {
        BuildYourOwn pizza = new BuildYourOwn();
        pizza.size = Size.LARGE;
        pizza.toppings.add(Topping.BEEF);
        pizza.toppings.add(Topping.SAUSAGE);
        pizza.toppings.add(Topping.HAM);
        pizza.toppings.add(Topping.PINEAPPLE);
        assertTrue(pizza.price() == 14.48);
    }

    @Test
    public void largePizza_SevenToppings_ExtraSauceExtraCheese() {
        BuildYourOwn pizza = new BuildYourOwn();
        pizza.size = Size.LARGE;
        pizza.toppings.add(Topping.BEEF);
        pizza.toppings.add(Topping.SAUSAGE);
        pizza.toppings.add(Topping.HAM);
        pizza.toppings.add(Topping.PINEAPPLE);
        pizza.toppings.add(Topping.PEPPERONI);
        pizza.toppings.add(Topping.ONION);
        pizza.toppings.add(Topping.GREEN_PEPPERS);
        pizza.extraSauce = true;
        pizza.extraCheese = true;
        assertTrue(pizza.price() == 20.95);
    }

}