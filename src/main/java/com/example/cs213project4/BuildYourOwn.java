package com.example.cs213project4;

import java.util.ArrayList;

public class BuildYourOwn extends Pizza {

    public static final double PRICE = 8.99;

    public final String type = "Build Your Own";

  public BuildYourOwn(){
      this.toppings = new ArrayList<>();
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
        if(this.toppings.size() > 3){
            double numTop = toppings.size();
            finalPrice += (toppings.size() - 3) * 1.49;
        }
        return finalPrice;
    }

    @Override
    public String toString() {
        return "[" + type + "] " + super.toString();
    }
}
