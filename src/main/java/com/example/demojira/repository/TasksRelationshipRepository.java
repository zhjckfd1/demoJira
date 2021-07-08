package com.example.demojira.repository;

import com.example.demojira.model.Task;
import com.example.demojira.model.TasksRelationship;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TasksRelationshipRepository extends JpaRepository<TasksRelationship, Integer> {
    TasksRelationship findDistinctBySourceTaskAndSubjectTask(Task sourceTask, Task subjectTask);
}
