package com.example.demojira.service;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TasksRelationshipServiceImpl implements TasksRelationshipService{

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TasksRelationshipRepository tasksRelationshipRepository;

    @Autowired
    private TasksRelationsTypeRepository tasksRelationsTypeRepository;

    @Autowired
    private TaskRelationshipDtoMapping taskRelationshipDtoMapping;

    @Override
    @Transactional
    public void createRelationship(TaskRelationshipDto taskRelationshipDto) {
        if (taskRelationshipDto.getSourceTaskId().equals(taskRelationshipDto.getSubjectTaskId())) {
            throw new TryingToCreateABondOnYourselfException();
        }

        Task sourceTack = taskRepository.findById(taskRelationshipDto.getSourceTaskId())
                .orElseThrow(MyEntityNotFoundException::new);
        Task subjectTack = taskRepository.findById(taskRelationshipDto.getSubjectTaskId())
                .orElseThrow(MyEntityNotFoundException::new);
        TasksRelationsType type = tasksRelationsTypeRepository.findById(taskRelationshipDto.getRelationId())
                .orElseThrow(MyEntityNotFoundException::new);

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
                    .findById(taskRelationshipUpdateDto.getRelationId()).orElseThrow(MyEntityNotFoundException::new);
            relationship.setTasksRelationsType(type);
        }, () -> {
            throw new MyEntityNotFoundException();
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
                tasksRelationshipRepository.findById(taskRelationshipId).orElseThrow(MyEntityNotFoundException::new));
    }

    @Override
    @Transactional
    public void deleteRelationshipById(Integer tasksRelationshipId) {
        tasksRelationshipRepository.findById(tasksRelationshipId)
                .ifPresentOrElse(relationship -> tasksRelationshipRepository.delete(relationship), () -> {
                    throw new MyEntityNotFoundException();
                });
    }
}
