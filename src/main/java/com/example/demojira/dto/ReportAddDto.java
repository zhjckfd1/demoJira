package com.example.demojira.dto;

import javax.validation.constraints.NotNull;

public class ReportAddDto {
    //(1 DTO, но с использованием аннотаций и интерфейсов?)
    //https://habr.com/ru/post/343960/

    @NotNull
    private Integer taskId;
    @NotNull
    private Integer employeeId;
    @NotNull
    private String description;
    @NotNull
    private Long timeSpent;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getTimeSpent() {
        return timeSpent;
    }

    public void setTimeSpent(Long timeSpent) {
        this.timeSpent = timeSpent;
    }
}
