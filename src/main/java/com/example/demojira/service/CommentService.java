package com.example.demojira.service;

import com.example.demojira.model.Comment;
import com.example.demojira.model.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentService {

    // /comments   ?
    void addComment(Comment comment);

    //нужен?
    List<Comment> getAll();

    List<Comment> getAllCommentsOnTheTask(Integer taskId);

    List<Comment> getAllCommentsOnTheEmployee(Integer employeeId);

    Comment getById(Integer commentId);

    //update?    (не комментируем)
    //Boolean editComment(Comment comment);

}
