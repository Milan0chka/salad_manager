package com.zhuhaievych.coursework.controllers;

import com.zhuhaievych.coursework.DBHandler;
import com.zhuhaievych.coursework.Ingredient;
import com.zhuhaievych.coursework.IngredientsForTable;
import com.zhuhaievych.coursework.IngredientsLists;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.util.ResourceBundle;

public class ViewIngredientsInDBMenuController {

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
    void onClickBackButton(ActionEvent event) {
        BackButton.getScene().getWindow().hide();
        Menu.showMenu(Menu.MANAGE_INGREDIENTS);
    }

    @FXML
    void initialize() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        weightColumn.setCellValueFactory(new PropertyValueFactory<>("weight"));
        calorityColumn.setCellValueFactory(new PropertyValueFactory<>("calority"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        expTermColumn.setCellValueFactory(new PropertyValueFactory<>("expTerm"));

        AllIngredientsTable.setItems(getIngredients());
    }

    ObservableList<IngredientsForTable> getIngredients() {
        ObservableList<IngredientsForTable> ingredients = FXCollections.observableArrayList();
        for (String ingName : IngredientsLists.AllIngredients) {
            Ingredient ing = DBHandler.SelectIngredient(ingName);
            ingredients.add(new IngredientsForTable(ing.getName(), ing.getWeight(), ing.getCalority(),
                    ing.getPrice(), ing.getExpTerm()));
        }
        return ingredients;
    }

}
