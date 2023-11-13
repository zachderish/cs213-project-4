package com.example.cs213project4;

public class BuildYourOwn extends Pizza {

    public static final double PRICE = 8.99;

  public BuildYourOwn(){
      this.size = Size.SMALL; //honestly we dont really need to define constructor since we have to customize everything
                              // will likely delete later since Pizza default constructor is probably enough
  }
    @Override
    public double price(){
        double finalPrice = PRICE;
        if(this.size == Size.MEDIUM){
            finalprice+= 2;
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
        return finalPrice;
    }
}
