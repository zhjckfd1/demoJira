package com.example.demojira.service;

import com.example.demojira.model.Employee;
import com.example.demojira.model.Task;

import java.util.List;

public interface TaskService {

    // /tasks
    void addTask(Task task);

    /*
    //получить все задачи сотрудника
    // /tasks/employeeId
    List<Task> getAllTasksOnEmployee(Integer employeeId);

    // /tasks/employeeId/statusId
    List<Task> getAllEmployeeTasksWithStatus(Integer employeeId, Integer statusId);
   */

    // меняем статус задачи     (описание? сотрудника?)  (updateTask на всю задачу?)
    //  /tasks/taskId/{statusId}     (через сотрудника?)
    Boolean updateStatus(Integer taskId, Integer statusId);

    // /tasks/taskId
    Task getById(Integer taskId);

    // /tasks
    List<Task> getAllTasks();
}
