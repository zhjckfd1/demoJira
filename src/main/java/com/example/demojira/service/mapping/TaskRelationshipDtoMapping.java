package com.example.demojira.service.mapping;

import com.example.demojira.dto.TaskRelationshipDto;
import com.example.demojira.model.Task;
import com.example.demojira.model.TasksRelationsType;
import com.example.demojira.model.TasksRelationship;
import org.springframework.stereotype.Service;

@Service
public class TaskRelationshipDtoMapping {
    public TaskRelationshipDto mapToDto(TasksRelationship entity) {
        TaskRelationshipDto taskRelationshipDto = new TaskRelationshipDto();
        taskRelationshipDto.setRelationId(entity.getTasksRelationsType().getId());
        taskRelationshipDto.setSourceTaskId(entity.getSourceTask().getId());
        taskRelationshipDto.setSubjectTaskId(entity.getSubjectTask().getId());
        return taskRelationshipDto;
    }

    public static TasksRelationship mapToEntity(Task sourceTask, Task subjectTask, TasksRelationsType type){
        TasksRelationship tasksRelationship = new TasksRelationship();
        tasksRelationship.setSourceTask(sourceTask);
        tasksRelationship.setSubjectTask(subjectTask);
        tasksRelationship.setTasksRelationsType(type);
        return tasksRelationship;
    }
}
