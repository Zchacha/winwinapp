package com.example.winwinapp;


public class Userinformation {

    public String name;
    public String surname;
    public String username;

    public Userinformation(){
    }

    public Userinformation(String name,String surname, String username){
        this.name = name;
        this.surname = surname;
        this.username = username;
    }
    public String getUserName() {
        return name;
    }
    public String getUserSurname() {
        return surname;
    }
    public String getUsername() {
        return username;
    }
}