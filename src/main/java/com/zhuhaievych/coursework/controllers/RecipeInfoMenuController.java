package com.zhuhaievych.coursework.controllers;

import com.zhuhaievych.coursework.Ingredient;
import com.zhuhaievych.coursework.IngredientsForTable;
import com.zhuhaievych.coursework.IngredientsLists;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.util.ResourceBundle;

public class RecipeInfoMenuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<IngredientsForTable> AllIngredientsTable;

    @FXML
    private TableColumn<IngredientsForTable, String> nameColumn = new TableColumn<>();

    @FXML
    private TableColumn<IngredientsForTable, Double> weightColumn = new TableColumn<>();

    @FXML
    private TableColumn<IngredientsForTable, Double> calorityColumn = new TableColumn<>();

    @FXML
    private TableColumn<IngredientsForTable, Double> priceColumn = new TableColumn<>();

    @FXML
    private TableColumn<IngredientsForTable, Double> expTermColumn = new TableColumn<>();


    @FXML
    private Button BackButton;

    @FXML
    private Label expTerm;

    @FXML
    private Label totalCalority;

    @FXML
    private Label totalPrice;

    @FXML
    void onClickBackButton(ActionEvent event) {
        BackButton.getScene().getWindow().hide();
        Menu.showMenu(Menu.NEW_RECIPE);
    }

    @FXML
    void initialize() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        weightColumn.setCellValueFactory(new PropertyValueFactory<>("weight"));
        calorityColumn.setCellValueFactory(new PropertyValueFactory<>("calority"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        expTermColumn.setCellValueFactory(new PropertyValueFactory<>("expTerm"));
        AllIngredientsTable.setItems(getIngredients());

        totalCalority.setText("Total calority: " + IngredientsLists.recipe.getTotalCalority());
        totalPrice.setText("Total price: " + IngredientsLists.recipe.getTotalPrice());
        expTerm.setText("Expiration term: " + IngredientsLists.recipe.getTotalExpTerm());
    }

    ObservableList<IngredientsForTable> getIngredients() {
        ObservableList<IngredientsForTable> ingredients = FXCollections.observableArrayList();

        for (Ingredient ing : IngredientsLists.recipe.getUsedIngredients()) {
            ingredients.add(new IngredientsForTable(ing.getName(), ing.getWeight(), ing.getCalority(),
                    ing.getPrice(), ing.getExpTerm()));
        }
        return ingredients;
    }

}
