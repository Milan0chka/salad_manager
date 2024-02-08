package com.zhuhaievych.coursework.controllers;

import com.zhuhaievych.coursework.DBHandler;
import com.zhuhaievych.coursework.IngredientsLists;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import java.net.URL;
import java.util.ResourceBundle;

public class NewRecipeMenuController {

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
    private Button SaveRecipeButton;

    @FXML
    private Button SetCalorityRangeButton;

    @FXML
    private Button SortIngredientsButton;

    @FXML
    private Button RecipeInfoButton;

    @FXML
    void onAddButtonClick(ActionEvent event) {
        AddIngredientButton.getScene().getWindow().hide();
        Menu.showMenu(Menu.ADD_ING_TO_RECIPE);
    }

    @FXML
    void onBackButtonClick(ActionEvent event) {
        BackButton.getScene().getWindow().hide();
        Menu.showMenu(Menu.MAIN_MENU);
    }

    @FXML
    void onDeleteButtonClick(ActionEvent event) {
        DeleteIngredientButton.getScene().getWindow().hide();
        Menu.showMenu(Menu.DEL_ING_FROM_RECIPE);
    }

    @FXML
    void onSaveButtonClick(ActionEvent event) {
        SaveRecipeButton.getScene().getWindow().hide();
        Menu.showMenu(Menu.SAVE_RECIPE);
    }

    @FXML
    void onSetCalorityButtonClick(ActionEvent event) {
        SetCalorityRangeButton.getScene().getWindow().hide();
        Menu.showMenu(Menu.SET_CALORITY_RANGE);
    }

    @FXML
    void onSortButtonClick(ActionEvent event) {
        SortIngredientsButton.getScene().getWindow().hide();
        Menu.showMenu(Menu.SORT_INGREDIENTS);
    }

    @FXML
    void onRecipeInfoButtonClick(ActionEvent event) {
        RecipeInfoButton.getScene().getWindow().hide();
        Menu.showMenu(Menu.RECIPE_INFO);
    }

    @FXML
    void initialize() {
        IngredientsLists.AllIngredients = DBHandler.SelectAllIngredients();
    }

}