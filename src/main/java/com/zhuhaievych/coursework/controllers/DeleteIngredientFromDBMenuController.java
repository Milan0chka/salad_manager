package com.zhuhaievych.coursework.controllers;

import com.zhuhaievych.coursework.DBHandler;
import com.zhuhaievych.coursework.IngredientsLists;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import java.net.URL;
import java.util.ResourceBundle;

public class DeleteIngredientFromDBMenuController {
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
    private ChoiceBox<String> NameInput = new ChoiceBox<>();

    @FXML
    void onClickBackButton(ActionEvent event) {
        BackButton.getScene().getWindow().hide();
        Menu.showMenu(Menu.MANAGE_INGREDIENTS);
    }

    @FXML
    void onClickDeleteButton(ActionEvent event) {
            String name = NameInput.getValue();
            nameIsValid = !name.isBlank();
        if (nameIsValid) {
            DBHandler.DeleteIngredient(name);
            IngredientsLists.AllIngredients.remove(name);
            Menu.infoWindow("Success", name + " was deleted");
            DeleteButton.getScene().getWindow().hide();
            Menu.showMenu(Menu.MANAGE_INGREDIENTS);
        } else Menu.infoWindow("Error!", "Ingredient's name is incorrect");
    }

    @FXML
    void initialize() {
        IngredientsLists.AllIngredients.forEach(ingName -> NameInput.getItems().add(ingName));
        NameInput.setValue("");
    }

}
