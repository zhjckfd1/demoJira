package com.example.demojira.service.mapping;

import com.example.demojira.dto.TaskRegistrateDto;
import com.example.demojira.model.Employee;
import com.example.demojira.model.Task;
import com.example.demojira.model.TaskStatus;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TaskRegistrateDtoMapping {
    public Task mapToEntity(TaskRegistrateDto taskRegistrateDto, TaskStatus ts, Employee e) {
        Task task = new Task();
        task.setStatus(ts);
        task.setEmployee(e);
        task.setTitle(taskRegistrateDto.getTitle());
        task.setDescription(taskRegistrateDto.getDescription());
        task.setRegisteredDate(new Date());
        return task;
    }
}
