package com.example.javafx;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ModifypasswordController {

    @FXML
    private Button confirmnewpasswordbutton;

    @FXML
    private TextField newpasswordfield;

    @FXML
    private TextField newpasswordfieldcheck;
    @FXML
    void onconfirmpasswordclick(ActionEvent event) {
        String newpassword = newpasswordfield.getText();
        String check = newpasswordfieldcheck.getText();
        if(newpassword.equals(check))
        {
            String rootpath = System.getProperty("user.dir") + "/src/main/resources/com/example/javafx/";
            String filename = Main.identity == 1 ? "user_student.txt" : "user_teacher.txt";
            String path = rootpath + filename;
            ArrayList<String> allLines = new ArrayList<String>();
            try {
                List<String> contents = Files.readAllLines(Paths.get(path), Charset.forName("UTF-8"));
                for (String line : contents) {
                    allLines.add(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            for(int i = 0;i < allLines.size();i++)
            {
                if(allLines.get(i).contains(Main.user.id))
                {
                    allLines.set(i,Main.user.id + ',' + newpassword);
                }
            }
            BufferedWriter bw = null;
            try {
                bw = new BufferedWriter(new FileWriter(path));
                for (int j = 0; j < allLines.size(); j++) {
                    bw.write(allLines.get(j));
                    bw.newLine();
                    bw.flush();
                }
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            Stage thisstage = (Stage) newpasswordfield.getScene().getWindow();
            thisstage.close();
            Alert alert=new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("修改成功");
            alert.setHeaderText("密码修改成功！");
            Stage alertstage = (Stage) alert.getDialogPane().getScene().getWindow();
            alertstage.getIcons().add(new Image(getClass().getResourceAsStream("success.png")));
            alert.show();
        }
        else
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("密码不一致");
            alert.setHeaderText("请核对输入的密码！");
            Stage alertstage = (Stage) alert.getDialogPane().getScene().getWindow();
            alertstage.getIcons().add(new Image(getClass().getResourceAsStream("attention.png")));
            alert.show();
        }

    }

}
