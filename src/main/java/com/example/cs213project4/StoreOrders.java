package com.example.cs213project4;

import java.util.ArrayList;

public class StoreOrders {

    private ArrayList<Order> storeOrders; //this will be the only instance variable used for construction

    public static int orderNumber = 0; //static variable as requested in instructions

    public StoreOrders(){
        this.storeOrders = new ArrayList<>();
    }
    public StoreOrders(ArrayList<Order> storeOrders){
        this.storeOrders = storeOrders;
    }
    //will add more later
}
