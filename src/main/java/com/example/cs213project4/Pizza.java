package com.example.cs213project4;
import java.text.DecimalFormat;
import java.util.ArrayList;
public abstract class Pizza {

    protected ArrayList<Topping> toppings;
    protected Size size;
    protected Sauce sauce;
    protected boolean extraSauce;
    protected boolean extraCheese;

    public abstract double price();

    /**
     * Pizza String format.
     * @return String
     */
    @Override
    public String toString() {
        String price = new DecimalFormat("#,##0.00").format(this.price());
        String toppings = this.toppings.toString().replace("[", "");
        toppings = toppings.replace("]", "");
        return toppings + " $" + price;
    }

}
