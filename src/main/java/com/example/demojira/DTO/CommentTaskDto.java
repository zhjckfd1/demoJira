package com.example.demojira.DTO;

import com.example.demojira.model.Comment;
import com.example.demojira.model.Employee;

import java.util.Date;

public class CommentTaskDto {
    String text;
    Date createdDate;
    Employee employee;

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

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
