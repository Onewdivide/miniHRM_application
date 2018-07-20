package com.example.onewdivideslaptop.minihrm_application.responseAndBody;

import java.util.ArrayList;
import java.util.List;

public class timesheetEditRequest {
    public taskDescriptionEditInRequest timesheet;

    public timesheetEditRequest(int userId, String date, String projectId, String timeIn, String timeOut, String task, String description,int taskId) {
        this.timesheet = new taskDescriptionEditInRequest(userId,date,projectId,timeIn,timeOut,task,description,taskId);
    }

//    public void add(int userId, String date, String projectId, String timeIn, String timeOut, String task, String description,int taskId){
//        timesheet.add(new taskDescriptionEditInRequest(userId,date,projectId,timeIn,timeOut,task,description,taskId));
//    }

}
