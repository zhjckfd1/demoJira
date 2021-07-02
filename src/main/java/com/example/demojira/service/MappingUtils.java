package com.example.demojira.service;

import com.example.demojira.DTO.*;
import com.example.demojira.model.Comment;
import com.example.demojira.model.Employee;
import com.example.demojira.model.Task;
import com.example.demojira.model.TaskStatus;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MappingUtils {
    public static CommentDto mapToCommentDto(Comment entity){
        CommentDto commentDto = new CommentDto();
        commentDto.setTask(entity.getTask());
        commentDto.setEmployee(entity.getEmployee());
        commentDto.setText(entity.getText());
        commentDto.setCreatedDate(entity.getCreatedDate());
        return commentDto;
    }

    //?
    public static CommentAddDto mapToCommentAddDto (Comment entity){
        CommentAddDto commentAddDto = new CommentAddDto();
        commentAddDto.setText(entity.getText());
        commentAddDto.setEmployee(entity.getEmployee());
        commentAddDto.setTask(entity.getTask());
        return commentAddDto;
    }

    public static CommentEmployeeDto mapToCommentEmployeeDto (Comment entity){
        CommentEmployeeDto commentEmployeeDto = new CommentEmployeeDto();
        commentEmployeeDto.setText(entity.getText());
        commentEmployeeDto.setCreatedDate(entity.getCreatedDate());
        commentEmployeeDto.setTask(entity.getTask());
        return commentEmployeeDto;
    }

    public static CommentTaskDto mapToCommentTaskDto(Comment entity){
        CommentTaskDto commentTaskDto = new CommentTaskDto();
        commentTaskDto.setText(entity.getText());
        commentTaskDto.setCreatedDate(entity.getCreatedDate());
        commentTaskDto.setEmployee(entity.getEmployee());
        return commentTaskDto;
    }

    /*
    public static EmployeeDto mapToEmployeeDto(Employee entity){
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setLogin(entity.getLogin());
        employeeDto.setPassword(employeeDto.getPassword());
        employeeDto.setRegisteredDate(entity.getRegisteredDate());
        employeeDto.setActive(entity.getActive());
        return employeeDto;
    }*/

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
        taskGetDto.setStatus(entity.getStatus());
        taskGetDto.setEmployee(entity.getEmployee());
        taskGetDto.setRegisteredDate(entity.getRegisteredDate());
        return taskGetDto;
    }


    //из dto в entity
    public static Comment mapToEntityFromCommentAddDto (CommentAddDto commentAddDto){
        Comment comment = new Comment();
        comment.setTask(commentAddDto.getTask());
        comment.setEmployee(commentAddDto.getEmployee());
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

    public static Task mapToEntityFromTaskRegistrateDto (TaskRegistrateDto taskRegistrateDto, TaskStatus ts){
        Task task = new Task();
        task.setStatus(ts);
        task.setEmployee(taskRegistrateDto.getEmployee());
        task.setTitle(taskRegistrateDto.getTitle());
        task.setDescription(taskRegistrateDto.getDescription());
        task.setRegisteredDate(new Date());
        return task;
    }

}
