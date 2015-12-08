package com.example.hoang.bookmovietickets.Model;

/**
 * Created by hoang on 11/16/2015.
 */
public class UserModel {

    private String username;
    private String password;
    private int id;

    public UserModel(){}

    public UserModel(String name, String pass){
        this.username = name;
        this.password = pass;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
