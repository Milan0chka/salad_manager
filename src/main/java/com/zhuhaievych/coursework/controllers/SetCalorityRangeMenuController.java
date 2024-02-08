package com.zhuhaievych.coursework.controllers;
import com.zhuhaievych.coursework.Ingredient;
import com.zhuhaievych.coursework.IngredientsForTable;
import com.zhuhaievych.coursework.IngredientsLists;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import com.zhuhaievych.coursework.controllers.Menu;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.util.ResourceBundle;

public class SetCalorityRangeMenuController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button BackButton;

    @FXML
    private TableView<IngredientsForTable> IngredientsTable;

    @FXML
    private TableColumn<IngredientsForTable, String> nameColumn = new TableColumn<>();

    @FXML
    private TableColumn<IngredientsForTable, Double> calorityColumn = new TableColumn<>();

    @FXML
    private TableColumn<IngredientsForTable, Double> priceColumn = new TableColumn<>();

    @FXML
    private TableColumn<IngredientsForTable, Double> expTermColumn = new TableColumn<>();

    @FXML
    private TableColumn<IngredientsForTable, Double> weightColumn = new TableColumn<>();

    @FXML
    private Label LabelTitle;

    @FXML
    private Button SetButton;

    @FXML
    private TextField maxInput;

    @FXML
    private TextField minInput;

    @FXML
    void onClickBackButton(ActionEvent event) {
        BackButton.getScene().getWindow().hide();
        Menu.showMenu(Menu.NEW_RECIPE);
    }

    @FXML
    void onClickSetButton(ActionEvent event) {
        double min = 0, max = 0;
        boolean minIsValid, maxIsValid;
        try {
            min = Double.parseDouble(minInput.getText());
            minIsValid = min > 0;
            max = Double.parseDouble(maxInput.getText());
            maxIsValid = max > min;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            minIsValid = false;
            maxIsValid = false;
        }
        if (minIsValid && maxIsValid) {
            initList(min, max);
            LabelTitle.setText("Ingredients with calority from " + min + " to " + max + ":");
        } else Menu.infoWindow("Error!", "Some fields are incorrect");

    }

    @FXML
    void initialize() {

    }

    void initList(double min, double max) {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        weightColumn.setCellValueFactory(new PropertyValueFactory<>("weight"));
        calorityColumn.setCellValueFactory(new PropertyValueFactory<>("calority"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        expTermColumn.setCellValueFactory(new PropertyValueFactory<>("expTerm"));
        ObservableList<IngredientsForTable> ingredients = FXCollections.observableArrayList();
        for (Ingredient ing : IngredientsLists.recipe.getUsedIngredients()) {
            if (ing.getCalority() >= min && ing.getCalority() <= max)
                ingredients.add(new IngredientsForTable(ing.getName(), ing.getWeight(), ing.getCalority(),
                        ing.getPrice(), ing.getExpTerm()));
        }
        IngredientsTable.setItems(ingredients);
    }

}