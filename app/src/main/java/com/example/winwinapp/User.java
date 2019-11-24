package com.example.winwinapp;

public class User{
    private String fistname,lastname,username,email,password,email1;


    public User(String firstname, String lastname, String username, String email){
    }

    public User(String fistname, String lastname, String username, String email,String password) {
        this.fistname = fistname;
        this.lastname = lastname;
        this.username = username;
        this.email = email;
        this.password = password;
        email1 = email;
        this.email1=getEmail1();


    }

    public User() {

    }

    public String getFistname() {
        return fistname;
    }

    public void setFistname(String fistname) {
        this.fistname = fistname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail1() {
        return email1;
    }
}
