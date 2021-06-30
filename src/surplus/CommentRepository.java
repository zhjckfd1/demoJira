package com.example.demojira.repository;

import com.example.demojira.model.Comment;
import com.example.demojira.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
}
