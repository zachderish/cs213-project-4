package com.example.cs213project4;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.EnumSet;
import java.util.ResourceBundle;

import static com.example.cs213project4.PizzaMaker.createPizza;

public class BuildYourOwnController implements Initializable {

    @FXML
    private ChoiceBox<String> buildOwnChoiceBox;
    @FXML
    private TextArea byoTextArea;
    @FXML
    private RadioButton byoTomatoButton, byoAlfredoButton;
    @FXML
    private CheckBox byoExtraSauceButton, byoExtraCheeseButton;
    @FXML
    private ListView<String> additionalToppingsList, selectedToppingsList;
    @FXML
    private Button addButton, removeButton;
    //ImageView image = new Image(getClass().getResourceAsStream("shaq.jpg"));
    private final String[] pizzaSizes = {"Small", "Medium", "Large"};


    private final String[] pizzaToppings = {"Sausage","Pepperoni","Green Peppers","Onion","Mushroom","Black Olive",
                                        "Beef", "Ham", "Shrimp","Squid", "Crab Meat", "Pineapple", "Pickles"}   ;

    private BuildYourOwn pizza = (BuildYourOwn) createPizza("BuildYourOwn");

    /**
     *Method to disable alfredo sauce button when tomatos selected
     * @param event, when tomato button is selected
     */
    @FXML
    void disableAlfredoButton(ActionEvent event){
        byoAlfredoButton.setSelected(false);
        byoTomatoButton.setSelected(true);
        setSauce(event);
    }

    /**
     *Method to disable tomato sauce button when alfredos selected
     * @param event, when alfredos button is selected
     */
    @FXML
    void disableTomatoButton(ActionEvent event){
        byoTomatoButton.setSelected(false);
        byoAlfredoButton.setSelected(true);
        setSauce(event);
    }

    /**
     *Method to initialize choice box and list view
     * @param url, default parameter
     * @param resourceBundle, default parameter
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buildOwnChoiceBox.getItems().addAll(pizzaSizes);
        buildOwnChoiceBox.setOnAction(this::setPizzaSize);

        additionalToppingsList.getItems().addAll(pizzaToppings);


    }

    /**
     * Button handler for back button.
     * @param event ActionEvent
     */
    @FXML
    protected void onBackButtonClick(ActionEvent event) throws IOException {
        Parent mainMenuRoot = FXMLLoader.load(getClass().getResource("main-menu.fxml"));
        Scene mainMenuScene = new Scene(mainMenuRoot);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setTitle("RU Pizza <Main Menu>");

        stage.setScene(mainMenuScene);
        stage.show();
    }

    /**
     * Method to set pizza size
     * @param event, once a size is selected/changed
     */
    @FXML
    void setPizzaSize(ActionEvent event){
        String size = buildOwnChoiceBox.getValue().toUpperCase();
        if(size.equals(Size.SMALL.toString())){
            pizza.size = Size.SMALL;
            updatePrice(event);
            return;
        }
        if(size.equals(Size.MEDIUM.toString())){
            pizza.size = Size.MEDIUM;
            updatePrice(event);
            return;
        }
        pizza.size = Size.LARGE;
        updatePrice(event);
    }

    /**
     * Method to display the most recent pizza price
     * @param event
     */
    @FXML
    void updatePrice(ActionEvent event){
    byoTextArea.setText(Double.toString(pizza.price()));
    }

    /**
     * Method to set pizza sauce
     * @param event, when a sauce button is selected
     */
    @FXML
    void setSauce(ActionEvent event){
        if(byoTomatoButton.isSelected()){
            pizza.sauce = Sauce.TOMATO;
            return;
        }
        if(byoAlfredoButton.isSelected()){
            pizza.sauce = Sauce.ALFREDO;
        }

    }

    /**
     *Method to set extra cheese and extra sauce in pizza
     * @param event, when a extra sauce or cheese button is selected
     */
    @FXML
    void setExtras(ActionEvent event){
        if(byoExtraCheeseButton.isSelected()){
            pizza.extraCheese = true;

        }
        if(byoExtraSauceButton.isSelected()){
            pizza.extraSauce = true;
        }
        if(!byoExtraCheeseButton.isSelected()){
            pizza.extraCheese = false;
        }
        if(!byoExtraSauceButton.isSelected()){
            pizza.extraSauce = false;
        }

        updatePrice(event);
    }

    /**
     * Method to convert String to Topping Enum
     * @param input, string representation of Topping
     * @return Topping enum
     */
    private Topping getTopping(String input){
        Topping[] tempToppings = Topping.BEEF.getList();
        for(int i =0; i<tempToppings.length; i++){
            String temp = tempToppings[i].toString();
            if(temp.equals(input.toUpperCase())){
                return tempToppings[i];
            }
        }
        if(input.equals("Green Peppers")){
            return Topping.GREEN_PEPPERS;
        }
        if(input.equals("Black Olives")){
            return Topping.BLACK_OLIVE;
        }

        return Topping.CRAB_MEATS;
    }

    /**
     * Method to send alert when too many toppings are selected
     */
    void tooManyToppingsAlert(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Toppings Warning");
        alert.setContentText("You Reached The Maximum Amount Of Toppings");
        alert.showAndWait();

    }

    /**
     * Method to send alert when no selection
     */
    void selectSomethingAlert(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("No Selection");
        alert.setContentText("Please Click On The Topping To Be Removed/Added");
        alert.showAndWait();
    }
    /**
     * Method to send alert when no size is selected
     */
    void noSizeSelectedAlert(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("No Selection");
        alert.setContentText("No Size Specified; Size Will Default to Small");
        alert.showAndWait();
    }

    /**
     * Method to send alert when no sauce is selected
     */
    void noSauceSelectedAlert(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("No Selection");
        alert.setContentText("No Sauce Specified; Sauce Will Default to Tomato");
        alert.showAndWait();
    }

    /**
     * Method to add selected toppings to build your own pizza
     * @param event, when add button is selected
     */
    @FXML
    void addToppings(ActionEvent event){
        int numOfTops = pizza.toppings.size();
        if(numOfTops < 7) {
            String currentTopping = additionalToppingsList.getSelectionModel().getSelectedItem();
            if(currentTopping == null){selectSomethingAlert(); return;}
            pizza.toppings.add(getTopping(currentTopping));
            additionalToppingsList.getItems().remove(currentTopping);
            selectedToppingsList.getItems().add(currentTopping);
            updatePrice(event);
            return;
        }
        tooManyToppingsAlert();
    }
    /**
     * Method to remove selected toppings from build your own pizza
     * @param event, when remove button is selected
     */
    @FXML
    void removeToppings(ActionEvent event){
        String currentTopping = selectedToppingsList.getSelectionModel().getSelectedItem();
        if(currentTopping == null){selectSomethingAlert(); return;}
        pizza.toppings.remove(getTopping(currentTopping));
        selectedToppingsList.getItems().remove(currentTopping);
        additionalToppingsList.getItems().add(currentTopping);
        updatePrice(event);
    }

}