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
import com.example.demojira.service.mapping.MappingCommentAddDto;
import com.example.demojira.service.mapping.MappingCommentDto;
import com.example.demojira.service.mapping.MappingCommentEmployeeDto;
import com.example.demojira.service.mapping.MappingCommentTaskDto;
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
    private MappingCommentDto mappingCommentDto;

    @Autowired
    private MappingCommentEmployeeDto mappingCommentEmployeeDto;

    @Autowired
    private MappingCommentTaskDto mappingCommentTaskDto;

    @Autowired
    private MappingCommentAddDto mappingCommentAddDto;

    @Override
    public List<CommentTaskDto> getAllCommentsOnTheTask(Integer taskId) {
        return commentRepository.getAllByTaskIdOrderByCreatedDate(taskId)
                .stream()
                .map(mappingCommentTaskDto::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CommentEmployeeDto> getAllCommentsOnTheEmployee(Integer employeeId) {
        return commentRepository.getAllByEmployeeIdOrderByCreatedDate(employeeId)
                .stream()
                .map(mappingCommentEmployeeDto::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void addComment(CommentAddDto commentAddDto) {
        Task task = taskRepository.findById(commentAddDto.getTaskId())
                .orElseThrow(MyEntityNotFoundException::new);
        Employee employee = employeeRepository.findById(commentAddDto.getEmployeeId())
                .orElseThrow(MyEntityNotFoundException::new);

        Comment comment = mappingCommentAddDto.mapToEntity(commentAddDto, task, employee);
        commentRepository.save(comment);
    }

    @Override
    public List<CommentDto> getAll() {
        return commentRepository.findAll()
                .stream()
                .map(mappingCommentDto::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public CommentDto getById(Integer commentId) {
        return mappingCommentDto.mapToDto(commentRepository.getById(commentId));
        //getById вернет JPA исключение, если не найдет. Везде делаем по закоментированному варианту?

        //return mappingCommentDto.mapToDto(commentRepository.findById(commentId).orElseThrow(MyEntityNotFoundException::new));

    }
}
