package com.example.onewdivideslaptop.minihrm_application;

import android.net.Uri;

public class staticData {
    public static int id;
    public static String username,token;
    public static Uri profilePic ;
    public static String firstName,lastName;

    public staticData(int id,String username,String token) {
        this.id = id;
        this.username = username;
        this.token = token;
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
