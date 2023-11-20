package com.example.cs213project4;

import java.util.ArrayList;
import java.util.List;

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

    public ArrayList<Integer> getOrderNumbers(){
        ArrayList<Integer> orderNums = new ArrayList<>();
        for(int i = 0; i<this.storeOrders.size(); i++){
            int tempNum = this.storeOrders.get(i).getOrderNumber();
            orderNums.add(tempNum);
        }
        return orderNums;
    }

    public Order find(int orderNumber){
        for(int i =0; i<this.storeOrders.size(); i++){
            if(this.storeOrders.get(i).getOrderNumber() == orderNumber){
                return storeOrders.get(i);
            }
        }
        return storeOrders.get(0);
    }

    public boolean removeOrder(int orderNumber){
        Order removeMe = find(orderNumber);
        this.storeOrders.remove(removeMe);
        return true;
    }

    public int numberOfOrders(){
        return this.storeOrders.size();
    }

}
