package com.zhuhaievych.coursework.controllers;

import com.zhuhaievych.coursework.DBHandler;
import com.zhuhaievych.coursework.IngredientsLists;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class AddIngredientToRecipeMenuController {
    static boolean weightIsValid = false;
    static boolean nameIsValid = false;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button BackButton;

    @FXML
    private Button ConfirmButton;

    @FXML
    private ChoiceBox<String> NameInput = new ChoiceBox<>();

    @FXML
    private TextField WeightInput;

    @FXML
    void onClickBackButton(ActionEvent event) {
        BackButton.getScene().getWindow().hide();
        Menu.showMenu(Menu.NEW_RECIPE);
    }

    @FXML
    void onClickConfirmButton(ActionEvent event) {
        String name = NameInput.getValue();
        double weight = 0, availableWeight;
        try {
            nameIsValid = !name.isBlank();
            weight = Double.parseDouble(WeightInput.getText());
            availableWeight = DBHandler.getAvailableWeight(name);
            weightIsValid = (weight > 0) && (weight <= availableWeight);
            if (!weightIsValid) Menu.infoWindow("Error!", "Max weight for " + name + " is " + availableWeight);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            nameIsValid = false;
            weightIsValid = false;
        }
        if (nameIsValid && weightIsValid) {
            IngredientsLists.UsedIngredients.add(name);
            IngredientsLists.recipe.addIngredient(name, weight);
            DBHandler.updateAvailableWeight(name, -weight);
            Menu.infoWindow("Success", name + " was added");
            ConfirmButton.getScene().getWindow().hide();
            Menu.showMenu(Menu.NEW_RECIPE);
        } else Menu.infoWindow("Error!", "Some fields are incorrect");
    }

    @FXML
    void initialize() {
        List<String> availableIngs = IngredientsLists.AllIngredients;
        for (String ingName : IngredientsLists.UsedIngredients) {
            availableIngs.remove(ingName);
        }
        NameInput.getItems().addAll(availableIngs);
        NameInput.setValue("");
    }

}
