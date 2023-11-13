package com.example.cs213project4;

import java.util.ArrayList;

public class Seafood extends Pizza{

    public static final double PRICE = 17.99;

    public Seafood(){
        ArrayList<Topping> toppings = new ArrayList<>();
        toppings.add(Topping.SHRIMP);
        toppings.add(Topping.SQUID);
        toppings.add(Topping.CRAB_MEATS);
        this.toppings = toppings;
        this.sauce = Sauce.ALFREDO;
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
