package com.example.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class ConfirmController{
    @FXML
    private Button nobutton;

    @FXML
    private Button yesbutton;

    @FXML
    private Label confirmtext;
    protected static Stage primarystage;
    protected static Stage thisstage;

    @FXML
    void onnoclick(ActionEvent event) {
        thisstage.close();

    }

    @FXML
    void onyesclick(ActionEvent event) {
        thisstage.close();
        primarystage.close();
    }
}

