package com.example.javafx;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Login extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Login.class.getResource("login-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1000, 600);
        stage.setTitle("Login");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("sign.png")));
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(new WindowsCloseEvent(stage));
    }
}