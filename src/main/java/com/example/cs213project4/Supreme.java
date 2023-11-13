package com.example.cs213project4;

import java.util.ArrayList;

public class Supreme extends Pizza{
    public static final double PRICE = 15.99;
    public Supreme(){
        ArrayList<Topping> toppings = new ArrayList<>();
        toppings.add(Topping.SAUSAGE);
        toppings.add(Topping.PEPPERONI);
        toppings.add(Topping.HAM);
        toppings.add(Topping.GREEN_PEPPERS);
        toppings.add(Topping.ONION);
        toppings.add(Topping.BLACK_OLIVE);
        toppings.add(Topping.MUSHROOM);
        this.toppings = toppings;
        this.sauce = Sauce.TOMATO;
    }
    @Override
    public double price(){
        double finalPrice = PRICE;
        if(this.size == Size.MEDIUM){
            finalprice+= 2;
        }
        if(this.size == Size.LARGE){
            finalPrice+=4;
        }
        if(this.extraCheese){
            finalPrice+=1;
        }
        if(this.extraSauce){
            finalPrice+=1;
        }
        return finalPrice;
    }
}
