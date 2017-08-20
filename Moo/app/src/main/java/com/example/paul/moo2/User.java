package com.example.paul.moo2;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

public class User implements Serializable{
    private String fullName;
    private String userName;
    private int mooPoints;
    private String email;
    private GregorianCalendar birthday;
    private int age;
    private String password;
    private String sex;
    private List<String> postr;
    List<ArrayList<String>> cast;

    public User(){
        fullName = "";
        userName = "";
        mooPoints = 0;
        email = "";
        age = 0;
        password = "";
        sex = "";
        postr = new ArrayList<>();
        cast = new ArrayList<>();
    }

    public User(String fullName, String userName, List<String> postr, List<ArrayList<String>> cast, int points, int age){
        this.fullName = fullName;
        this.userName = userName;
        this.mooPoints = points;
        this.email = "";
        this.age = age;
        this.password = "";
        this.sex = "";
        this.postr = postr;
        this.cast = cast;

    }
    public User(String fullName, String userName, String password, int age, String email, String sex, int moo){
        this.fullName = fullName;
        this.password = password;
        this.userName  = userName;
        this.age = age;
        this.email = email;
        this.mooPoints = moo;
        this.sex = sex;
        this.postr = new ArrayList<>();
        this.cast = new ArrayList<>();
    }

    public void setPostr(ArrayList<String> postr){
        this.postr = postr;
    }

    public List<String> getPostr(){
        return postr;
    }

    public void setCast(ArrayList<ArrayList<String>> cast){
        this.cast = cast;
    }

    public List<ArrayList<String>> getCast(){
        return cast;
    }

    public String getSex(){ return sex; }

    public void setSex(String sex){ this.sex = sex; }

    public String getFullName(){
        return fullName;
    }

    public String getEmail(){ return email; }

    public void setEmail(String newEmail){ this.email = newEmail; }

    public void setFullName(String fullName){
        this.fullName = fullName;
    }

    public String getUserName(){
        return userName;
    }

    public void setUserName(String userName){
        this.userName = userName;
    }

    public int getMooPoints(){
        return mooPoints;
    }

    public int getAge(){
        return age;
    }

    public void setAge(int age){
        this.age = age;
    }

    public void setMooPoints(int mooPoints){
        this.mooPoints = mooPoints;
    }

    public GregorianCalendar getBirthday(){
        return birthday;
    }

    public void setBirthday(GregorianCalendar birthday){
        this.birthday = birthday;
    }

    public String getPassword(){
        return password;
    }

    public void setPassword(String password){
        this.password = password;
    }
}
