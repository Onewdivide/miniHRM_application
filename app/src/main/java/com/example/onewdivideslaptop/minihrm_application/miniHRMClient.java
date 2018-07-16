package com.example.onewdivideslaptop.minihrm_application;

import com.example.onewdivideslaptop.minihrm_application.responseAndBody.calendarEventResponse;
import com.example.onewdivideslaptop.minihrm_application.responseAndBody.loginResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface miniHRMClient {
    @FormUrlEncoded
    @POST("/auth/login")
    Call<loginResponse>  login(
            @Field("username") String username,
            @Field("password") String password
    );


    @GET("/api/timesheets")
    Call<List<calendarEventResponse>> viewEvent(
            @Header("Content-Type") String form,
            @Header("Authorization") String token,
            @Query("year") int year,
            @Query("month") int month,
            @Query("userId") int userId
    );

}
