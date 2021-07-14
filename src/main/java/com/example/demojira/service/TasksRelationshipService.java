package com.example.demojira.service;

import com.example.demojira.dto.TaskRelationshipDto;
import com.example.demojira.dto.TaskRelationshipUpdateDto;

import java.util.List;

public interface TasksRelationshipService {
    void createRelationship(TaskRelationshipDto taskRelationshipDto);

    void updateRelationship(Integer relationshipId, TaskRelationshipUpdateDto taskRelationshipUpdateDto);

    List<TaskRelationshipDto> getAllTasksRelationships();

    TaskRelationshipDto getRelationshipById(Integer taskRelationshipId);

    void deleteRelationshipById(Integer tasksRelationshipId);
}
