package com.example.demojira.service;

import com.example.demojira.dto.*;
import com.example.demojira.model.*;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MappingUtils {
    //не маппинг?
    public static CommentDto mapToCommentDto(Comment entity){
        CommentDto commentDto = new CommentDto();
        commentDto.setTaskId(entity.getTask().getId());
        commentDto.setEmployeeId(entity.getEmployee().getId());
        commentDto.setText(entity.getText());
        commentDto.setCreatedDate(entity.getCreatedDate());
        return commentDto;
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
        employeeDto.setFirstname(entity.getFirstname());
        employeeDto.setSurname(entity.getSurname());
        employeeDto.setPatronymic(entity.getPatronymic());
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
    public static Comment mapToEntityFromCommentAddDto(CommentAddDto commentAddDto, Task task,
                                                       Employee employee){
        Comment comment = new Comment();
        comment.setTask(task);
        comment.setEmployee(employee);
        comment.setText(commentAddDto.getText());
        comment.setCreatedDate(new Date());
        return comment;
    }

    public static Employee mapToEntityFromEmployeeRegistrateDto(EmployeeRegistrateDto employeeRegistrateDto,
                                                                String password){
        Employee employee = new Employee();
        employee.setLogin(employeeRegistrateDto.getLogin());
        employee.setPassword(password);
        employee.setFirstname(employeeRegistrateDto.getFirstname());
        employee.setSurname(employeeRegistrateDto.getSurname());
        employee.setPatronymic(employeeRegistrateDto.getPatronymic());
        employee.setRegisteredDate(new Date());
        employee.setActive(true);
        return employee;
    }

    public static Task mapToEntityFromTaskRegistrateDto
            (TaskRegistrateDto taskRegistrateDto, TaskStatus ts, Employee e){
        Task task = new Task();
        task.setStatus(ts);
        task.setEmployee(e);
        task.setTitle(taskRegistrateDto.getTitle());
        task.setDescription(taskRegistrateDto.getDescription());
        task.setRegisteredDate(new Date());
        return task;
    }

    public static TasksRelationship mapToEntityFromTaskRelationshipDto (Task sourceTask, Task subjectTask,
                                                                        TasksRelationsType type){
        TasksRelationship tasksRelationship = new TasksRelationship();
        tasksRelationship.setSourceTask(sourceTask);
        tasksRelationship.setSubjectTask(subjectTask);
        tasksRelationship.setTasksRelationsType(type);
        return tasksRelationship;
    }

}
