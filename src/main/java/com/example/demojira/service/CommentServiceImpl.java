package com.example.demojira.service;

import com.example.demojira.dto.CommentAddDto;
import com.example.demojira.dto.CommentDto;
import com.example.demojira.dto.CommentEmployeeDto;
import com.example.demojira.dto.CommentTaskDto;
import com.example.demojira.exceptions.MyEntityNotFoundException;
import com.example.demojira.model.Comment;
import com.example.demojira.model.Employee;
import com.example.demojira.model.Task;
import com.example.demojira.repository.CommentRepository;
import com.example.demojira.repository.EmployeeRepository;
import com.example.demojira.repository.TaskRepository;
import com.example.demojira.service.mapping.CommentAddDtoMapping;
import com.example.demojira.service.mapping.CommentDtoMapping;
import com.example.demojira.service.mapping.CommentEmployeeDtoMapping;
import com.example.demojira.service.mapping.CommentTaskDtoMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CommentDtoMapping commentDtoMapping;

    @Autowired
    private CommentEmployeeDtoMapping commentEmployeeDtoMapping;

    @Autowired
    private CommentTaskDtoMapping commentTaskDtoMapping;

    @Autowired
    private CommentAddDtoMapping commentAddDtoMapping;

    @Override
    @Transactional
    public List<CommentTaskDto> getAllCommentsOnTheTask(Integer taskId) {
        return commentRepository.getAllByTaskIdOrderByCreatedDate(taskId)
                .stream()
                .map(commentTaskDtoMapping::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<CommentEmployeeDto> getAllCommentsOnTheEmployee(Integer employeeId) {
        return commentRepository.getAllByEmployeeIdOrderByCreatedDate(employeeId)
                .stream()
                .map(commentEmployeeDtoMapping::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void addComment(CommentAddDto commentAddDto) {
        Task task = taskRepository.findById(commentAddDto.getTaskId())
                .orElseThrow(MyEntityNotFoundException::new);
        Employee employee = employeeRepository.findById(commentAddDto.getEmployeeId())
                .orElseThrow(MyEntityNotFoundException::new);

        Comment comment = commentAddDtoMapping.mapToEntity(commentAddDto, task, employee);
        commentRepository.save(comment);
    }

    @Override
    @Transactional
    public List<CommentDto> getAll() {
        return commentRepository.findAll()
                .stream()
                .map(commentDtoMapping::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public CommentDto getById(Integer commentId) {
        return commentDtoMapping
                .mapToDto(commentRepository.findById(commentId).orElseThrow(MyEntityNotFoundException::new));
    }
}
