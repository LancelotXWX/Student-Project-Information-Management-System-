package com.example.javafx;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.AnchorPane;


import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LoginController {
    private boolean check = true;
    @FXML
    private Button eye_button;
    @FXML
    private Button loginbutton;
    @FXML
    private ImageView hide_eye;
    @FXML
    private ImageView open_eye;

    @FXML
    private AnchorPane loginpane;

    @FXML
    private PasswordField password_hide;

    @FXML
    private TextField password_show;

    @FXML
    private TextField username;

    @FXML
    private ComboBox<String> choice;

    @FXML
    void oneyebuttonclick(ActionEvent event) {
        if(check) {
            String s = password_hide.getText();
            password_show.setVisible(true);
            password_show.requestFocus();
            password_show.setText(s);
            password_show.positionCaret(s.length());
            password_hide.setVisible(false);
            hide_eye.setVisible(false);
            open_eye.setVisible(true);
            this.check = false;
            eye_button.setGraphic(open_eye);
        }
        else
        {
            String s = password_show.getText();
            password_hide.setVisible(true);
            password_hide.requestFocus();
            password_hide.setText(s);
            password_hide.positionCaret(s.length());
            password_show.setVisible(false);
            open_eye.setVisible(false);
            hide_eye.setVisible(true);
            this.check = true;
            eye_button.setGraphic(hide_eye);
        }

    }
    @FXML
    void onloginbutton(ActionEvent event) {
        //判断身份
        if (choice.getValue().equals("学生")) {
            Main.user = new Student();
            Main.identity = 1;
        }
        else
        {
            Main.user = new Teacher();
            Main.identity = 0;
        }
        Main.user.id = username.getText();
        loginbutton.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String password = password_hide.isVisible() == true ? password_hide.getText() : password_show.getText();
                if(!Main.user.checkexit(password))
                {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("登录失败");
                    alert.setHeaderText("用户名或密码错误！（用户名应为12位学号或工号，初始密码为123456）");
                    Stage alertstage = (Stage) alert.getDialogPane().getScene().getWindow();
                    alertstage.getIcons().add(new Image(getClass().getResourceAsStream("attention.png")));
                    alert.show();
                }
                else {
                    URL url = getClass().getResource("initial-view.fxml");
                    Parent root = null;
                    try {
                        root = FXMLLoader.load(url);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Scene scene = new Scene(root, 1000, 600);
                    Stage stage = new Stage();
                    Stage old_stage = (Stage) loginpane.getScene().getWindow();
                    old_stage.close();
                    stage.getIcons().add(new Image(getClass().getResourceAsStream("sign.png")));
                    //stage.initStyle(StageStyle.UNDECORATED);
                    stage.setScene(scene);
                    stage.setTitle("主界面");
                    //stage.showAndWait();
                    stage.show();
                    stage.onCloseRequestProperty().setValue(e -> Platform.exit());
                    stage.setOnCloseRequest(new WindowsCloseEvent(stage));
                }
            }


        });
    }


}
