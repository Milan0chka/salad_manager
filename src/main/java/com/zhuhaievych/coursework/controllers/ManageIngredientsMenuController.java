package com.zhuhaievych.coursework.controllers;

import com.zhuhaievych.coursework.DBHandler;
import com.zhuhaievych.coursework.IngredientsLists;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import java.net.URL;
import java.util.ResourceBundle;

public class ManageIngredientsMenuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button AddIngredientButton;

    @FXML
    private Button BackButton;

    @FXML
    private Button DeleteIngredientButton;

    @FXML
    private Button ViewIngredientsButton;

    @FXML
    void onClickAddButton(ActionEvent event) {
        AddIngredientButton.getScene().getWindow().hide();
        Menu.showMenu(Menu.ADD_ING_TO_DB);
    }

    @FXML
    void onClickDeleteButton(ActionEvent event) {
        DeleteIngredientButton.getScene().getWindow().hide();
        Menu.showMenu(Menu.DEL_ING_FROM_DB);
    }

    @FXML
    void onClickViewButton(ActionEvent event) {
        ViewIngredientsButton.getScene().getWindow().hide();
        Menu.showMenu(Menu.VIEW_INGS_IN_DB);
    }

    @FXML
    void onClickBackButton(ActionEvent event) {
        BackButton.getScene().getWindow().hide();
        Menu.showMenu(Menu.MAIN_MENU);
    }

    @FXML
    void initialize() {
        IngredientsLists.AllIngredients = DBHandler.SelectAllIngredients();
    }

}
