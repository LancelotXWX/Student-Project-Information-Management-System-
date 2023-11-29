package com.example.javafx;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;

public class WindowsCloseEvent implements EventHandler<WindowEvent> {

    private Stage stage;

    public WindowsCloseEvent(Stage stage){
        this.stage = stage;
    }

    public void handle(WindowEvent event) {
        event.consume();
        URL url = getClass().getResource("confirm-view.fxml");
        Parent root = null;
        try {
            root = FXMLLoader.load(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        ConfirmController.primarystage = this.stage;
        Scene scene = new Scene(root,250,300);
        Stage newstage = new Stage();
        ConfirmController.thisstage = newstage;
        newstage.getIcons().add(new Image(getClass().getResourceAsStream("confirm.png")));
        newstage.setScene(scene);
        newstage.setTitle("确认");
        //stage.showAndWait();
        newstage.show();
    }
}