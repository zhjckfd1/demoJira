package com.example.demojira.repository;

import com.example.demojira.model.Comment;
import com.example.demojira.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Integer> {

    /*
    @Query(
            value = "SELECT * FROM TASKS t WHERE t.employee_id = :employeeId and t.status_id = :statusId order by t.registered_date",
            nativeQuery = true)
    List<Task> getAllEmployeeTasksWithStatus(@Param("employee_id") Integer employeeId, @Param("status_id") Integer statusId);

    @Query(
            value = "SELECT * FROM TASKS t WHERE t.employee_id = :employeeId order by t.registered_date",
            nativeQuery = true)
    List<Task> getAllTasksOnEmployee(@Param("employee_id") Integer employeeId);

     */
}
