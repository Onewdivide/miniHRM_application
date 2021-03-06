package com.example.onewdivideslaptop.minihrm_application;

import android.net.Uri;

import com.example.onewdivideslaptop.minihrm_application.responseAndBody.getProjectsResponse;

import java.util.ArrayList;
import java.util.List;

public class staticData {
    public static int id,tabIndex;
    public static String username,token;
    public static Uri profilePic ;
    public static String firstName,lastName;
    public static Long timeStampCompare;
    public static String refreshToken;
    public static List<getProjectsResponse> projectsResponses = new ArrayList<>();

    public staticData(int id,String username,String token) {
        this.id = id;
        this.username = username;
        this.token = token;
    }

    public static List<getProjectsResponse> getProjectsResponses() {
        return projectsResponses;
    }

    public static void setProjectsResponses(List<getProjectsResponse> projectsResponses) {
        staticData.projectsResponses = projectsResponses;
    }

    public static String getRefreshToken() {
        return refreshToken;
    }

    public static void setRefreshToken(String refreshToken) {
        staticData.refreshToken = refreshToken;
    }

    public static Long getTimeStampCompare() {
        return timeStampCompare;
    }

    public static void setTimeStampCompare(Long timeStampCompare) {
        staticData.timeStampCompare = timeStampCompare;
    }

    public static String getFirstName() {
        return firstName;
    }

    public static void setFirstName(String firstName) {
        staticData.firstName = firstName;
    }

    public static String getLastName() {
        return lastName;
    }

    public static void setLastName(String lastName) {
        staticData.lastName = lastName;
    }

    public static void setProfilePic(Uri profilePic) {
        staticData.profilePic = profilePic;
    }

    public static Uri getProfilePic(){return profilePic;}

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        staticData.id = id;
    }

    public static String getUsername() {
        return username;
    }

    public static void setUsername(String username) {
        staticData.username = username;
    }

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        staticData.token = token;
    }
}
