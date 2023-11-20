package com.example.cs213project4;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Order {

    private int orderNumber;
    private ArrayList<Pizza> pizzaList;

    public Order(int orderNumber, ArrayList<Pizza> pizzaList){
        this.orderNumber = orderNumber;
        this.pizzaList = pizzaList;
    }

    public void addPizza(Pizza pizza){
        this.pizzaList.add(pizza);
    }

    public void removePizza(int index){
        this.pizzaList.remove(index);
    }

    public int getOrderNumber(){
        return this.orderNumber;
    }

    /**
     * Get list of Pizza Strings for order.
     * @return ArrayList<String>
     */
    public ArrayList<String> getPizzas() {
        ArrayList<String> pizzas = new ArrayList<>();
        for (int i = 0; i < this.pizzaList.size(); i++) {
            pizzas.add(this.pizzaList.get(i).toString());
        }
        return pizzas;
    }

    /**
     * Get the subtotal for order
     * @return double
     */
    public double getOrder_Subtotal() {
        double returnVal = 0.0;
        for (int i = 0; i < this.pizzaList.size(); i++) {
            returnVal += this.pizzaList.get(i).price();
        }
        return returnVal;
    }

    public Pizza getPizza(int index){
        return pizzaList.get(index);
    }

}
