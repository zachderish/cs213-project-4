package com.example.cs213project4;

import java.util.ArrayList;


/**
 * Extend Pizza Class and Define BuildYourOwn Class
 * @author Kenrick Eagar, Zachary Derish
 */

public class BuildYourOwn extends Pizza {

    public static final double PRICE = 8.99;

    public final String type = "Build Your Own";

    /**
     * Build Your Own default constructor
     */
  public BuildYourOwn(){
      this.toppings = new ArrayList<>();
      this.size = Size.SMALL;
  }
    /**
     * Method to return Price of Build Your Own Pizza
     * @return double, representing price of pizza
     */
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

    /**
     * Deluxe pizza String format.
     * @return String
     */
    @Override
    public String toString() {
        return "[" + type + "] " + super.toString();
    }


}
