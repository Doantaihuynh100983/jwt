package com.example.demojwt.dto.request;

public class SignIn {
    private String userName;
    private  String password;

    public SignIn(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public SignIn() {
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

