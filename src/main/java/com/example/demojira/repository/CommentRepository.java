package com.example.demojira.repository;

import com.example.demojira.model.Comment;
import com.example.demojira.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

//@RepositoryRestResource
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    //@Query("select c from Comment c where c.taskId = :taskId order by c.createdDate")
    /*
    @Query(
            value = "SELECT * FROM COMMENTS c WHERE c.task_id = :taskId order by c.registered_date",
            nativeQuery = true)
     */
    @Query("select c from Comment c where c.task.id= :taskId order by c.createdDate")
    List<Comment> getAllCommentsOnTheTask(@Param("taskId") Integer taskId);

    //@Query("select c from Comment c where c.employeeId = :employeeId order by c.createdDate")
    @Query("select c from Comment c where c.employee.id = :employeeId order by c.createdDate")
    /*
    @Query(
            value = "SELECT * FROM COMMENTS c WHERE c.employee_id = :employeeId order by c.registered_date",
            nativeQuery = true)
            */
    List<Comment> getAllCommentsOnTheEmployee(@Param("employeeId") Integer employeeId);
}
