package com.example.cs213project4;

import java.util.ArrayList;

public class Meatzza extends Pizza{

    public static final double PRICE = 16.99;
    public Meatzza(){
        ArrayList<Topping> toppings = new ArrayList<>();
        toppings.add(Topping.SAUSAGE);
        toppings.add(Topping.HAM);
        toppings.add(Topping.BEEF);
        toppings.add(Topping.PEPPERONI);
        this.toppings = toppings;
        this.sauce = Sauce.TOMATO;
        this.size = Size.SMALL;
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
