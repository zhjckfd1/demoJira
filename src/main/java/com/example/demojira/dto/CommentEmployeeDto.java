package com.example.demojira.dto;

import com.example.demojira.model.Task;

import java.util.Date;

public class CommentEmployeeDto {
    String text;
    Date createdDate;
    Integer taskId;

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

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }
}
