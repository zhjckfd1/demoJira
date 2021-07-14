package com.example.demojira.service;

import com.example.demojira.dto.*;
import com.example.demojira.exceptions.EntityAlreadyExistsException;
import com.example.demojira.exceptions.TryingToCreateABondOnYourselfException;

import java.util.List;

public interface TaskService {

    void addTask(TaskRegistrateDto task);

    void patchTask(Integer taskId, TaskUpdateDto taskUpdateDto);

    TaskGetDto getById(Integer taskId);

    List<TaskGetDto> getAllTasks();
}
