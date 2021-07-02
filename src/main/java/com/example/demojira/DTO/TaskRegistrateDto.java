package com.example.demojira.DTO;

import com.example.demojira.model.Employee;

public class TaskRegistrateDto {
    Employee employee;
    String title;
    String description;

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
}
