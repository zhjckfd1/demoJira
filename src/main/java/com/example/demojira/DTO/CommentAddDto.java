package com.example.demojira.DTO;

import com.example.demojira.model.Comment;
import com.example.demojira.model.Employee;
import com.example.demojira.model.Task;
import io.swagger.v3.oas.annotations.media.Schema;

public class CommentAddDto {
    //комментарий должен относиться к задаче?
    @Schema(description = "текст комментария", example = "текст комментария")
    private String text;

    @Schema(description = "сотрудник, написавший комментарий")
    private Employee employee;

    @Schema(description = "задача, к которой написан комментарий")
    private Task task;


    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
}
