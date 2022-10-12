package com.example.demojwt.dto.response;

public class ResponseMesseage {
    private  String message;

    public ResponseMesseage(String message) {
        this.message = message;
    }

    public ResponseMesseage() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
