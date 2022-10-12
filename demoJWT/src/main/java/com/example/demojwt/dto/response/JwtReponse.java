package com.example.demojwt.dto.response;

public class JwtReponse {
    String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public JwtReponse() {
    }

    public JwtReponse(String token) {
        this.token = token;
    }
}

