package com.example.onewdivideslaptop.minihrm_application;

import com.example.onewdivideslaptop.minihrm_application.responseAndBody.loginResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface miniHRMClient {
    @FormUrlEncoded
    @POST("/auth/login")
    Call<loginResponse>  login(
            @Field("username") String username,
            @Field("password") String password
    );
}
