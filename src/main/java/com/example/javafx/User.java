package com.example.javafx;

import javafx.scene.control.CheckBox;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public abstract class User {
    protected String id;
    protected String name;
    protected String school;
    protected String grade;
    protected String major;

    abstract String[] getinfo(); //读取个人信息
    abstract boolean checkexit(String password); //判断用户名和密码是否正确
    void deleteinfo(String studentid,String[] info){
        String rootpath = System.getProperty("user.dir") + "/src/main/resources/com/example/javafx/";
        String filename = studentid + ".txt";
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
        StringBuffer sb = new StringBuffer();
        for (String str : info) {
            sb.append(str).append(",");
        }
        String finalstr = sb.deleteCharAt(sb.length() - 1).toString();
        int del = 0;
        for(int i = 0;i < allLines.size();i++)
        {
            if(allLines.get(i).equals(finalstr))
            {
                del = i;
                break;
            }
        }
        allLines.remove(del);
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
    }

    void editinfo(String studentid, String[] info,String[] oldinfo){
        String rootpath = System.getProperty("user.dir") + "/src/main/resources/com/example/javafx/";
        String filename = studentid + ".txt";
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
        StringBuffer sb = new StringBuffer();
        for (String str : info) {
            sb.append(str).append(",");
        }
        StringBuffer old_sb = new StringBuffer();
        for (String str : oldinfo) {
            old_sb.append(str).append(",");
        }
        String finalstr = sb.deleteCharAt(sb.length() - 1).toString();
        String oldstr = old_sb.deleteCharAt(old_sb.length() - 1).toString();
        for(int i = 0;i < allLines.size();i++)
        {
            if(allLines.get(i).equals(oldstr))
            {
                allLines.set(i,finalstr);
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
    }
    void addinfo(String studentid,String[] info){
        String rootpath = System.getProperty("user.dir") + "/src/main/resources/com/example/javafx/";
        String filename = studentid + ".txt";
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
        StringBuffer sb = new StringBuffer();
        for (String str : info) {
            sb.append(str).append(",");
        }
        String finalstr = sb.deleteCharAt(sb.length() - 1).toString();
        allLines.add(finalstr);
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
    }
    ArrayList<Project> getproject(int identify) throws IOException {
        String rootpath = System.getProperty("user.dir") + "/src/main/resources/com/example/javafx/";
        if(identify == 1)
        {
            String filename = this.id + ".txt";
            String path = rootpath + filename;
            ArrayList<String> contents = new ArrayList<String>();
            try {
                List<String> allLines = Files.readAllLines(Paths.get(path));
                for (String line : allLines) {
                    contents.add(line);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            ArrayList<Project> result = new ArrayList<Project>();
            if(contents.size() == 1)
            {
                return result;
            }
            else {

                for (int i = 1; i < contents.size(); i++) {
                    if(!(contents.get(i).equals(""))) {
                        result.add(new Project(contents.get(i)));
                    }
                }
                return result;
            }
        }
        else
        {
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
            ArrayList<Project> result = new ArrayList<Project>();
            for(int i = 0;i < students.size();i++)
            {
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
                if(tempcontents.size() == 1)
                {
                    continue;
                }
                for(int j = 1;j < tempcontents.size();j++)
                {
                    if(!(tempcontents.get(j).equals(""))) {
                        result.add(new Project(templist[0], tempcontents.get(j)));
                    }
                }
            }
            return result;
        }
    }
    void modifyinfo(String[] s){
        String rootpath = System.getProperty("user.dir") + "/src/main/resources/com/example/javafx/";
        String filename = this.id + ".txt";
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
        StringBuffer sb = new StringBuffer();
        for (String str : s) {
            sb.append(str).append(",");
        }
        String finalstr = sb.deleteCharAt(sb.length() - 1).toString();
        allLines.set(0,finalstr);
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

    }
}
class Teacher extends User{
    @Override
    String[] getinfo() {
        String rootpath = System.getProperty("user.dir") + "/src/main/resources/com/example/javafx/";
        String filename = this.id + ".txt";
        String path = rootpath + filename;
        RandomAccessFile randomAccessFile = null;
        try {
            randomAccessFile = new RandomAccessFile(new File(path), "r");
            randomAccessFile.seek(0);
            String info = randomAccessFile.readLine();
            info = new String(info.getBytes("ISO-8859-1"), "utf-8");
            String[] infolist = info.split(",");
            this.name = infolist[0];
            this.school = infolist[1];
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String[] result = new String[]{"姓名： " + name, "", "工号： " + this.id, "", "学院： " + school};
        return result;
    }

    @Override
    boolean checkexit(String password) {
        String rootpath = System.getProperty("user.dir") + "/src/main/resources/com/example/javafx/";
        String filename = "user_teacher.txt";
        String path = rootpath + filename;
        File file = new File(path);
        //记录文件长度
        Long fileLength = file.length();
        //记录读出来的文件的内容
        byte[] fileContext = new byte[fileLength.intValue()];
        FileInputStream in = null;
        PrintWriter out = null;
        try {
            in = new FileInputStream(path);
            //读出文件全部内容(内容和文件中的格式一致,含换行)
            in.read(fileContext);
            // 避免出现中文乱码
            String wholestr = new String(fileContext, "utf-8");
            String[] userlist = wholestr.split("\r\n");
            for(int i = 0;i < userlist.length;i++)
            {
                if(userlist[i].contains(this.id))
                {
                    String truepassword = userlist[i].split(",")[1];
                    if(truepassword.equals(password))
                    {
                        return true;
                    }
                    else
                    {
                        return false;
                    }
                }
            }
            if(!password.equals("123456"))
            {
                return false;
            }
            String finalstr = this.id + "," + "123456" + "\r\n";
            wholestr = wholestr + finalstr;
            //System.out.println(wholestr);
            out = new PrintWriter(path);
            out.write(wholestr);
            out.flush();
            out.close();
            String txtname = rootpath + this.id + ".txt";
            FileWriter fw = null;
            File txtfile = new File(txtname);
            if (!txtfile.exists())
            {
                txtfile.createNewFile();
            }
            fw = new FileWriter(txtname);
            BufferedWriter bw=new BufferedWriter(fw);
            bw.write(" , \r\n");
            bw.close();
            return true;

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}

class Student extends User {
    private CheckBox c = new CheckBox();
    @Override
    String[] getinfo() {
        String rootpath = System.getProperty("user.dir") + "/src/main/resources/com/example/javafx/";
        String filename = this.id + ".txt";
        //String path = getClass().getResourceAsStream(filename);
        //File file = new File(path);
        String path = rootpath + filename;
        RandomAccessFile randomAccessFile = null;
        try {
            randomAccessFile = new RandomAccessFile(new File(path), "r");
            randomAccessFile.seek(0);
            String info = randomAccessFile.readLine();
            info = new String(info.getBytes("ISO-8859-1"), "utf-8");
            String[] infolist = info.split(",");
            this.name = infolist[0];
            this.grade = infolist[1];
            this.school = infolist[2];
            this.major = infolist[3];

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String[] result = new String[]{"姓名： " + name, "", "学号： " + this.id, "", "学院： " + school, "","年级： " + grade,  "", "专业： " + major};
        return result;
    }

    @Override
    boolean checkexit(String password) {
        Pattern pattern = Pattern.compile("^\\d{12}$");
        if(!pattern.matcher(Main.user.id).matches())
        {
            return false;
        }
        String rootpath = System.getProperty("user.dir") + "/src/main/resources/com/example/javafx/";
        String filename = "user_student.txt";
        String path = rootpath + filename;
        File file = new File(path);
        //记录文件长度
        Long fileLength = file.length();
        //记录读出来的文件的内容
        byte[] fileContext = new byte[fileLength.intValue()];
        FileInputStream in = null;
        PrintWriter out = null;
        try {
            in = new FileInputStream(path);
            //读出文件全部内容(内容和文件中的格式一致,含换行)
            in.read(fileContext);
            // 避免出现中文乱码
            String wholestr = new String(fileContext, "utf-8");
            String[] userlist = wholestr.split("\r\n");
            System.out.println(this.id);
            for(int i = 0;i < userlist.length;i++)
            {
                if(userlist[i].contains(this.id))
                {
                    String truepassword = userlist[i].split(",")[1];
                    System.out.println(truepassword);
                    if(truepassword.equals(password))
                    {
                        System.out.println("密码对了");
                        return true;
                    }
                    else
                    {
                        System.out.println("密码错了");
                        return false;
                    }
                }
            }
            if(!password.equals("123456"))
            {
                return false;
            }
            String finalstr = this.id + "," + "123456" + "\r\n";
            wholestr = wholestr + finalstr;
            //System.out.println(wholestr);
            out = new PrintWriter(path);
            out.write(wholestr);
            out.flush();
            out.close();
            String txtname = rootpath + this.id + ".txt";
            FileWriter fw = null;
            File txtfile = new File(txtname);
            if (!txtfile.exists())
            {
                txtfile.createNewFile();
            }
            fw = new FileWriter(txtname);
            BufferedWriter bw=new BufferedWriter(fw);
            bw.write(" , , , \r\n");
            bw.close();
            return true;

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}


