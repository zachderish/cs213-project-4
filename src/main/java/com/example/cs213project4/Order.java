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

    public void removePizza(Pizza pizza){
        this.pizzaList.remove(pizza);
    }

    public int getOrderNumber(){
        return this.orderNumber;
    }


}
