package com.example.demojira.service;

import com.example.demojira.dto.*;
import com.example.demojira.exceptions.EntityAlreadyExistsException;
import com.example.demojira.exceptions.TryingToCreateABondOnYourselfException;
import com.example.demojira.model.*;
import com.example.demojira.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskStatusRepository taskStatusRepository;

    @Autowired
    private TasksRelationshipRepository tasksRelationshipRepository;

    @Autowired
    private TasksRelationsTypeRepository tasksRelationsTypeRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    private static final String START_STATUS = "BASE";

    @Override
    @Transactional
    public void addTask(TaskRegistrateDto trd) {
        Employee employee = employeeRepository.findById(trd.getEmployeeId()).orElseThrow(EntityNotFoundException::new);
        TaskStatus ts = taskStatusRepository.findByCode(START_STATUS);

        if (ts != null) {
            Task task = MappingUtils.mapToEntityFromTaskRegistrateDto(trd, ts, employee);
            taskRepository.save(task);
        } else {
            throw new EntityNotFoundException();
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
                Employee emp = employeeRepository.findById(taskUpdateDto.getEmployeeId()).orElseThrow(EntityNotFoundException::new);
                task.setEmployee(emp);
            }
            if (taskUpdateDto.getTaskStatusId() != null) {
                TaskStatus ts = taskStatusRepository.findById(taskUpdateDto.getTaskStatusId()).orElseThrow(EntityNotFoundException::new);
                task.setStatus(ts);
            }
            if (taskUpdateDto.getTitle() != null) {
                task.setTitle(taskUpdateDto.getTitle());
            }
            taskRepository.save(task);
        }, () -> {
            throw new EntityNotFoundException();
        });
    }

    @Override
    public TaskGetDto getById(Integer taskId) {
        return MappingUtils.mapToTaskGetDto(taskRepository.getById(taskId));
    }

    @Override
    public List<TaskGetDto> getAllTasks() {
        return taskRepository.findAll().stream().map(MappingUtils::mapToTaskGetDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void createRelationship(TaskRelationshipDto taskRelationshipDto) {
        if (taskRelationshipDto.getSourceTaskId().equals(taskRelationshipDto.getSubjectTaskId())) {
            throw new TryingToCreateABondOnYourselfException();
        }

        Task sourceTack = taskRepository.findById(taskRelationshipDto.getSourceTaskId())
                .orElseThrow(EntityNotFoundException::new);
        Task subjectTack = taskRepository.findById(taskRelationshipDto.getSubjectTaskId())
                .orElseThrow(EntityNotFoundException::new);
        TasksRelationsType type = tasksRelationsTypeRepository.findById(taskRelationshipDto.getRelationId())
                .orElseThrow(EntityNotFoundException::new);

        TasksRelationship tasksRelationship = MappingUtils.mapToEntityFromTaskRelationshipDto(sourceTack, subjectTack, type);

        if (tasksRelationshipRepository.findDistinctBySourceTaskAndSubjectTask(tasksRelationship.getSourceTask(), tasksRelationship.getSubjectTask()) == null) {
            tasksRelationshipRepository.save(tasksRelationship);
        } else {
            throw new EntityAlreadyExistsException();
        }
    }

    @Override
    @Transactional
    public void updateRelationship(Integer relationshipId, TaskRelationshipUpdateDto taskRelationshipUpdateDto) {
        tasksRelationshipRepository.findById(relationshipId).ifPresentOrElse(relationship -> {
            TasksRelationsType type = tasksRelationsTypeRepository.findById(taskRelationshipUpdateDto.getRelationId()).orElseThrow(EntityNotFoundException::new);
            relationship.setTasksRelationsType(type);
            tasksRelationshipRepository.save(relationship);
        }, () -> {
            throw new EntityNotFoundException();
        });
    }

    @Override
    public List<TaskRelationshipDto> getAllTasksRelationships() {
        return tasksRelationshipRepository.findAll().stream().map(MappingUtils::mapToTaskRelationshipDto).collect(Collectors.toList());
    }

    @Override
    public TaskRelationshipDto getRelationshipById(Integer taskRelationshipId) {
        return MappingUtils.mapToTaskRelationshipDto(tasksRelationshipRepository.getById(taskRelationshipId));
    }

    @Override
    @Transactional
    public void deleteRelationshipById(Integer tasksRelationshipId) {
        tasksRelationshipRepository.findById(tasksRelationshipId).ifPresentOrElse(relationship -> tasksRelationshipRepository.delete(relationship), () -> {
            throw new EntityNotFoundException();
        });
    }
}
