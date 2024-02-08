package com.zhuhaievych.coursework.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhuhaievych.coursework.DBHandler;
import com.zhuhaievych.coursework.Ingredient;
import com.zhuhaievych.coursework.IngredientsLists;
import com.zhuhaievych.coursework.Recipe;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ManageRecipesMenuController {
    static boolean nameIsValid = false;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button BackButton;

    @FXML
    private Button DeleteButton;

    @FXML
    private Button LoadButton;

    @FXML
    private ChoiceBox<String> NameInput;

    @FXML
    void onClickBackButton(ActionEvent event) {
        BackButton.getScene().getWindow().hide();
        Menu.showMenu(Menu.MAIN_MENU);
    }

    @FXML
    void onClickDeleteButton(ActionEvent event) {
        String name = NameInput.getValue();
        nameIsValid = !name.isBlank();
        if (nameIsValid) {
            DBHandler.DeleteRecipe(name);
            Menu.infoWindow("Success", name + " was deleted");
            DeleteButton.getScene().getWindow().hide();
            Menu.showMenu(Menu.MAIN_MENU);
        } else Menu.infoWindow("Error!", "Recipe's name is incorrect");
    }

    @FXML
    void onClickLoadButton(ActionEvent event) {
        String name = NameInput.getValue();
        nameIsValid = !name.isBlank();
        if (nameIsValid) {
            String json = DBHandler.SelectRecipe(name);

            ObjectMapper mapper = new ObjectMapper();
            Recipe loadedRecipe = new Recipe();
            try {
                loadedRecipe = mapper.readValue(json, Recipe.class);
            } catch (IOException e) {
                System.out.println(e.getMessage());
                Menu.infoWindow("Error!", "An error occurred while reading " + name);
            }
            IngredientsLists.recipe = loadedRecipe;
            IngredientsLists.UsedIngredients.clear();
            for (Ingredient ing : loadedRecipe.getUsedIngredients()) {
                IngredientsLists.UsedIngredients.add(ing.getName());
            }
            Menu.infoWindow("Success", name + " was loaded");
            LoadButton.getScene().getWindow().hide();
            Menu.showMenu(Menu.NEW_RECIPE);
        } else Menu.infoWindow("Error!", "Recipe's name is incorrect");
    }

    @FXML
    void initialize() {
    List<String> availableRecipes = DBHandler.SelectAllRecipes();
    availableRecipes.forEach(recName -> NameInput.getItems().add(recName));
    NameInput.setValue("");
    }

}
