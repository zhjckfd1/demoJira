package com.example.demojira.service;

import com.example.demojira.dto.*;
import com.example.demojira.model.*;
import com.example.demojira.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService{

    //@Inject
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private TaskStatusRepository taskStatusRepository;

    @Autowired
    private TasksRelationshipRepository tasksRelationshipRepository;

    @Autowired
    private TasksRelationsTypeRepository tasksRelationsTypeRepository;

    @Autowired
    private EmployeeRepository employeeRepository;


    @Override
    public void addTask(TaskRegistrateDto trd) {
        //как статус назначаем?
        TaskStatus ts = taskStatusRepository.findById(1).orElseThrow(EntityNotFoundException::new);
        Employee employee = employeeRepository.findById(trd.getEmployeeId()).orElseThrow(EntityNotFoundException::new);

        Task task = MappingUtils.mapToEntityFromTaskRegistrateDto(trd, ts, employee);
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
    public void patchTask(Integer taskId, TaskUpdateDto taskUpdateDto) {
        //обнуление необязательных полей?
        taskRepository.findById(taskId).ifPresentOrElse(task -> {
            if (taskUpdateDto.getDescription() != null) {
                task.setDescription(taskUpdateDto.getDescription());
            }
            if (taskUpdateDto.getEmployeeId() != null) {
                Employee emp = employeeRepository.findById(taskUpdateDto.getEmployeeId()).orElseThrow(EntityNotFoundException::new);
                task.setEmployee(emp);
            }
            if (taskUpdateDto.getTaskStatusId() != null) {
                TaskStatus ts = taskStatusRepository.findById(taskUpdateDto.getTaskStatusId()).orElseThrow(EntityNotFoundException::new);
                task.setStatus(ts);
            }
            if (taskUpdateDto.getTitle() != null) {
                task.setTitle(taskUpdateDto.getTitle());
            }
            taskRepository.save(task);
        }, () -> {
            throw new EntityNotFoundException();
        });
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



    //в отдельный сервис/контроллер?
    @Override
    public void createRelationship(TaskRelationshipDto taskRelationshipDto) {
        Task sourceTack = taskRepository.findById(taskRelationshipDto.getSourceTaskId()).orElseThrow(EntityNotFoundException::new);
        Task subjectTack = taskRepository.findById(taskRelationshipDto.getSubjectTaskId()).orElseThrow(EntityNotFoundException::new);
        TasksRelationsType type = tasksRelationsTypeRepository.findById(taskRelationshipDto.getRelationId()).orElseThrow(EntityNotFoundException::new);

        TasksRelationship tasksRelationship = MappingUtils.mapToEntityFromTaskRelationshipDto(sourceTack, subjectTack, type);
        tasksRelationshipRepository.save(tasksRelationship);
    }


    @Override
    public void updateRelationship(Integer relationshipId, TaskRelationshipUpdateDto taskRelationshipUpdateDto) {
        tasksRelationshipRepository.findById(relationshipId).ifPresentOrElse(relationship ->{
            TasksRelationsType type = tasksRelationsTypeRepository.findById(taskRelationshipUpdateDto.getRelationId()).orElseThrow(EntityNotFoundException::new);
            //System.out.println(type.getRelationType());
            relationship.setTasksRelationsType(type);
            //relationship.setTasksRelationsType(tasksRelationsTypeRepository.getById(taskRelationshipUpdateDto.getRelationId()));
            tasksRelationshipRepository.save(relationship);
        }, () -> {
            throw new EntityNotFoundException();
        });
    }

    @Override
    public List<TaskRelationshipDto> getAllTasksRelationships() {
        return tasksRelationshipRepository.findAll().stream().map(MappingUtils::mapToTaskRelationshipDto).collect(Collectors.toList());
    }

    @Override
    public TaskRelationshipDto getRelationshipById(Integer taskRelationshipId){
        return MappingUtils.mapToTaskRelationshipDto(tasksRelationshipRepository.getById(taskRelationshipId));
    }
}
