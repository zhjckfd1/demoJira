package com.example.demojira.service;

import com.example.demojira.model.Employee;
import com.example.demojira.model.Task;

import java.util.List;

public interface TaskService {
    // /employees/id_employee/tasks
    // /tasks/add
    void addTask(Task task);

    //получить все задачи сотрудника
    // /employees/id_employee/tasks
    List<Task> getAllOnEmployee();


    //как в мапинге делим?  (/employees/id_employee/tasks/unfulfilled)

    //получить все невыполненные задачи сотрудника
    List<Task> getAllUnfulfilledTasksOnEmployee();

    //получить все выполненные задачи сотрудника
    // /employees/id_employee/tasks/completed)
    List<Task> getAllCompletedTasksOnEmployee();

    // меняем статус задачи     (описание? сотрудника?)  (updateTask на всю задачу?)
    //  /tasks/id_task/update    (через сотруднгика?)
    Boolean updateStatus(Task task);

    // /tasks/id_task
    Task getById(Integer id);

    //получить все задачи?  удалить задачу?    (нужно?)
}
