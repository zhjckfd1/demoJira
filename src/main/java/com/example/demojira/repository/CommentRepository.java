package com.example.demojira.repository;

import com.example.demojira.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    List<Comment> getAllByTaskIdOrderByCreatedDate(Integer taskId);

    List<Comment> getAllByEmployeeIdOrderByCreatedDate(Integer employeeId);
}
