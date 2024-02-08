package com.zhuhaievych.coursework.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import java.net.URL;
import java.util.ResourceBundle;

public class MainMenuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button ExitButton;

    @FXML
    private Button ManageRecipesButton;

    @FXML
    private Button ManageIngredientsButton;

    @FXML
    private Button NewRecipeButton;

    @FXML
    void onExitButtonClick(ActionEvent event) {
        Menu.confirmExit();
    }

    @FXML
    void onManageRecipesButtonClick(ActionEvent event) {
        ManageIngredientsButton.getScene().getWindow().hide();
        Menu.showMenu(Menu.MANAGE_RECIPES);
    }

    @FXML
    void onManageIngredientsButtonClick(ActionEvent event) {
        ManageIngredientsButton.getScene().getWindow().hide();
        Menu.showMenu(Menu.MANAGE_INGREDIENTS);
    }

    @FXML
    void onNewButtonClick(ActionEvent event) {
        NewRecipeButton.getScene().getWindow().hide();
        Menu.showMenu(Menu.NEW_RECIPE);
    }

    @FXML
    void initialize() {

    }

}