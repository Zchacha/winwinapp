package com.example.winwinapp;

public class User {
    public String fistname,lastname,username,email;

    public User(String firstname, String lastname, String username, String email, String password){
    }

    public User(String fistname, String lastname, String username, String email) {
        this.fistname = fistname;
        this.lastname = lastname;
        this.username = username;
        this.email = email;
    }
}
