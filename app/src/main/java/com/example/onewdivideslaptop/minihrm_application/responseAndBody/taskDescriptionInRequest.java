package com.example.onewdivideslaptop.minihrm_application.responseAndBody;

public class taskDescriptionInRequest {
    int userId;
    String date,projectId,timeIn,timeOut,task,description;

    public taskDescriptionInRequest(int userId,String date,String projectId,String timeIn,String timeOut,String task,String description){
        this.userId = userId;
        this.date = date;
        this.projectId = projectId;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
        this.task = task;
        this.description = description;
    }
}
