package com.example.demojira.service;

import com.example.demojira.model.Task;
import com.example.demojira.model.TaskStatus;
import com.example.demojira.repository.EmployeeRepository;
import com.example.demojira.repository.TaskRepository;
import com.example.demojira.repository.TaskStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TaskServiceImpl implements TaskService{

    //@Inject
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskStatusRepository taskStatusRepository;


    @Override
    public void addTask(Task task) {
        //проверка сотрудника?   (через репозиторий?)
        task.setRegisteredDate(new Date());
        task.setStatusId(0);    //?    (назначена)
        taskRepository.save(task);
    }

    //удлиняем ссылку в контроллере для включения закомментированного функционала?


    /*
    @Override
    public List<Task> getAllTasksOnEmployee(Integer employeeId) {
        return taskRepository.getAllTasksOnEmployee(employeeId);
    }

    @Override
    public List<Task> getAllEmployeeTasksWithStatus(Integer employeeId, Integer statusId) {
        return taskRepository.getAllEmployeeTasksWithStatus(employeeId, statusId);
    }*/


    @Override
    public Boolean updateStatus(Integer taskId, Integer statusId) {
    //проверка статуса?
        if (taskRepository.findById(taskId).isPresent()  && taskStatusRepository.findById(statusId).isPresent()) {
            Task task = taskRepository.getById(taskId);
            task.setStatusId(statusId);
            taskRepository.save(task);
            return true;
        }
        else return false;
    }

    @Override
    public Task getById(Integer taskId) {
        return taskRepository.getById(taskId);
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }
}
