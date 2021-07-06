package com.example.demojira.service;

import com.example.demojira.dto.*;

import java.util.List;

public interface TaskService {

    void addTask(TaskRegistrateDto task);

    /*
    //получить все задачи сотрудника
    // /tasks/employeeId
    List<Task> getAllTasksOnEmployee(Integer employeeId);

    // /tasks/employeeId/statusId
    List<Task> getAllEmployeeTasksWithStatus(Integer employeeId, Integer statusId);
   */

    void patchTask(Integer taskId, TaskUpdateDto taskUpdateDto);

    TaskGetDto getById(Integer taskId);

    List<TaskGetDto> getAllTasks();

    void createRelationship(TaskRelationshipDto taskRelationshipDto);

    void updateRelationship(Integer relationshipId, TaskRelationshipUpdateDto taskRelationshipUpdateDto);

    List<TaskRelationshipDto> getAllTasksRelationships();

    TaskRelationshipDto getRelationshipById(Integer taskRelationshipId);
}
