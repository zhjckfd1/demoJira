package com.example.demojira.controller;


import com.example.demojira.dto.*;
import com.example.demojira.exceptions.EntityAlreadyExistsException;
import com.example.demojira.exceptions.TryingToCreateABondOnYourselfException;
import com.example.demojira.service.TaskService;
import com.example.demojira.service.TasksRelationshipService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.util.List;

@RestController
@Tag(name = "Задача", description = "работает с задачами")
public class TaskController {
    private final TaskService taskService;
    private final TasksRelationshipService tasksRelationshipService;

    @Autowired
    public TaskController(TaskService taskService, TasksRelationshipService tasksRelationshipService) {
        this.taskService = taskService;
        this.tasksRelationshipService = tasksRelationshipService;
    }

    @Operation(
            summary = "Создание задачи",
            description = "Позволяет создать задачу"
    )
    @RequestMapping(value = "/tasks", method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody TaskRegistrateDto task) {
        taskService.addTask(task);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Operation(
            summary = "Получение списка задач",
            description = "Позволяет получить все созданные задачи"
    )
    @RequestMapping(value = "/tasks", method = RequestMethod.GET)
    public ResponseEntity<List<TaskGetDto>> read() {
        final List<TaskGetDto> tasks = taskService.getAllTasks();
        return new ResponseEntity<>(tasks, HttpStatus.OK);
    }

    @Operation(
            summary = "Получение задачи",
            description = "Позволяет получить задачу по ее id"
    )
    @RequestMapping(value = "/tasks/{id}", method = RequestMethod.GET)
    public ResponseEntity<TaskGetDto> read(
            @PathVariable(name = "id") @Parameter(description = "id задачи") @Min(1) Integer id) {
        final TaskGetDto task = taskService.getById(id);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @Operation(
            summary = "Обновление задачи",
            description = "Позволяет изменить переменные задачи "
    )
    @RequestMapping(value = "/tasks/{taskId}", method = RequestMethod.PATCH)
    //@ResponseStatus(HttpStatus.NO_CONTENT)    //вместо return
    public ResponseEntity<?> changeTask(
            @PathVariable(name = "taskId") @Parameter(description = "id задачи") @Min(1) Integer taskId,
            @RequestBody TaskUpdateDto task) {
        taskService.patchTask(taskId, task);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(
            summary = "Связывает задачи",
            description = "Позволяет связать 2 задачи"
    )
    @RequestMapping(value = "/tasks/relations", method = RequestMethod.POST)
    public ResponseEntity<?> createRelationBetweenTasks(
            @RequestBody TaskRelationshipDto taskRelationshipDto) {
        tasksRelationshipService.createRelationship(taskRelationshipDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(
            summary = "Обновляет связь между задачами",
            description = "Позволяет обновить связь между задачами"
    )
    @RequestMapping(value = "/tasks/relations/{relationshipId}", method = RequestMethod.PATCH)
    public ResponseEntity<?> updateRelationBetweenTasks(
            @PathVariable(name = "relationshipId")
            @Parameter(description = "id связи между задачами")
            @Min(1)
                    Integer relationshipId,
            @RequestBody TaskRelationshipUpdateDto taskRelationshipUpdateDto) {
        tasksRelationshipService.updateRelationship(relationshipId, taskRelationshipUpdateDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Operation(
            summary = "Получение списка связей задач",
            description = "Позволяет получить список связей между задачами"
    )
    @RequestMapping(value = "/tasks/relations", method = RequestMethod.GET)
    public ResponseEntity<List<TaskRelationshipDto>> readAllTasksRelationships() {
        final List<TaskRelationshipDto> tasksRelationships = tasksRelationshipService.getAllTasksRelationships();
        return new ResponseEntity<>(tasksRelationships, HttpStatus.OK);
    }

    @Operation(
            summary = "Получение связи между задачами",
            description = "Позволяет получить связь между задачами по ее id"
    )
    @RequestMapping(value = "/tasks/relations/{id}", method = RequestMethod.GET)
    public ResponseEntity<TaskRelationshipDto> readTasksRelationship(
            @PathVariable(name = "id") @Parameter(description = "id связи") @Min(1) Integer id) {
        final TaskRelationshipDto task = tasksRelationshipService.getRelationshipById(id);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @Operation(
            summary = "Удаление связи между задачами",
            description = "Позволяет удалить связь между задачами по ее id"
    )
    @RequestMapping(value = "/tasks/relations/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<TaskRelationshipDto> deleteTasksRelationship(
            @PathVariable(name = "id") @Parameter(description = "id связи") @Min(1) Integer id) {
        tasksRelationshipService.deleteRelationshipById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
