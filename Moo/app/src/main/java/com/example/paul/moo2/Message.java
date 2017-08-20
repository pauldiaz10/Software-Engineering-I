package com.example.paul.moo2;


import java.lang.reflect.Array;
import java.util.*;

public class Message {

    private User user;
    private User movie;
    private ArrayList<String> messageList;


    public Message(User user, User movie, ArrayList<String> messageList){
        this.user = user;
        this.movie = movie;
        this.messageList = messageList;

    }

    public void setUser(User user){
        this.user = user;
    }
    public void setMovie(User movie){
        this.movie = movie;
    }
    public void setMessageList(ArrayList<String> messageList){
        this.messageList = messageList;
    }
    public User getUser(){
        return user;
    }
    public User getMovie(){
        return movie;
    }
    public ArrayList<String> getMessageList(){
        return messageList;
    }
}
