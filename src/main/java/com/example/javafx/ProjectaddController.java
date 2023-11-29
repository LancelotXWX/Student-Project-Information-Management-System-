package com.example.javafx;

import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.function.UnaryOperator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class ProjectaddController implements Initializable {
    protected static String mode;
    protected static Project selectedproject;
    protected static int index;
    protected Project newproject;
    @FXML
    private TextField apartmentfield;

    @FXML
    private TextField discriptionfield;

    @FXML
    private TextField guiderfield;

    @FXML
    private TextField idfield;

    @FXML
    private Label idlabel;

    @FXML
    private TextField namefield;

    @FXML
    private Button projectaddbutton;

    @FXML
    private Button projecteditbutton;

    @FXML
    private TextField timefield;

    @FXML
    private ComboBox<String> typebox;

    @FXML
    void onprojectaddclick(ActionEvent event) throws IOException {
        String time = timefield.getText();
        String datePattern = "\\d{4}-\\d{2}-\\d{2}";
        Pattern pattern = Pattern.compile(datePattern);
        Matcher match = pattern.matcher(time);
        if(!match.matches())
        {
            System.out.println("日期本身就错了");
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("错误警告");
            alert.setHeaderText("日期格式有误！请按yyyy-MM-dd的正确格式输入。");
            Stage alertstage = (Stage) alert.getDialogPane().getScene().getWindow();
            alertstage.getIcons().add(new Image(getClass().getResourceAsStream("attention.png")));
            alert.show();
        }

        else
        {
            if(checkdate(time)){
                if(!namefield.getText().trim().equals("")) {
                    String name = namefield.getText();
                    String type = typebox.getValue().trim().equals("") ? " " : typebox.getValue();
                    String guider = guiderfield.getText().trim().equals("") ? " " : guiderfield.getText();
                    String apartment = apartmentfield.getText().trim().equals("") ? " " : apartmentfield.getText();
                    String discription = discriptionfield.getText().trim().equals("") ? " " : discriptionfield.getText();
                    if (Main.identity == 1) {
                        newproject = new Project(time + ',' + name + ',' + type + ',' + guider + ',' + apartment + "," + discription);
                        InitialController.data.add(newproject);
                        Main.user.addinfo(Main.user.id, new String[]{time, name, type, guider, apartment, discription});
                    } else {
                        String id = idfield.getText();
                        newproject = new Project(id, time + ',' + name + ',' + type + ',' + guider + ',' + apartment + "," + discription);
                        InitialController.data.add(newproject);
                        Main.user.addinfo(id, new String[]{time, name, type, guider, apartment, discription});
                    }
                    Stage thisstage = (Stage) timefield.getScene().getWindow();
                    thisstage.close();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("添加成功");
                    alert.setHeaderText("已成功添加项目！");
                    Stage alertstage = (Stage) alert.getDialogPane().getScene().getWindow();
                    alertstage.getIcons().add(new Image(getClass().getResourceAsStream("success.png")));
                    alert.show();
                }
                else
                {
                    Alert alert=new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("错误警告");
                    alert.setHeaderText("项目名称不能为空！");
                    Stage alertstage = (Stage) alert.getDialogPane().getScene().getWindow();
                    alertstage.getIcons().add(new Image(getClass().getResourceAsStream("attention.png")));
                    alert.show();
                }
            }
            else{
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("错误警告");
                alert.setHeaderText("日期输入有误！请输入有效的日期。");
                Stage alertstage = (Stage) alert.getDialogPane().getScene().getWindow();
                alertstage.getIcons().add(new Image(getClass().getResourceAsStream("attention.png")));
                alert.show();
            }
        }

    }

    @FXML
    void onprojecteditclick(ActionEvent event) throws IOException {
        String time = timefield.getText();
        String datePattern = "\\d{4}-\\d{2}-\\d{2}";
        Pattern pattern = Pattern.compile(datePattern);
        Matcher match = pattern.matcher(time);
        if(!match.matches())
        {
            System.out.println(time);
            System.out.println("日期本身就错了");
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("错误警告");
            alert.setHeaderText("日期格式有误！请按yyyy-MM-dd的正确格式输入。");
            Stage alertstage = (Stage) alert.getDialogPane().getScene().getWindow();
            alertstage.getIcons().add(new Image(getClass().getResourceAsStream("attention.png")));
            alert.show();
        }
        else {
            if(checkdate(time)) {
                if(!namefield.getText().trim().equals("")) {
                    String name = namefield.getText();
                    String type = typebox.getValue().trim().equals("") ? " " : typebox.getValue();
                    String guider = guiderfield.getText().trim().equals("") ? " " : guiderfield.getText();
                    String apartment = apartmentfield.getText().trim().equals("") ? " " : apartmentfield.getText();
                    String discription = discriptionfield.getText().trim().equals("") ? " " : discriptionfield.getText();
                    if (Main.identity == 1) {
                        newproject = new Project(time + ',' + name + ',' + type + ',' + guider + ',' + apartment + "," + discription);
                        InitialController.data.set(index, newproject);
                        Main.user.editinfo(Main.user.id, new String[]{time, name, type, guider, apartment, discription}, new String[]{selectedproject.time, selectedproject.proname, selectedproject.type, selectedproject.guider, selectedproject.apartment, selectedproject.discription});
                    } else {
                        String id = idfield.getText();
                        newproject = new Project(id, time + ',' + name + ',' + type + ',' + guider + ',' + apartment + "," + discription);
                        InitialController.data.set(index, newproject);
                        Main.user.editinfo(id, new String[]{time, name, type, guider, apartment, discription}, new String[]{selectedproject.time, selectedproject.proname, selectedproject.type, selectedproject.guider, selectedproject.apartment, selectedproject.discription});
                    }
                    Stage thisstage = (Stage) timefield.getScene().getWindow();
                    thisstage.close();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("修改成功");
                    alert.setHeaderText("项目信息修改成功！");
                    Stage alertstage = (Stage) alert.getDialogPane().getScene().getWindow();
                    alertstage.getIcons().add(new Image(getClass().getResourceAsStream("success.png")));
                    alert.show();
                }
                else
                {
                    Alert alert=new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("错误警告");
                    alert.setHeaderText("项目名称不能为空！");
                    Stage alertstage = (Stage) alert.getDialogPane().getScene().getWindow();
                    alertstage.getIcons().add(new Image(getClass().getResourceAsStream("attention.png")));
                    alert.show();
                }
            }
            else
            {
                Alert alert=new Alert(Alert.AlertType.ERROR);
                alert.setTitle("错误警告");
                alert.setHeaderText("日期输入有误！请输入有效的日期。");
                Stage alertstage = (Stage) alert.getDialogPane().getScene().getWindow();
                alertstage.getIcons().add(new Image(getClass().getResourceAsStream("attention.png")));
                alert.show();
            }
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(Main.identity == 1)
        {
            idlabel.setVisible(false);
            idfield.setVisible(false);
            if(mode.equals("add"))
            {
                projecteditbutton.setVisible(false);
                projectaddbutton.setVisible(true);
            }
            else
            {
                projecteditbutton.setVisible(true);
                projectaddbutton.setVisible(false);
                typebox.setValue(selectedproject.type);
                timefield.setText(selectedproject.time);
                namefield.setText(selectedproject.proname);
                guiderfield.setText(selectedproject.guider);
                apartmentfield.setText(selectedproject.apartment);
                discriptionfield.setText(selectedproject.discription);
            }
        }
        else
        {
            idlabel.setVisible(true);
            idfield.setVisible(true);
            if(mode.equals("add"))
            {
                projecteditbutton.setVisible(false);
                projectaddbutton.setVisible(true);
            }
        }

    }
    private static boolean checkdate(String t)
    {
        String[] d = t.split("-");
        if(Integer.parseInt(d[0]) < 1949)
        {
            return false;
        }
        String date = d[0] + '-' +  String.valueOf(Integer.parseInt(d[1])) + '-' + String.valueOf(Integer.parseInt(d[2]));
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        df.setLenient(false);
        try {
            df.parse(date);
        } catch (ParseException e) {
            return false;
        }
        return true;
    }
}






