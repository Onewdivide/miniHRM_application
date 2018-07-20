package com.example.onewdivideslaptop.minihrm_application;

import com.example.onewdivideslaptop.minihrm_application.responseAndBody.addTaskResponse;
import com.example.onewdivideslaptop.minihrm_application.responseAndBody.calendarEventResponse;
import com.example.onewdivideslaptop.minihrm_application.responseAndBody.deleteRequestBody;
import com.example.onewdivideslaptop.minihrm_application.responseAndBody.getProjectsResponse;
import com.example.onewdivideslaptop.minihrm_application.responseAndBody.loginResponse;
import com.example.onewdivideslaptop.minihrm_application.responseAndBody.taskDescriptionInRequest;
import com.example.onewdivideslaptop.minihrm_application.responseAndBody.timesheetAddRequest;
import com.example.onewdivideslaptop.minihrm_application.responseAndBody.timesheetEditRequest;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
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

    @FormUrlEncoded
    @POST("/auth/token")
    Call<loginResponse> newToken(
            @Field("refreshToken") String refreshToken
    );

    @GET("/api/projects")
    Call<List<getProjectsResponse>> getProject(
            @Header("Content-Type") String form,
            @Header("Authorization") String token
    );

    @POST("/api/timesheets")
    Call<List<addTaskResponse>> addTaskTocalendar(
            @Header("Content-Type") String form,
            @Header("Authorization") String token,
            @Body timesheetAddRequest timesheetAddRequest
    );

    @PUT("/api/timesheets")
    Call<Void> editTask(
            @Header("Content-Type") String form,
            @Header("Authorization") String token,
            @Body timesheetEditRequest timesheeteditRequest
    );

    @HTTP(method = "DELETE", path = "/api/timesheets", hasBody = true)
    Call<Void> deleteTask(
            @Header("Content-Type") String form,
            @Header("Authorization") String token,
            @Body deleteRequestBody deleteRequestBody
    );

}
