package com.example.demojira.controller;

import com.example.demojira.DTO.CommentAddDto;
import com.example.demojira.DTO.CommentDto;
import com.example.demojira.DTO.CommentEmployeeDto;
import com.example.demojira.DTO.CommentTaskDto;
import com.example.demojira.model.Comment;
import com.example.demojira.service.CommentService;
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
//@RequestMapping(value = "/comments")
@Tag(name="Комментарий", description="работает с комментариями сотрудников")
public class CommentController {

    private final CommentService commentService;
    //на примере @RequestMapping(value = "/comments",  method = RequestMethod.GET)

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }


    //dto?
    @Operation(
            summary = "Создание комментария",
            description = "Позволяет создать комментарий"
    )
    //@PostMapping(value = "/comments")
    @RequestMapping(value = "/comments",  method = RequestMethod.POST)
    public ResponseEntity<?> create(@RequestBody CommentAddDto commentAddDto) {
        commentService.addComment(commentAddDto);
        //return ResponseEntity.ok().body(HttpStatus.CREATED);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @Operation(
            summary = "Получение списка комментариев",
            description = "Позволяет получить все созданные комментарии"
    )
    @RequestMapping(value = "/comments",  method = RequestMethod.GET)
    public ResponseEntity<List<CommentDto>> read() {
        final List<CommentDto> comments = commentService.getAll();
        return comments != null && !comments.isEmpty()
                ? new ResponseEntity<>(comments, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    //"/comments/tasks/{taskId}"   с TaskController не соединится?
    //"/comments/getTaskComments/{taskId}"           ?

    @Operation(
            summary = "Получение списка комментариев к задаче",
            description = "Позволяет получить все комментарии к задаче"
    )
    @RequestMapping(value = "/comments/tasks/{taskId}",  method = RequestMethod.GET)
    public ResponseEntity<List<CommentTaskDto>> readTaskComments(@PathVariable(name = "taskId") @Parameter(description = "id задачи") @Min(1) Integer taskId) {
        final List<CommentTaskDto> comments = commentService.getAllCommentsOnTheTask(taskId);
        return comments != null && !comments.isEmpty()
                ? new ResponseEntity<>(comments, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @Operation(
            summary = "Получение списка комментариев пользователя",
            description = "Позволяет получить все комментарии пользователя"
    )
    @RequestMapping(value = "/comments/employees/{employeeId}",  method = RequestMethod.GET)
    public ResponseEntity<List<CommentEmployeeDto>> readEmployeeComments(@PathVariable(name = "employeeId") @Parameter(description = "id задачи") @Min(1) Integer employeeId) {
        final List<CommentEmployeeDto> comments = commentService.getAllCommentsOnTheEmployee(employeeId);
        return comments != null && !comments.isEmpty()
                ? new ResponseEntity<>(comments, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }


    @Operation(
            summary = "Получение комментария",
            description = "Позволяет получить комментарий по его id"
    )
    @RequestMapping(value = "/comments/{id}",  method = RequestMethod.GET)
    public ResponseEntity<CommentDto> read(@PathVariable(name = "id") @Parameter(description = "id комментария") @Min(1) Integer id) {
        final CommentDto comment = commentService.getById(id);

        return comment != null
                ? new ResponseEntity<>(comment, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
