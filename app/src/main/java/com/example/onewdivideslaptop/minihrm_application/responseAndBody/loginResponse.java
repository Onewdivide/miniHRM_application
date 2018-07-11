package com.example.onewdivideslaptop.minihrm_application.responseAndBody;

public class loginResponse {
    int id;
    String username,token;

    public loginResponse(int id, String username, String token) {
        this.id = id;
        this.username = username;
        this.token = token;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getToken() {
        return token;
    }
}
