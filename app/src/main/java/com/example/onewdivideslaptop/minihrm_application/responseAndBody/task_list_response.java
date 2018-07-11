package com.example.onewdivideslaptop.minihrm_application.responseAndBody;

public class task_list_response {

    public String project_name,task_des,time;

    public task_list_response(String project_name, String task_des,String time) {
        this.project_name = project_name;
        this.task_des = task_des;
        this.time = time;
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
