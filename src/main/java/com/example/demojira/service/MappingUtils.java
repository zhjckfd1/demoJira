package com.example.demojira.service;

import com.example.demojira.dto.*;
import com.example.demojira.model.*;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MappingUtils {
    public static CommentDto mapToCommentDto(Comment entity){
        CommentDto commentDto = new CommentDto();
        commentDto.setTaskId(entity.getTask().getId());
        commentDto.setEmployeeId(entity.getEmployee().getId());
        commentDto.setText(entity.getText());
        commentDto.setCreatedDate(entity.getCreatedDate());
        return commentDto;
    }

    //?
    public static CommentAddDto mapToCommentAddDto (Comment entity){
        CommentAddDto commentAddDto = new CommentAddDto();
        commentAddDto.setText(entity.getText());
        commentAddDto.setTaskId(entity.getTask().getId());
        commentAddDto.setEmployeeId(entity.getEmployee().getId());
        return commentAddDto;
    }

    public static CommentEmployeeDto mapToCommentEmployeeDto (Comment entity){
        CommentEmployeeDto commentEmployeeDto = new CommentEmployeeDto();
        commentEmployeeDto.setText(entity.getText());
        commentEmployeeDto.setCreatedDate(entity.getCreatedDate());
        commentEmployeeDto.setTaskId(entity.getTask().getId());
        return commentEmployeeDto;
    }

    public static CommentTaskDto mapToCommentTaskDto(Comment entity){
        CommentTaskDto commentTaskDto = new CommentTaskDto();
        commentTaskDto.setText(entity.getText());
        commentTaskDto.setCreatedDate(entity.getCreatedDate());
        commentTaskDto.setEmployeeId(entity.getEmployee().getId());
        return commentTaskDto;
    }

    public static EmployeeGetDto mapToEmployeeGetDto(Employee entity){
        EmployeeGetDto employeeDto = new EmployeeGetDto();
        employeeDto.setLogin(entity.getLogin());
        employeeDto.setRegisteredDate(entity.getRegisteredDate());
        employeeDto.setActive(entity.getActive());
        return employeeDto;
    }

    public static TaskGetDto mapToTaskGetDto(Task entity){
        TaskGetDto taskGetDto = new TaskGetDto();
        taskGetDto.setDescription(entity.getDescription());
        taskGetDto.setTitle(entity.getTitle());
        taskGetDto.setTaskStatusId(entity.getStatus().getId());
        taskGetDto.setEmployeeId(entity.getEmployee().getId());
        taskGetDto.setRegisteredDate(entity.getRegisteredDate());
        return taskGetDto;
    }

    public static TaskRelationshipDto mapToTaskRelationshipDto(TasksRelationship entity){
        TaskRelationshipDto taskRelationshipDto = new TaskRelationshipDto();
        taskRelationshipDto.setRelationId(entity.getTasksRelationsType().getId());
        taskRelationshipDto.setSourceTaskId(entity.getSourceTask().getId());
        taskRelationshipDto.setSubjectTaskId(entity.getSubjectTask().getId());
        return taskRelationshipDto;
    }


    //из dto в entity
    //или инициируем репозиторий, или передаем данные из него (полученную по id задачу...)
    public static Comment mapToEntityFromCommentAddDto (CommentAddDto commentAddDto, Task task, Employee employee){
        Comment comment = new Comment();
        //comment.setTask(commentAddDto.getTask());
        //comment.setEmployee(commentAddDto.getEmployee());
        comment.setTask(task);
        comment.setEmployee(employee);
        comment.setText(commentAddDto.getText());
        comment.setCreatedDate(new Date());
        return comment;
    }

    public static Employee mapToEntityFromEmployeeRegistrateDto (EmployeeRegistrateDto employeeRegistrateDto){
        Employee employee = new Employee();
        employee.setLogin(employeeRegistrateDto.getLogin());
        employee.setPassword(employeeRegistrateDto.getPassword());
        employee.setRegisteredDate(new Date());
        employee.setActive(true);
        return employee;
    }

    public static Task mapToEntityFromTaskRegistrateDto (TaskRegistrateDto taskRegistrateDto, TaskStatus ts, Employee e){
        Task task = new Task();
        task.setStatus(ts);
        //task.setEmployee(taskRegistrateDto.getEmployee());
        task.setEmployee(e);
        task.setTitle(taskRegistrateDto.getTitle());
        task.setDescription(taskRegistrateDto.getDescription());
        task.setRegisteredDate(new Date());
        return task;
    }

    //или инициируем репозиторий, или передаем данные из него (полученную по id задачу...)
    public static TasksRelationship mapToEntityFromTaskRelationshipDto (Task sourceTask, Task subjectTask, TasksRelationsType type){
        //System.out.println(sourceTask + " + " + subjectTask + " + " + type);
        TasksRelationship tasksRelationship = new TasksRelationship();
        //System.out.println(sourceTask + " + " + subjectTask + " + " + type);

        tasksRelationship.setSourceTask(sourceTask);
        tasksRelationship.setSubjectTask(subjectTask);
        tasksRelationship.setTasksRelationsType(type);
        return tasksRelationship;
    }

}
