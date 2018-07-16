package com.example.onewdivideslaptop.minihrm_application.responseAndBody;

public class calendarEventResponse {
    String id,name,date,task,description,projectId,timeIn,timeOut;
    float totalhours;

    public calendarEventResponse(String id, String name, String date, String task, String description, String projectId, String timeIn, String timeOut, float totalhours) {
        this.id = id;
        this.name = name;
        this.date = date;
        this.task = task;
        this.description = description;
        this.projectId = projectId;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
        this.totalhours = totalhours;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getTimeIn() {
        return timeIn;
    }

    public void setTimeIn(String timeIn) {
        this.timeIn = timeIn;
    }

    public String getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(String timeOut) {
        this.timeOut = timeOut;
    }

    public float getTotalhours() {
        return totalhours;
    }

    public void setTotalhours(int totalhours) {
        this.totalhours = totalhours;
    }
}
