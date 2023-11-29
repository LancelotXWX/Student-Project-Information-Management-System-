package com.example.javafx;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;

import java.net.URL;
import java.util.Comparator;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;


public class StatisticsController implements Initializable {
    @FXML
    private ListView<String> stalist;
    private ObservableList<String> staarray = FXCollections.observableArrayList();
    protected static Map m;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        m.forEach((key,value)->{
            if((int)value != 0)
            staarray.add(key + ":  " + value);
        });
        staarray.sort(Comparator.naturalOrder());
        stalist.setItems(staarray);
    }
}

