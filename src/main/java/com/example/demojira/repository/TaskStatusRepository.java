package com.example.demojira.repository;

import com.example.demojira.model.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskStatusRepository extends JpaRepository<TaskStatus, Integer>{

    TaskStatus findByCode(String code);
}
