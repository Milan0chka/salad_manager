package com.zhuhaievych.coursework.controllers;

import com.zhuhaievych.coursework.DBHandler;
import com.zhuhaievych.coursework.IngredientsLists;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.ResourceBundle;

public class AddIngredientToDBMenuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField NameInput;

    @FXML
    private TextField WeightInput;

    @FXML
    private TextField CalorityInput;

    @FXML
    private TextField PriceInput;

    @FXML
    private TextField ExpTermInput;

    @FXML
    private Button ConfirmButton;

    @FXML
    private Button BackButton;

    @FXML
    void onClickConfirmButton(ActionEvent event) {
        boolean nameIsValid, weightIsValid, calorityIsValid, priceIsValid, expTermIsValid;
        String name = "";
        double weight = 0, calority = 0, price = 0, expTerm = 0;
        try {
            name = NameInput.getText();
            nameIsValid = !(name.isBlank());
            weight = Double.parseDouble(WeightInput.getText());
            weightIsValid = weight > 0;
            calority = Double.parseDouble(CalorityInput.getText());
            calorityIsValid = calority > 0;
            price = Double.parseDouble(PriceInput.getText());
            priceIsValid = price > 0;
            expTerm = Double.parseDouble(ExpTermInput.getText());
            expTermIsValid = expTerm > 0;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            nameIsValid = false;
            weightIsValid = false;
            calorityIsValid = false;
            priceIsValid = false;
            expTermIsValid = false;
        }
        if (nameIsValid && weightIsValid && calorityIsValid && priceIsValid && expTermIsValid) {
            DBHandler.InsertIngredient(name, weight, calority, price, expTerm);
            IngredientsLists.AllIngredients.add(name);
            Menu.infoWindow("Success", name + " was added");
            ConfirmButton.getScene().getWindow().hide();
            Menu.showMenu(Menu.MANAGE_INGREDIENTS);
        } else Menu.infoWindow("Error!", "Some fields are incorrect");
    }

    @FXML
    void onClickBackButton(ActionEvent event) {
        BackButton.getScene().getWindow().hide();
        Menu.showMenu(Menu.MANAGE_INGREDIENTS);
    }

    @FXML
    void initialize() {

    }

}
