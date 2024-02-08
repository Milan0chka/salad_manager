package com.zhuhaievych.coursework.controllers;

import com.zhuhaievych.coursework.IngredientsLists;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import java.net.URL;
import java.util.ResourceBundle;

public class SortIngredientsMenuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button BackButton;

    @FXML
    private ChoiceBox<String> ParamInput;

    @FXML
    private Button SortButton;

    @FXML
    void onClickBackButton(ActionEvent event) {
        BackButton.getScene().getWindow().hide();
        Menu.showMenu(Menu.NEW_RECIPE);
    }

    @FXML
    void onClickSortButton(ActionEvent event) {
        String param = ParamInput.getValue();
        if (param.equals("")) {
            Menu.infoWindow("Error!", "Incorrect parameter");
        } else {
            IngredientsLists.recipe.sortIngredients(param);
            SortButton.getScene().getWindow().hide();
            Menu.showMenu(Menu.RECIPE_INFO);
        }
    }

    @FXML
    void initialize() {
     ParamInput.getItems().addAll("Name", "Calority", "Price", "Expiration term");
     ParamInput.setValue("");
    }

}
