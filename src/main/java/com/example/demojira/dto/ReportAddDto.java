package com.example.demojira.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ReportAddDto {
    //(1 DTO, но с использованием аннотаций и интерфейсов?)
    //https://habr.com/ru/post/343960/

    @Min(1)
    @NotNull
    private Integer taskId;

    @Min(1)
    @NotNull
    private Integer employeeId;

    @Size(min=2, max=255)
    @NotNull
    private String description;

    @Min(1)
    @NotNull
    private Long spentTime;

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

    public Long getSpentTime() {
        return spentTime;
    }

    public void setSpentTime(Long spentTime) {
        this.spentTime = spentTime;
    }
}
