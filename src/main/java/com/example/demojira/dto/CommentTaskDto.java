package com.example.demojira.dto;

import com.example.demojira.model.Employee;

import java.util.Date;

public class CommentTaskDto {
    String text;
    Date createdDate;
    Integer employeeId;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }
}
