package com.example.javafx;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;

import java.io.*;

import javafx.event.ActionEvent;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class InfomodifyController implements Initializable{
    private String name;
    private String grade;
    private String school;
    private String major;

    static  Stage laststage;

    @FXML
    private Button confirmbutton;

    @FXML
    private Label idlabel;
    @FXML
    private TextField gradefield;

    @FXML
    private Label gradelabel;

    @FXML
    private Label majorlabel;

    @FXML
    private TextField majorfield;

    @FXML
    private TextField namefield;

    @FXML
    private TextField schoolfield;

    @FXML
    void onconfirmclick(ActionEvent event) throws IOException {
        if(Main.identity == 1) {
            name = namefield.getText();
            grade = gradefield.getText();
            school = schoolfield.getText();
            major = majorfield.getText();
            Main.user.modifyinfo(new String[]{name,grade,school,major});
        }
        else
        {
            name = namefield.getText();
            school = schoolfield.getText();
            Main.user.modifyinfo(new String[]{name,school});
        }
        URL url = getClass().getResource("initial-view.fxml");
        Parent root = null;
        try {
            root = FXMLLoader.load(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root,1000,600);
        Stage stage = new Stage();
        Stage old_stage = (Stage) idlabel.getScene().getWindow();
        old_stage.close();
        laststage.close();
        stage.getIcons().add(new Image(getClass().getResourceAsStream("sign.png")));
        //stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.setTitle("主界面");
        //stage.showAndWait();
        stage.show();
        stage.setOnCloseRequest(new WindowsCloseEvent(stage));
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("修改成功");
        alert.setHeaderText("个人信息修改成功！");
        Stage alertstage = (Stage) alert.getDialogPane().getScene().getWindow();
        alertstage.getIcons().add(new Image(getClass().getResourceAsStream("success.png")));
        alert.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String[] temp = Main.user.getinfo();
        if(Main.identity == 1) {
            gradefield.setVisible(true);
            majorfield.setVisible(true);
            idlabel.setText(Main.user.id + "（不可修改）");
            namefield.setText(Main.user.name);
            gradefield.setText(Main.user.grade);
            schoolfield.setText(Main.user.school);
            majorfield.setText(Main.user.major);
        }
        else
        {
            idlabel.setText(Main.user.id + "（不可修改）");
            namefield.setText(Main.user.name);
            schoolfield.setText(Main.user.school);
            gradefield.setVisible(false);
            majorfield.setVisible(false);
            gradelabel.setVisible(false);
            majorlabel.setVisible(false);

        }



    }
}
