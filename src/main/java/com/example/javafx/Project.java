package com.example.javafx;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class Project {
    protected String time;
    protected String proname;
    protected String type;
    protected String guider;
    protected String apartment;
    protected String discription;
    protected String studentid;
    protected Checksign c = new Checksign();

    public Project()
    {

    }
    public Project(String content)
    {
        System.out.println(content);
        String[] temp = content.split(",");
        this.time = temp[0];
        this.proname = temp[1];
        this.type = temp[2];
        this.guider = temp[3];
        this.apartment = temp[4];
        this.discription = temp[5];
    }
    public Project(String id,String content)
    {
        String[] temp = content.split(",");
        this.studentid = id;
        this.time = temp[0];
        this.proname = temp[1];
        this.type = temp[2];
        this.guider = temp[3];
        this.apartment = temp[4];
        this.discription = temp[5];
    }


}
