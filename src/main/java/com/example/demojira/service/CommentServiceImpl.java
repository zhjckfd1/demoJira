package com.example.demojira.service;

import com.example.demojira.dto.CommentAddDto;
import com.example.demojira.dto.CommentDto;
import com.example.demojira.dto.CommentEmployeeDto;
import com.example.demojira.dto.CommentTaskDto;
import com.example.demojira.model.Comment;
import com.example.demojira.model.Employee;
import com.example.demojira.model.Task;
import com.example.demojira.repository.CommentRepository;
import com.example.demojira.repository.EmployeeRepository;
import com.example.demojira.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
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

    @Override
    public List<CommentTaskDto> getAllCommentsOnTheTask(Integer taskId) {
        return commentRepository.getAllByTaskIdOrderByCreatedDate(taskId).stream().map(MappingUtils::mapToCommentTaskDto).collect(Collectors.toList());
    }

    @Override
    public List<CommentEmployeeDto> getAllCommentsOnTheEmployee(Integer employeeId) {
        return commentRepository.getAllByEmployeeIdOrderByCreatedDate(employeeId).stream().map(MappingUtils::mapToCommentEmployeeDto).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public void addComment(CommentAddDto commentAddDto) {
        Task task = taskRepository.findById(commentAddDto.getTaskId()).orElseThrow(EntityNotFoundException::new);
        Employee employee = employeeRepository.findById(commentAddDto.getEmployeeId()).orElseThrow(EntityNotFoundException::new);

        Comment comment = MappingUtils.mapToEntityFromCommentAddDto(commentAddDto, task, employee);
        commentRepository.save(comment);
    }

    @Override
    public List<CommentDto> getAll() {
        return commentRepository.findAll().stream().map(MappingUtils::mapToCommentDto).collect(Collectors.toList());
    }

    @Override
    public CommentDto getById(Integer commentId) {
        return MappingUtils.mapToCommentDto(commentRepository.getById(commentId));
    }
}
