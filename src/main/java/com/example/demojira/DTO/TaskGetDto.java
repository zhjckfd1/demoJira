package com.example.demojira.DTO;

import com.example.demojira.model.Employee;
import com.example.demojira.model.TaskStatus;

import javax.xml.crypto.Data;
import java.util.Date;

public class TaskGetDto {
    TaskStatus status;
    Employee employee;
    String title;
    String description;
    Date registeredDate;

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
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

    public Date getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(Date registeredDate) {
        this.registeredDate = registeredDate;
    }
}
