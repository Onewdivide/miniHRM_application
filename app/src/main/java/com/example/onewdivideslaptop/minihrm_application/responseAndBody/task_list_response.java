package com.example.onewdivideslaptop.minihrm_application.responseAndBody;

public class task_list_response {

    public String project_name,task_des,time,taskId;
    public String name,date,description;


    public task_list_response(String project_name, String task_des, String time, String taskId, String name, String date, String description) {
        this.project_name = project_name;
        this.task_des = task_des;
        this.time = time;
        this.taskId = taskId;
        this.name = name;
        this.date = date;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTime() {
        return time;
    }


    public void setTime(String time) {
        this.time = time;
    }

    public String getProject_name() {
        return project_name;
    }

    public void setProject_name(String project_name) {
        this.project_name = project_name;
    }

    public String getTask_des() {
        return task_des;
    }

    public void setTask_des(String task_des) {
        this.task_des = task_des;
    }
}
