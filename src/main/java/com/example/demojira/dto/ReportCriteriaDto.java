package com.example.demojira.dto;

import javax.validation.constraints.Min;

public class ReportCriteriaDto {

    @Min(1)
    private Integer taskId;

    @Min(1)
    private Integer employeeId;

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
}
