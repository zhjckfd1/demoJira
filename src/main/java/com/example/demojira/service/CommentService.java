package com.example.demojira.service;

import com.example.demojira.dto.CommentAddDto;
import com.example.demojira.dto.CommentDto;
import com.example.demojira.dto.CommentEmployeeDto;
import com.example.demojira.dto.CommentTaskDto;

import java.util.List;

public interface CommentService {

    void addComment(CommentAddDto commentAddDto);

    List<CommentDto> getAll();

    List<CommentTaskDto> getAllCommentsOnTheTask(Integer taskId);

    List<CommentEmployeeDto> getAllCommentsOnTheEmployee(Integer employeeId);

    CommentDto getById(Integer commentId);
}
