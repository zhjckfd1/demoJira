package com.example.demojira.service;

import com.example.demojira.Constants;
import com.example.demojira.dto.*;
import com.example.demojira.exceptions.IncorrectStatusChangeException;
import com.example.demojira.exceptions.MyEntityNotFoundException;
import com.example.demojira.model.*;
import com.example.demojira.repository.*;
import com.example.demojira.service.mapping.TaskGetDtoMapping;
import com.example.demojira.service.mapping.TaskRegistrateDtoMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskStatusRepository taskStatusRepository;
    private final EmployeeRepository employeeRepository;
    private final TaskGetDtoMapping taskGetDtoMapping;
    private final TaskRegistrateDtoMapping taskRegistrateDtoMapping;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository,
                           TaskStatusRepository taskStatusRepository,
                           EmployeeRepository employeeRepository,
                           TaskGetDtoMapping taskGetDtoMapping,
                           TaskRegistrateDtoMapping taskRegistrateDtoMapping) {
        this.employeeRepository = employeeRepository;
        this.taskGetDtoMapping = taskGetDtoMapping;
        this.taskRegistrateDtoMapping = taskRegistrateDtoMapping;
        this.taskRepository = taskRepository;
        this.taskStatusRepository = taskStatusRepository;
    }

    @Override
    @Transactional
    public void addTask(TaskRegistrateDto trd) {
        Employee employee = employeeRepository.findById(trd.getEmployeeId())
                .orElseThrow(
                        () -> new MyEntityNotFoundException(Constants.EMPLOYEE_NOT_FOUND_MESSAGE + trd.getEmployeeId())
                );
        TaskStatus ts = taskStatusRepository.findByCode(Constants.TASK_BASE_STATUS);

        if (ts != null) {
            Task task = taskRegistrateDtoMapping.mapToEntity(trd, ts, employee);
            taskRepository.save(task);
        } else {
            throw new MyEntityNotFoundException(Constants.BASE_TASK_STATUS_NOT_FOUND_MESSAGE);
        }
    }

    @Override
    @Transactional
    public void patchTask(Integer taskId, TaskUpdateDto taskUpdateDto) {
        taskRepository.findById(taskId).ifPresentOrElse(task -> {
            if (taskUpdateDto.getDescription() != null) {
                task.setDescription(taskUpdateDto.getDescription());
            }
            if (taskUpdateDto.getEmployeeId() != null) {
                Employee emp = employeeRepository.findById(taskUpdateDto.getEmployeeId())
                        .orElseThrow(() -> new MyEntityNotFoundException(
                                Constants.EMPLOYEE_NOT_FOUND_MESSAGE + taskUpdateDto.getEmployeeId()));
                task.setEmployee(emp);
            }
            if (taskUpdateDto.getTitle() != null) {
                task.setTitle(taskUpdateDto.getTitle());
            }
        }, () -> {
            throw new MyEntityNotFoundException(Constants.TASK_NOT_FOUND_MESSAGE + taskId);
        });
    }

    @Override
    @Transactional
    public void patchTaskStatus(Integer taskId, List<ChangeStatusGetDto> changes, Integer newStatusId) {
        taskRepository.findById(taskId).ifPresentOrElse(task -> {
            boolean contain = false;
            int oldStatusId = task.getStatus().getId();
            for (ChangeStatusGetDto change : changes) {
                if (change.getBeginStatusId().equals(oldStatusId)) {
                    contain = true;
                    break;
                }
            }
            if (contain) {
                //в таблице CHANGES_STATUS меняем id статуса на код статуса
                //меняем id на код
                TaskStatus ts = taskStatusRepository.findById(newStatusId)
                        .orElseThrow(
                                () -> new MyEntityNotFoundException(
                                        Constants.TASK_STATUS_NOT_FOUND_MESSAGE + newStatusId));
                task.setStatus(ts);
            } else {
                throw new IncorrectStatusChangeException();
            }

        }, () -> {
            throw new MyEntityNotFoundException(Constants.TASK_NOT_FOUND_MESSAGE + taskId);
        });
    }

    @Override
    @Transactional
    public TaskGetDto getById(Integer taskId) {
        return taskGetDtoMapping
                .mapToDto(taskRepository
                        .findById(taskId)
                        .orElseThrow(() -> new MyEntityNotFoundException(taskId)));
    }

    @Override
    @Transactional
    public List<TaskGetDto> getAllTasks() {
        return taskRepository.findAll()
                .stream()
                .map(taskGetDtoMapping::mapToDto)
                .collect(Collectors.toList());
    }


}
