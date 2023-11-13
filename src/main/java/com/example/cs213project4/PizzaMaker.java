package com.example.cs213project4;

import java.util.ArrayList;

public class PizzaMaker {
    public static Pizza createPizza(String pizzaType){ // we dont have to worry about size or extrasauce can manually update the pizzas

        if(pizzaType.equals("Deluxe")){
            return new Deluxe();
        }
        if(pizzaType.equals("Supreme")){
            return new Supreme();
        }
        if(pizzaType.equals("Seafood")){
            return new Seafood();
        }
        if(pizzaType.equals("Pepperoni")){
            return new Pepperoni();
        }
        if(pizzaType.equals("Meatzza")){
            return new Meatzza();
        }

            return new BuildYourOwn();

    }
//testbed will delete later
    public static void main(String[] args){

        Pizza test = createPizza("Deluxe");
        System.out.println(test.price());
        test.size = Size.LARGE;
        System.out.println(test.price());


        System.out.println(test.sauce);
        System.out.println(test.size);
    }

}
