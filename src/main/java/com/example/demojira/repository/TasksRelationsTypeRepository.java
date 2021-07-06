package com.example.demojira.repository;

import com.example.demojira.model.TasksRelationsType;
import com.example.demojira.model.TasksRelationship;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TasksRelationsTypeRepository extends JpaRepository<TasksRelationsType, Integer> {
}
