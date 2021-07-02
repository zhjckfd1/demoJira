package com.example.demojira.DTO;

import com.example.demojira.model.Comment;
import com.example.demojira.model.Employee;
import com.example.demojira.model.Task;

import java.util.Date;

//lombok ?

//нужны все поля, ничего не добавляем/убираем?
public class CommentDto {
    Integer id;
    Task task;    //только id?
    Employee employee;      //только id?
    String text;
    Date createdDate;

    //toString?

    /*
    public CommentDto mapToCommentDto(Comment entity){
        CommentDto commentDto = new CommentDto();
        commentDto.setId(entity.getId());
        commentDto.setTask(entity.getTask());
        commentDto.setEmployee(entity.getEmployee());
        commentDto.setText(entity.getText());
        commentDto.setCreatedDate(entity.getCreatedDate());
        return commentDto;
    }*/

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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
