package com.example.cs213project4;

import java.util.ArrayList;

public class StoreOrders {

    private ArrayList<Order> storeOrders; //this will be the only instance variable used for construction

    private static int orderNumber = 0; //static variable as requested in instructions

    public StoreOrders(){
        this.storeOrders = new ArrayList<>();
        // create first order
        ArrayList<Pizza> pizzaList = new ArrayList<>();
        Order firstOrder = new Order(0, pizzaList);
        this.storeOrders.add(firstOrder);
    }
    public StoreOrders(ArrayList<Order> storeOrders){
        this.storeOrders = storeOrders;
    }
    //will add more later

    public ArrayList<Order> getStoreOrders() {
        return this.storeOrders;
    }

    public int getAvailable_OrderNumber() {
        int returnNum = orderNumber;
        return returnNum;
    }

    public void addOrder(Order order) {
        this.storeOrders.set(getAvailable_OrderNumber(), order);
        orderNumber++;

        ArrayList<Pizza> pizzaList = new ArrayList<>();
        Order setOrder = new Order(orderNumber, pizzaList);
        this.storeOrders.add(setOrder);
    }
}
