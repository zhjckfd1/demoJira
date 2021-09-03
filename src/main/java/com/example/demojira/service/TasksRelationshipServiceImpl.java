package com.example.demojira.service;

import com.example.demojira.Constants;
import com.example.demojira.dto.TaskRelationshipDto;
import com.example.demojira.dto.TaskRelationshipUpdateDto;
import com.example.demojira.exceptions.EntityAlreadyExistsException;
import com.example.demojira.exceptions.MyEntityNotFoundException;
import com.example.demojira.exceptions.TryingToCreateABondOnYourselfException;
import com.example.demojira.model.Task;
import com.example.demojira.model.TasksRelationsType;
import com.example.demojira.model.TasksRelationship;
import com.example.demojira.repository.TaskRepository;
import com.example.demojira.repository.TasksRelationsTypeRepository;
import com.example.demojira.repository.TasksRelationshipRepository;
import com.example.demojira.service.mapping.TaskRelationshipDtoMapping;
import liquibase.pro.packaged.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TasksRelationshipServiceImpl implements TasksRelationshipService {

    private final TaskRepository taskRepository;
    private final TasksRelationshipRepository tasksRelationshipRepository;
    private final TasksRelationsTypeRepository tasksRelationsTypeRepository;
    private final TaskRelationshipDtoMapping taskRelationshipDtoMapping;

    @Autowired
    public TasksRelationshipServiceImpl(TaskRepository taskRepository,
                                        TasksRelationshipRepository tasksRelationshipRepository,
                                        TasksRelationsTypeRepository tasksRelationsTypeRepository,
                                        TaskRelationshipDtoMapping taskRelationshipDtoMapping) {
        this.taskRepository = taskRepository;
        this.tasksRelationshipRepository = tasksRelationshipRepository;
        this.tasksRelationsTypeRepository = tasksRelationsTypeRepository;
        this.taskRelationshipDtoMapping = taskRelationshipDtoMapping;
    }

    @Override
    @Transactional
    public void createRelationship(TaskRelationshipDto taskRelationshipDto) {
        if (taskRelationshipDto.getSourceTaskId().equals(taskRelationshipDto.getSubjectTaskId())) {
            throw new TryingToCreateABondOnYourselfException();
        }

        Task sourceTack = taskRepository.findById(taskRelationshipDto.getSourceTaskId())
                .orElseThrow(() -> new MyEntityNotFoundException(
                        Constants.TASK_NOT_FOUND_MESSAGE + taskRelationshipDto.getSourceTaskId()));
        Task subjectTack = taskRepository.findById(taskRelationshipDto.getSubjectTaskId())
                .orElseThrow(() -> new MyEntityNotFoundException(
                        Constants.TASK_NOT_FOUND_MESSAGE + taskRelationshipDto.getSourceTaskId()));
        TasksRelationsType type = tasksRelationsTypeRepository.findById(taskRelationshipDto.getRelationId())
                .orElseThrow(() -> new MyEntityNotFoundException(
                        Constants.TASK_RELATIONS_TYPE_NOT_FOUND_MESSAGE + taskRelationshipDto.getRelationId()));

        TasksRelationship tasksRelationship = TaskRelationshipDtoMapping
                .mapToEntity(sourceTack, subjectTack, type);

        if (tasksRelationshipRepository.findDistinctBySourceTaskAndSubjectTask(
                tasksRelationship.getSourceTask(),
                tasksRelationship.getSubjectTask()) == null
        ) {
            tasksRelationshipRepository.save(tasksRelationship);
        } else {
            throw new EntityAlreadyExistsException();
        }
    }

    @Override
    @Transactional
    public void updateRelationship(Integer relationshipId, TaskRelationshipUpdateDto taskRelationshipUpdateDto) {
        tasksRelationshipRepository.findById(relationshipId).ifPresentOrElse(relationship -> {
            TasksRelationsType type = tasksRelationsTypeRepository
                    .findById(taskRelationshipUpdateDto.getRelationId())
                    .orElseThrow(() -> new MyEntityNotFoundException(
                            Constants.TASK_RELATIONS_TYPE_NOT_FOUND_MESSAGE + taskRelationshipUpdateDto.getRelationId()
                    ));
            relationship.setTasksRelationsType(type);
            //используем код отношения вместо его id
        }, () -> {
            throw new MyEntityNotFoundException(Constants.TASK_RELATIONSHIP_NOT_FOUND_MESSAGE + relationshipId);
        });
    }

    @Override
    @Transactional
    public List<TaskRelationshipDto> getAllTasksRelationships() {
        return tasksRelationshipRepository.findAll()
                .stream()
                .map(taskRelationshipDtoMapping::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public TaskRelationshipDto getRelationshipById(Integer taskRelationshipId) {
        return taskRelationshipDtoMapping.mapToDto(
                tasksRelationshipRepository.findById(taskRelationshipId)
                        .orElseThrow(() -> new MyEntityNotFoundException(taskRelationshipId)));
    }

    @Override
    @Transactional
    public void deleteRelationshipById(Integer tasksRelationshipId) {
        tasksRelationshipRepository.findById(tasksRelationshipId)
                .ifPresentOrElse(tasksRelationshipRepository::delete, () -> {
                    throw new MyEntityNotFoundException(tasksRelationshipId);
                });
    }
}
