package com.example.winwinapp;

public class User {
    public String fistname,lastname,username,email,password;

    public User(String firstname, String lastname, String username, String email){
    }

    public User(String fistname, String lastname, String username, String email,String password) {
        this.fistname = fistname;
        this.lastname = lastname;
        this.username = username;
        this.email = email;
        this.password = password;
    }
}
