package com.example.demojira.service;

import com.example.demojira.DTO.TaskGetDto;
import com.example.demojira.DTO.TaskRegistrateDto;
import com.example.demojira.model.Task;
import com.example.demojira.repository.TaskRepository;
import com.example.demojira.repository.TaskStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService{

    //@Inject
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskStatusRepository taskStatusRepository;


    @Override
    public void addTask(TaskRegistrateDto trd) {
        //как статус назначаем?
        Task task = MappingUtils.mapToEntityFromTaskRegistrateDto(trd, taskStatusRepository.getById(0));
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
            task.setStatus(taskStatusRepository.getById(statusId));
            taskRepository.save(task);
            return true;
        }
        else return false;
    }

    @Override
    public TaskGetDto getById(Integer taskId) {
        return MappingUtils.mapToTaskGetDto(taskRepository.getById(taskId));
        //return taskRepository.getById(taskId);
    }

    @Override
    public List<TaskGetDto> getAllTasks() {
        return taskRepository.findAll().stream().map(MappingUtils::mapToTaskGetDto).collect(Collectors.toList());
    }
}
