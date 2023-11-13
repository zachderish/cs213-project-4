package com.example.cs213project4;

import java.util.ArrayList;

public class Deluxe extends Pizza{

    public static final double PRICE = 14.99;
    public Deluxe(){
        ArrayList<Topping> toppings = new ArrayList<>();
        toppings.add(Topping.SAUSAGE);
        toppings.add(Topping.PEPPERONI);
        toppings.add(Topping.GREEN_PEPPERS);
        toppings.add(Topping.ONION);
        toppings.add(Topping.MUSHROOM);
        this.toppings = toppings;
        this.sauce = Sauce.TOMATO;
        this.size = Size.SMALL; //default is small
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
