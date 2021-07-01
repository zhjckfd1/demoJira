package com.example.demojira.controller;


import com.example.demojira.model.Employee;
import com.example.demojira.model.Task;
import com.example.demojira.service.EmployeeService;
import com.example.demojira.service.TaskService;
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
//@RequestMapping("/tasks")                  //?
@Tag(name="Задача", description="работает с задачами")
public class TaskController {
    private final TaskService taskService;

    @Autowired
    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }


    @Operation(
            summary = "Создание задачи",
            description = "Позволяет создать задачу"
    )
    @RequestMapping(value = "/tasks",  method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody Task task) {
        taskService.addTask(task);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @Operation(
            summary = "Получение списка задач",
            description = "Позволяет получить все созданные задачи"
    )
    @RequestMapping(value = "/tasks",  method = RequestMethod.GET)
    public ResponseEntity<List<Task>> read() {
        final List<Task> tasks = taskService.getAllTasks();
        return tasks != null && !tasks.isEmpty()
                ? new ResponseEntity<>(tasks, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @Operation(
            summary = "Получение задачи",
            description = "Позволяет получить задачу по ее id"
    )
    @RequestMapping(value = "/tasks/{id}",  method = RequestMethod.GET)
    public ResponseEntity<Task> read(@PathVariable(name = "id") @Parameter(description = "id задачи") @Min(1) Integer id) {
        final Task task = taskService.getById(id);

        return task != null
                ? new ResponseEntity<>(task, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @Operation(
            summary = "Обновление статуса задачи",
            description = "Позволяет изменить статус задачи"
    )
    @RequestMapping(value = "/tasks/{taskId}/{statusId}",  method = RequestMethod.PUT)
    public ResponseEntity<?> changeStatus(@PathVariable(name = "taskId") @Parameter(description = "id задачи") @Min(1) Integer taskId,
                                          @PathVariable(name = "statusId") @Parameter(description = "id статуса") @Min(1) Integer statusId) {
        Boolean update = taskService.updateStatus(taskId,statusId);
        return update
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



}
