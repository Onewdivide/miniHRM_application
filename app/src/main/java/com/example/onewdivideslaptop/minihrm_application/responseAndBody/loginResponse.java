package com.example.onewdivideslaptop.minihrm_application.responseAndBody;

public class loginResponse {
    int id;
    String username,accessToken,refreshToken;

    public loginResponse(int id, String username, String accessToken, String refreshToken) {
        this.id = id;
        this.username = username;
        this.accessToken = accessToken;
        this.refreshToken = refreshToken;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getToken() {
        return accessToken;
    }

    public String getRefreshToken() {
        return refreshToken;
    }
}
