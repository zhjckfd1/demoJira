package com.example.demojira.dto;

import com.example.demojira.model.Employee;
import com.example.demojira.model.Task;

import java.util.Date;

//lombok ?

//нужны все поля, ничего не добавляем/убираем?
public class CommentDto {
    private Integer taskId;
    private Integer employeeId;
    private String text;
    private Date createdDate;

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

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
}
