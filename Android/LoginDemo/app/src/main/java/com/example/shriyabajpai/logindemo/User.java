package com.example.shriyabajpai.logindemo;

import com.google.gson.annotations.SerializedName;

public class User {
    public String getUsername() {
        return username;
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @SerializedName("username")
    private String username;

    @SerializedName("password")
    private String password;
}
