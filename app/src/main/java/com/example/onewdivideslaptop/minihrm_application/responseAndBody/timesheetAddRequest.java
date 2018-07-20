package com.example.onewdivideslaptop.minihrm_application.responseAndBody;

import java.util.ArrayList;
import java.util.List;

public class timesheetAddRequest {
    public List<taskDescriptionInRequest> timesheets;

    public timesheetAddRequest() {
        this.timesheets = new ArrayList<>();
    }

    public void add(int userId, String date, String projectId, String timeIn, String timeOut, String task, String description){
        timesheets.add(new taskDescriptionInRequest(userId,date,projectId,timeIn,timeOut,task,description));
    }

}
