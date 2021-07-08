package com.example.demojira.repository;

import com.example.demojira.model.Task;
import com.example.demojira.model.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskStatusRepository extends JpaRepository<TaskStatus, Integer>{
    TaskStatus findByCode(String code);
}
