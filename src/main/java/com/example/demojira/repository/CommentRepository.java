package com.example.demojira.repository;

import com.example.demojira.model.Comment;
import com.example.demojira.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

    @Query("select c from Comment c where c.taskId = :taskId order by c.registeredDate")
    /*
    @Query(
            value = "SELECT * FROM COMMENTS c WHERE c.task_id = :taskId order by c.registered_date",
            nativeQuery = true)
     */
    List<Comment> getAllCommentsOnTheTask(@Param("taskId") Integer taskId);

    @Query("select c from Comment c where c.employeeId = :employeeId order by c.registeredDate")
    /*
    @Query(
            value = "SELECT * FROM COMMENTS c WHERE c.employee_id = :employeeId order by c.registered_date",
            nativeQuery = true)
            */
    List<Comment> getAllCommentsOnTheEmployee(@Param("employeeId") Integer employeeId);
}
