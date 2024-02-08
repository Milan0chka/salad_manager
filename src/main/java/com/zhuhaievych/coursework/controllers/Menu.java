package com.zhuhaievych.coursework.controllers;

import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Menu {
    public static final String ICON = "com/zhuhaievych/coursework/icon.png";
    public static final String RES_PATH = "/com/zhuhaievych/coursework/";

    public static final String ADD_ING_TO_DB = RES_PATH + "AddIngredientToDBMenu.fxml";
    public static final String ADD_ING_TO_RECIPE = RES_PATH + "AddIngredientToRecipeMenu.fxml";
    public static final String DEL_ING_FROM_DB = RES_PATH + "DeleteIngredientFromDBMenu.fxml";
    public static final String DEL_ING_FROM_RECIPE = RES_PATH + "DeleteIngredientFromRecipeMenu.fxml";
    public static final String MAIN_MENU = RES_PATH + "MainMenu.fxml";
    public static final String MANAGE_INGREDIENTS = RES_PATH + "ManageIngredientsMenu.fxml";
    public static final String MANAGE_RECIPES = RES_PATH + "ManageRecipesMenu.fxml";
    public static final String NEW_RECIPE = RES_PATH + "NewRecipeMenu.fxml";
    public static final String RECIPE_INFO = RES_PATH + "RecipeInfoMenu.fxml";
    public static final String SAVE_RECIPE = RES_PATH + "SaveRecipeMenu.fxml";
    public static final String SET_CALORITY_RANGE = RES_PATH + "SetCalorityRangeMenu.fxml";
    public static final String SORT_INGREDIENTS = RES_PATH + "SortIngredientsMenu.fxml";
    public static final String VIEW_INGS_IN_DB = RES_PATH + "ViewIngredientsInDBMenu.fxml";

    public static void showMenu(String menu) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Menu.class.getResource(menu));
        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = loader.getRoot();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setTitle("Chef's Assistant");
        stage.getIcons().add(new Image(ICON));
        stage.setResizable(false);
        stage.setScene(scene);
        stage.setOnCloseRequest(event -> {
            event.consume();
            confirmExit();
        });
        stage.show();
    }

    public static void confirmExit() {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("Confirm Exit");
        window.setWidth(250);
        window.setHeight(110);
        window.setResizable(false);

        Label label = new Label("Are you sure you want to exit?");
        Button exitButton = new Button("Exit");
        exitButton.setOnAction(event -> System.exit(0));
        Button cancelButton = new Button("Cancel");
        cancelButton.setOnAction(event -> window.close());

        HBox hb = new HBox(30);
        hb.getChildren().addAll(exitButton, cancelButton);
        hb.setAlignment(Pos.CENTER);
        VBox vb = new VBox(10);
        vb.getChildren().addAll(label, hb);
        vb.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vb);
        window.setScene(scene);
        window.showAndWait();
    }

    public static void infoWindow(String title, String message) {
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setWidth(250);
        window.setHeight(100);
        window.setResizable(false);

        Label label = new Label(message);
        Button closeButton = new Button("OK");
        closeButton.setOnAction(event -> window.close());

        VBox layout = new VBox(10);
        layout.getChildren().addAll(label, closeButton);
        layout.setAlignment(Pos.CENTER);

        Scene scene = new Scene(layout);
        window.setScene(scene);
        window.showAndWait();
    }

}
