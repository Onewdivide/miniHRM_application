package com.example.onewdivideslaptop.minihrm_application.responseAndBody;

public class taskDescriptionEditInRequest {

    int userId,id;
    String date,projectId,timeIn,timeOut,task,description;

    public taskDescriptionEditInRequest(int userId,String date,String projectId,String timeIn,String timeOut,String task,String description,int taskId){
        this.userId = userId;
        this.date = date;
        this.projectId = projectId;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
        this.task = task;
        this.description = description;
        this.id = taskId;

    }

}
