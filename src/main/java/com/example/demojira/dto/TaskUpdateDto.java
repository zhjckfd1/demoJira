package com.example.demojira.dto;

import com.example.demojira.model.Employee;
import com.example.demojira.model.TaskStatus;

import java.util.Date;

public class TaskUpdateDto {
    Integer taskStatusId;
    Integer employeeId;
    String title;
    String description;

    public Integer getTaskStatusId() {
        return taskStatusId;
    }

    public void setTaskStatusId(Integer taskStatusId) {
        this.taskStatusId = taskStatusId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
