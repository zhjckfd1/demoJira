package com.example.demojira.repository;

import com.example.demojira.model.TasksRelationsType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TasksRelationsTypeRepository extends JpaRepository<TasksRelationsType, Integer> {
}
