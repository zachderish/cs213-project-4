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
    //ImageView image = new Image(getClass().getResourceAsStream("shaq.jpg"));
    private final String[] pizzaSizes = {"Small", "Medium", "Large"};

    private BuildYourOwn pizza = (BuildYourOwn) createPizza("BuildYourOwn");

    @FXML
    void disableAlfredoButton(ActionEvent event){
        byoAlfredoButton.setSelected(false);
        byoTomatoButton.setSelected(true);
        setSauce(event);
    }
    @FXML
    void disableTomatoButton(ActionEvent event){
        byoTomatoButton.setSelected(false);
        byoAlfredoButton.setSelected(true);
        setSauce(event);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buildOwnChoiceBox.getItems().addAll(pizzaSizes);
        buildOwnChoiceBox.setOnAction(this::setPizzaSize);
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
    @FXML
    void updatePrice(ActionEvent event){
    byoTextArea.setText(Double.toString(pizza.price()));
    }

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


}