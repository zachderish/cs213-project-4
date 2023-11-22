package com.example.cs213project4;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.FileChooser.ExtensionFilter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
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

    public int findIndexOfOrder(Order order){
        for(int i =0; i<this.storeOrders.size(); i++){
            if(storeOrders.get(i).getOrderNumber() == order.getOrderNumber()){
                return i;
            }
        }
        return -1; //NOT FOUND
    }

    public void addOrder(Order order) {
        int index = findIndexOfOrder(order);
        this.storeOrders.set(index, order);
      //  this.storeOrders.set(getAvailable_OrderNumber(), order);


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

    public String orderToString(int index){
       Order order = storeOrders.get(index);
       int orderNumber = order.getOrderNumber();
       double total = order.getOrder_Subtotal();
       double tax = 0.06625;
       total = (total*tax) + total;

       String returnString = "Order Number " + Integer.toString(orderNumber);
       ArrayList<String> pizzaStrings = order.getPizzas();
       if(pizzaStrings.isEmpty()){
           return "";
       }
       for(int i =0; i<pizzaStrings.size(); i++){
           returnString += "\n" + pizzaStrings.get(i);
       }
       String totalString = new DecimalFormat("#,##0.00").format(total);
       returnString+= "\n" + "Total Price: $"+ totalString;
       return returnString;
    }

    public boolean export(Stage stage){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Save File");
        ExtensionFilter ex1 = new ExtensionFilter("Text Files", "*.txt");
        fileChooser.getExtensionFilters().add(ex1);
        File selectedFile = fileChooser.showSaveDialog(stage);

        if(selectedFile == null){
            return false;
        }
        String finalProduct = "";
        for(int i =0; i<numberOfOrders(); i++){
            finalProduct += orderToString(i);
            finalProduct+= "\n\n";
        }

        String absPath = selectedFile.getAbsolutePath();
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(absPath));
            writer.write(finalProduct);
            writer.close();
            return true;
        } catch(IOException e){
            return false;
        }

    }


}
