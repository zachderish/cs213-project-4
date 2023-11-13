package com.example.cs213project4;

import java.util.ArrayList;

public class Pepperoni extends Pizza{

    public static final double PRICE = 10.99;
    public Pepperoni(){
        ArrayList<Topping> toppings = new ArrayList<>();
        toppings.add(Topping.PEPPERONI);
        this.toppings = toppings;
        this.sauce = Sauce.TOMATO;
        this.size = Size.SMALL;
    }
    @Override
    public double price(){
        double finalPrice = PRICE;
        if(this.size == Size.MEDIUM){
            finalPrice+= 2;
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
