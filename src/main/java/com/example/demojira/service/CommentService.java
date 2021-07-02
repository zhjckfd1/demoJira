package com.example.demojira.service;

import com.example.demojira.DTO.CommentAddDto;
import com.example.demojira.DTO.CommentDto;
import com.example.demojira.DTO.CommentEmployeeDto;
import com.example.demojira.DTO.CommentTaskDto;
import com.example.demojira.model.Comment;

import java.util.List;

public interface CommentService {

    // /comments   ?
    void addComment(CommentAddDto commentAddDto);

    //нужен?
    List<CommentDto> getAll();

    List<CommentTaskDto> getAllCommentsOnTheTask(Integer taskId);

    List<CommentEmployeeDto> getAllCommentsOnTheEmployee(Integer employeeId);

    CommentDto getById(Integer commentId);


    //update?    (не комментируем)
    //Boolean editComment(Comment comment);

}
