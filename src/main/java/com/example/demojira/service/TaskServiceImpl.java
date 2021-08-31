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
                .orElseThrow(() -> new MyEntityNotFoundException(Employee.class.getName()));
        TaskStatus ts = taskStatusRepository.findByCode(START_STATUS);

        if (ts != null) {
            Task task = taskRegistrateDtoMapping.mapToEntity(trd, ts, employee);
            taskRepository.save(task);
        } else {
            throw new MyEntityNotFoundException("Начальный статус не найден в репозитории");
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
                                "Не найден сотрудник с id = " + taskUpdateDto.getEmployeeId()));
                task.setEmployee(emp);
            }
            if (taskUpdateDto.getTitle() != null) {
                task.setTitle(taskUpdateDto.getTitle());
            }
        }, () -> {
            throw new MyEntityNotFoundException("Не найдена задача с id = " + taskId);
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
                        .orElseThrow(
                                () -> new MyEntityNotFoundException("Не найден статус задачи с id = " + newStatusId));
                task.setStatus(ts);
            } else {
                throw new IncorrectStatusChangeException();
            }

        }, () -> {
            throw new MyEntityNotFoundException("Не найдена задача с id = " + taskId);
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
