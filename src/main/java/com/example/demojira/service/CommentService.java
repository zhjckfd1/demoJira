package com.example.demojira.service;

import com.example.demojira.DTO.CommentDto;
import com.example.demojira.model.Comment;
import com.example.demojira.model.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentService {

    // /comments   ?
    void addComment(Comment comment);

    //нужен?
    List<CommentDto> getAll();

    List<CommentDto> getAllCommentsOnTheTask(Integer taskId);

    List<CommentDto> getAllCommentsOnTheEmployee(Integer employeeId);

    CommentDto getById(Integer commentId);

    //update?    (не комментируем)
    //Boolean editComment(Comment comment);

}
