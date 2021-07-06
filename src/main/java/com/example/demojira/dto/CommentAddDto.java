package com.example.demojira.dto;

import com.example.demojira.model.Employee;
import com.example.demojira.model.Task;
import io.swagger.v3.oas.annotations.media.Schema;

public class CommentAddDto {
    @Schema(description = "текст комментария", example = "текст комментария")
    private String text;

    @Schema(description = "Id сотрудника, написавшего комментарий")
    private Integer employeeId;

    @Schema(description = "Id задачи, к которой написан комментарий")
    private Integer taskId;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }
}
