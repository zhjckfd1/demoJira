package com.example.demojira.repository;

import com.example.demojira.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
    List<Comment> getAllByTaskIdOrderByCreatedDate(Integer taskId);

    List<Comment> getAllByEmployeeIdOrderByCreatedDate(Integer employeeId);
}
