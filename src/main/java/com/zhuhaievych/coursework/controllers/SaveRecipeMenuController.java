package com.zhuhaievych.coursework.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhuhaievych.coursework.IngredientsLists;
import com.zhuhaievych.coursework.DBHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class SaveRecipeMenuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button BackButton;

    @FXML
    private ListView<String> ExistingRecipesList;

    @FXML
    private TextField NameInput;

    @FXML
    private Button SaveButton;

    @FXML
    void onClickBackButton(ActionEvent event) {
        BackButton.getScene().getWindow().hide();
        Menu.showMenu(Menu.NEW_RECIPE);
    }

    @FXML
    void onClickSaveButton(ActionEvent event) {
        boolean nameIsValid;
        String name = NameInput.getText();
        nameIsValid = (!name.isBlank()) && (!ExistingRecipesList.getItems().contains(name));

        if (nameIsValid) {
            String json;
            try {
                json = new ObjectMapper().writeValueAsString(IngredientsLists.recipe);
                DBHandler.InsertRecipe(name, json);
                Menu.infoWindow("Success", name + " was saved");
            } catch (JsonProcessingException e) {
                Menu.infoWindow("Error!", "An error occurred while saving recipe");
                e.printStackTrace();
            }

        } else Menu.infoWindow("Error!", "Recipe's name is incorrect");

        SaveButton.getScene().getWindow().hide();
        Menu.showMenu(Menu.NEW_RECIPE);
    }

    @FXML
    void initialize() {
        List<String> existingRecipes = DBHandler.SelectAllRecipes();
        ExistingRecipesList.getItems().addAll(existingRecipes);
    }

}
