package com.example.demojira.service;

import com.example.demojira.model.Comment;
import com.example.demojira.model.Employee;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentService {
    void addComment(Comment comment);

    //нужен?
    List<Comment> getAll();

    //сортировка?  (как правильно?)
    @Query("select c from Comment c where c.idTask = :id_task order by c.dateOfCreation")
    List<Comment> getAllCommentsOnTheTask(@Param("id_task") Integer id_task);

    Employee getById(Integer id);

    //update?
    Employee editComment(Comment comment);

}
