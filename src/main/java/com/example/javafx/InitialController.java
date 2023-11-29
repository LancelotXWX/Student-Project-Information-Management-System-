package com.example.javafx;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.event.ActionEvent;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.util.Callback;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Consumer;

public class InitialController implements Initializable{

    @FXML
    private ListView<String> informationlist;

    private ObservableList<String> dataList = FXCollections.observableArrayList();

    static protected ObservableList<Project> data= FXCollections.observableArrayList();
    @FXML
    private Button modifyinfobutton;

    @FXML
    private Button addbutton;

    @FXML
    private Button deletebutton;

    @FXML
    private Button editbutton;

    @FXML
    private Tab infolabel;

    @FXML
    private Button statistics;
    @FXML
    private Button passwordmodifybutton;
    @FXML
    private TableView<Project> projecttable;

    @FXML
    private TableColumn<Project,String>timelist;
    @FXML
    private TableColumn<Project,String> namelist;
    @FXML
    private TableColumn<Project,String> typelist;
    @FXML
    private TableColumn<Project,String> guiderlist;
    @FXML
    private TableColumn<Project,String> apartmentlist;
    @FXML
    private TableColumn<Project,String> discriptionlist;
    @FXML
    private TableColumn<Project,CheckBox> checklist;
    @FXML
    private TableView<Project> projecttable1;

    @FXML
    private TableColumn<Project,String> idlist;
    @FXML
    private TableColumn<Project,String>timelist1;
    @FXML
    private TableColumn<Project,String> namelist1;
    @FXML
    private TableColumn<Project,String> typelist1;
    @FXML
    private TableColumn<Project,String> guiderlist1;
    @FXML
    private TableColumn<Project,String> apartmentlist1;
    @FXML
    private TableColumn<Project,String> discriptionlist1;
    @FXML
    private TableColumn<Project,CheckBox> checklist1;

    @FXML
    void passwordmodifyclick(ActionEvent event) {
        URL url = getClass().getResource("modifypassword-view.fxml");
        Parent root = null;
        try {
            root = FXMLLoader.load(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root,350,250);
        Stage stage = new Stage();
        stage.getIcons().add(new Image(getClass().getResourceAsStream("sign.png")));
        //stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.setTitle("密码修改");
        //stage.showAndWait();
        stage.show();
    }

    @FXML
    void onmodifyclick(ActionEvent event) {
        URL url = getClass().getResource("infomodify-view.fxml");
        Parent root = null;
        try {
            root = FXMLLoader.load(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root,400,500);
        Stage stage = new Stage();
        stage.getIcons().add(new Image(getClass().getResourceAsStream("sign.png")));
        InfomodifyController.laststage = (Stage) modifyinfobutton.getScene().getWindow();
        //stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.setTitle("信息修改");
        //stage.showAndWait();
        stage.show();
    }
    @FXML
    void onaddclick(ActionEvent event) {
        ProjectaddController.mode = "add";
        URL url = getClass().getResource("Projectadd-view.fxml");
        Parent root = null;
        try {
            root = FXMLLoader.load(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root,400,500);
        Stage stage = new Stage();
        stage.getIcons().add(new Image(getClass().getResourceAsStream("sign.png")));
        InfomodifyController.laststage = (Stage) modifyinfobutton.getScene().getWindow();
        //stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.setTitle("项目编辑");
        //stage.showAndWait();
        stage.show();
    }

    @FXML
    void ondeleteclick(ActionEvent event) throws IOException {
        if(Main.identity == 1) {
            //ObservableList<Project> list = projecttable.getItems();
            for (int i = data.size() - 1;i >= 0;i--) {
                if (data.get(i).c.isSelected()) {
                    Main.user.deleteinfo(Main.user.id,new String[]{data.get(i).time,data.get(i).proname,data.get(i).type,data.get(i).guider,data.get(i).apartment,data.get(i).discription});
                    data.remove(i);
                }
            }
        }
        else
        {
            for (int i = data.size() - 1;i >= 0;i--) {
                if (data.get(i).c.isSelected()) {
                    Main.user.deleteinfo(data.get(i).studentid,new String[]{data.get(i).time,data.get(i).proname,data.get(i).type,data.get(i).guider,data.get(i).apartment,data.get(i).discription});
                    data.remove(i);
                }
            }
        }
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("删除成功");
        alert.setHeaderText("所选项目已成功删除！");
        Stage alertstage = (Stage) alert.getDialogPane().getScene().getWindow();
        alertstage.getIcons().add(new Image(getClass().getResourceAsStream("success.png")));
        alert.show();

    }

    private int typesort(Project p1,Project p2,String order)
    {
        String a = p1.type;
        String b = p2.type;
        Map<String,Integer> dict = new HashMap<>();
        dict.put("院级",0);
        dict.put("校级",1);
        dict.put("市级",2);
        dict.put("省级",3);
        dict.put("国家级",4);
        dict.put("国际级",5);
        if(order.equals("down"))
        {
            if(dict.get(a) > dict.get(b))
            {
                return 1;
            }
            else if(dict.get(a) == dict.get(b))
            {
                return 0;
            }
            else
            {
                return -1;
            }
        }
        else
        {
            if(dict.get(a) < dict.get(b))
            {
                return 1;
            }
            else if(dict.get(a) == dict.get(b))
            {
                return 0;
            }
            else
            {
                return -1;
            }
        }
    }

    @FXML
    void oneditclick(ActionEvent event) {
        int count = 0;
        for (int i = 0;i < data.size();i++) {
            if (data.get(i).c.isSelected()) {
                ProjectaddController.selectedproject = data.get(i);
                ProjectaddController.index = i;
                count++;
            }
        }
        if(count == 1) {
            ProjectaddController.mode = "edit";
            URL url = getClass().getResource("Projectadd-view.fxml");
            Parent root = null;
            try {
                root = FXMLLoader.load(url);
            } catch (IOException e) {
                e.printStackTrace();
            }
            Scene scene = new Scene(root, 400, 500);
            Stage stage = new Stage();
            stage.getIcons().add(new Image(getClass().getResourceAsStream("sign.png")));
            InfomodifyController.laststage = (Stage) modifyinfobutton.getScene().getWindow();
            //stage.initStyle(StageStyle.UNDECORATED);
            stage.setScene(scene);
            stage.setTitle("项目编辑");
            //stage.showAndWait();
            stage.show();
        }
        else
        {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.setTitle("错误警告");
            alert.setHeaderText("修改功能只能选择勾选一项！");
            Stage alertstage = (Stage) alert.getDialogPane().getScene().getWindow();
            alertstage.getIcons().add(new Image(getClass().getResourceAsStream("attention.png")));
            alert.show();
        }
    }
    @FXML
    void onstaticticsclick(ActionEvent event) {
        String rootpath = System.getProperty("user.dir") + "/src/main/resources/com/example/javafx/";
        Map<String,Integer> dict = new HashMap<>();
        dict.put("本科一年级",0);
        dict.put("本科二年级",0);
        dict.put("本科三年级",0);
        dict.put("本科四年级",0);
        dict.put("研究生一年级",0);
        dict.put("研究生二年级",0);
        dict.put("研究生三年级",0);
        dict.put("博士生",0);
        String filename = "user_student.txt";
        String path = rootpath + filename;
        ArrayList<String> students = new ArrayList<String>();
        try {
            List<String> allLines = Files.readAllLines(Paths.get(path));
            for (String line : allLines) {
                students.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(int i = 0;i < students.size();i++)
        {
            int num = 0;
            String[] templist = students.get(i).split(",");
            String new_filename = templist[0] + ".txt";
            String new_path = rootpath + new_filename;
            ArrayList<String> tempcontents = new ArrayList<String>();
            try {
                List<String> Lines = Files.readAllLines(Paths.get(new_path));
                for (String line : Lines) {
                    tempcontents.add(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            String grade = tempcontents.get(0).split(",")[1];
            if(tempcontents.size() == 1)
            {
                continue;
            }
            for(int j = 1;j < tempcontents.size();j++)
            {
                if(!(tempcontents.get(j).equals(""))) {
                    num += 1;
                }
            }
            dict.replace(grade,num);
        }
        StatisticsController.m = dict;
        URL url = getClass().getResource("statistics-view.fxml");
        Parent root = null;
        try {
            root = FXMLLoader.load(url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene scene = new Scene(root,200,300);
        Stage stage = new Stage();
        stage.getIcons().add(new Image(getClass().getResourceAsStream("sign.png")));
        stage.setScene(scene);
        stage.setTitle("统计信息");
        //stage.showAndWait();
        stage.show();
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        data.clear();
        if(Main.identity == 1)
        {
            projecttable.setVisible(true);
            projecttable1.setVisible(false);
            infolabel.setText("个人项目信息");
            editbutton.setVisible(true);
            statistics.setVisible(false);
            String[]temp = Main.user.getinfo();
            for(int i = 0;i < temp.length;i++)
            {
                dataList.add(temp[i]);
            }
            informationlist.setItems(dataList);
            ArrayList<Project> dataarray = null;
            try {
                dataarray = Main.user.getproject(Main.identity);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            for(int i = 0;i < dataarray.size();i++)
            {
                data.add(dataarray.get(i));
            }
            timelist.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().time) );
            namelist.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().proname) );
            typelist.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().type) );
            guiderlist.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().guider) );
            apartmentlist.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().apartment) );
            discriptionlist.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().discription) );
            checklist.setCellValueFactory(cellData ->cellData.getValue().c.getCheckBox());
            timelist.setSortable(true);
            namelist.setSortable(true);
            typelist.setSortable(true);
            guiderlist.setSortable(true);
            apartmentlist.setSortable(false);
            discriptionlist.setSortable(false);
            checklist.setSortable(false);
            projecttable.setItems(data);
            projecttable.setSortPolicy(new Callback<TableView<Project>, Boolean>() {
                @Override
                public Boolean call(TableView<Project> projectTableView) {
                    projectTableView.getColumns().forEach(new Consumer<TableColumn<Project, ?>>() {
                        @Override
                        public void accept(TableColumn<Project, ?> projectTableColumn) {
                            if(projectTableColumn.getText().equals("项目类型") && projectTableColumn.getSortType() == TableColumn.SortType.ASCENDING)
                            {
                                projectTableView.getItems().sort(new Comparator<Project>() {
                                    @Override
                                    public int compare(Project o1, Project o2) {
                                        return typesort(o1,o2,"up");
                                    }
                                });
                            }
                            else if(projectTableColumn.getText().equals("项目类型") && projectTableColumn.getSortType() == TableColumn.SortType.DESCENDING)
                            {
                                projectTableView.getItems().sort(new Comparator<Project>() {
                                    @Override
                                    public int compare(Project o1, Project o2) {
                                        return typesort(o1,o2,"down");
                                    }
                                });
                            }
                            else if (projectTableColumn.getText().equals("学生学号") && projectTableColumn.getSortType() == TableColumn.SortType.ASCENDING)
                            {
                                projectTableView.getItems().sort(new Comparator<Project>() {
                                    @Override
                                    public int compare(Project o1, Project o2) {
                                        return o1.studentid.compareTo(o2.studentid);
                                    }
                                });
                            }
                            else if (projectTableColumn.getText().equals("学生学号") && projectTableColumn.getSortType() == TableColumn.SortType.DESCENDING)
                            {
                                projectTableView.getItems().sort(new Comparator<Project>() {
                                    @Override
                                    public int compare(Project o1, Project o2) {
                                        return o2.studentid.compareTo(o1.studentid);
                                    }
                                });
                            }
                            else if (projectTableColumn.getText().equals("获得时间") && projectTableColumn.getSortType() == TableColumn.SortType.ASCENDING)
                            {
                                projectTableView.getItems().sort(new Comparator<Project>() {
                                    @Override
                                    public int compare(Project o1, Project o2) {
                                        return o1.time.compareTo(o2.time);
                                    }
                                });
                            }
                            else if (projectTableColumn.getText().equals("获得时间") && projectTableColumn.getSortType() == TableColumn.SortType.DESCENDING)
                            {
                                projectTableView.getItems().sort(new Comparator<Project>() {
                                    @Override
                                    public int compare(Project o1, Project o2) {
                                        return o2.time.compareTo(o1.time);
                                    }
                                });
                            }
                            else if (projectTableColumn.getText().equals("项目名称") && projectTableColumn.getSortType() == TableColumn.SortType.ASCENDING)
                            {
                                projectTableView.getItems().sort(new Comparator<Project>() {
                                    @Override
                                    public int compare(Project o1, Project o2) {
                                        return o1.proname.compareTo(o2.proname);
                                    }
                                });
                            }
                            else if (projectTableColumn.getText().equals("项目名称") && projectTableColumn.getSortType() == TableColumn.SortType.DESCENDING)
                            {
                                projectTableView.getItems().sort(new Comparator<Project>() {
                                    @Override
                                    public int compare(Project o1, Project o2) {
                                        return o2.proname.compareTo(o1.proname);
                                    }
                                });
                            }
                            else if (projectTableColumn.getText().equals("指导老师") && projectTableColumn.getSortType() == TableColumn.SortType.ASCENDING)
                            {
                                projectTableView.getItems().sort(new Comparator<Project>() {
                                    @Override
                                    public int compare(Project o1, Project o2) {
                                        return o1.guider.compareTo(o2.guider);
                                    }
                                });
                            }
                            else if (projectTableColumn.getText().equals("指导老师") && projectTableColumn.getSortType() == TableColumn.SortType.DESCENDING)
                            {
                                projectTableView.getItems().sort(new Comparator<Project>() {
                                    @Override
                                    public int compare(Project o1, Project o2) {
                                        return o2.guider.compareTo(o1.guider);
                                    }
                                });
                            }

                        }
                    });
                return true;
                }
            });
        }
        else
        {
            infolabel.setText("学生项目信息");
            projecttable1.setVisible(true);
            projecttable.setVisible(false);
            editbutton.setVisible(false);
            statistics.setVisible(true);
            String[]temp = Main.user.getinfo();
            for(int i = 0;i < temp.length;i++)
            {
                dataList.add(temp[i]);
            }
            informationlist.setItems(dataList);
            ArrayList<Project> dataarray = null;
            try {
                dataarray = Main.user.getproject(Main.identity);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            for(int i = 0;i < dataarray.size();i++)
            {
                data.add(dataarray.get(i));
            }
            idlist.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().studentid) );
            timelist1.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().time) );
            namelist1.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().proname) );
            typelist1.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().type) );
            guiderlist1.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().guider) );
            apartmentlist1.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().apartment) );
            discriptionlist1.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().discription) );
            checklist1.setCellValueFactory(cellData ->cellData.getValue().c.getCheckBox());
            idlist.setSortable(true);
            timelist1.setSortable(true);
            namelist1.setSortable(true);
            typelist1.setSortable(true);
            guiderlist1.setSortable(true);
            apartmentlist1.setSortable(false);
            discriptionlist1.setSortable(false);
            checklist1.setSortable(false);
            projecttable1.setItems(data);
        }

    }
}

