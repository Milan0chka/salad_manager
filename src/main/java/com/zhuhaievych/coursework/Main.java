package com.zhuhaievych.coursework;

import com.zhuhaievych.coursework.controllers.Menu;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("MainMenu.fxml")));
        stage.setTitle("Chef's Assistant");
        stage.getIcons().add(new Image("com/zhuhaievych/coursework/icon.png"));
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.setOnCloseRequest(event -> {
            event.consume();
            Menu.confirmExit();
        });
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}