package com.example.demojira.repository;

import com.example.demojira.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;


public interface TaskRepository extends JpaRepository<Task, Integer> { }
