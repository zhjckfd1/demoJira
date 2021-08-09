package com.example.demojira.service;

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

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskStatusRepository taskStatusRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private TaskGetDtoMapping taskGetDtoMapping;

    @Autowired
    private TaskRegistrateDtoMapping taskRegistrateDtoMapping;

    private static final String START_STATUS = "BASE";

    @Override
    @Transactional
    public void addTask(TaskRegistrateDto trd) {
        Employee employee = employeeRepository.findById(trd.getEmployeeId())
                .orElseThrow(MyEntityNotFoundException::new);
        TaskStatus ts = taskStatusRepository.findByCode(START_STATUS);

        if (ts != null) {
            Task task = taskRegistrateDtoMapping.mapToEntity(trd, ts, employee);
            taskRepository.save(task);
        } else {
            throw new MyEntityNotFoundException();
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
                        .orElseThrow(MyEntityNotFoundException::new);
                task.setEmployee(emp);
            }
            if (taskUpdateDto.getTitle() != null) {
                task.setTitle(taskUpdateDto.getTitle());
            }
        }, () -> {
            throw new MyEntityNotFoundException();
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
                TaskStatus ts = taskStatusRepository.findById(newStatusId)
                        .orElseThrow(MyEntityNotFoundException::new);
                task.setStatus(ts);
            } else {
                throw new IncorrectStatusChangeException();
            }

        }, () -> {
            throw new MyEntityNotFoundException();
        });
    }

    @Override
    @Transactional
    public TaskGetDto getById(Integer taskId) {
        return taskGetDtoMapping.mapToDto(taskRepository.findById(taskId).orElseThrow(MyEntityNotFoundException::new));
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
