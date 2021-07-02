package com.example.demojira.DTO;

import com.example.demojira.model.Comment;
import com.example.demojira.model.Employee;
import com.example.demojira.model.Task;

import java.util.Date;

//lombok ?

//нужны все поля, ничего не добавляем/убираем?
public class CommentDto {
    private Task task;    //только id?
    private Employee employee;      //только id?
    private String text;
    private Date createdDate;



    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
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
