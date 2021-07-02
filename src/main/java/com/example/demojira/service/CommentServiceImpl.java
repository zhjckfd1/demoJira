package com.example.demojira.service;

import com.example.demojira.DTO.CommentAddDto;
import com.example.demojira.DTO.CommentDto;
import com.example.demojira.DTO.CommentEmployeeDto;
import com.example.demojira.DTO.CommentTaskDto;
import com.example.demojira.model.Comment;
import com.example.demojira.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService{

    //@Inject
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<CommentTaskDto> getAllCommentsOnTheTask(Integer taskId) {
        return commentRepository.getAllCommentsOnTheTask(taskId).stream().map(MappingUtils::mapToCommentTaskDto).collect(Collectors.toList());
    }

    @Override
    public List<CommentEmployeeDto> getAllCommentsOnTheEmployee(Integer employeeId) {
        return commentRepository.getAllCommentsOnTheEmployee(employeeId).stream().map(MappingUtils::mapToCommentEmployeeDto).collect(Collectors.toList());
    }

    @Override
    public void addComment(CommentAddDto commentAddDto) {
        Comment comment = MappingUtils.mapToEntityFromCommentAddDto(commentAddDto);
        commentRepository.save(comment);
    }

    /*
    @Override
    public List<Comment> getAll() {

        return commentRepository.findAll();
    }*/

    @Override
    public List<CommentDto> getAll() {
        return commentRepository.findAll().stream().map(MappingUtils::mapToCommentDto).collect(Collectors.toList());
    }

    @Override
    public CommentDto getById(Integer commentId) {
        return MappingUtils.mapToCommentDto(commentRepository.getById(commentId));
        //return MappingUtils.mapToCommentDto(commentRepository.findById(commentId).orElse(new Comment()));
    }

    /*
    //@Transactional
    @Override
    public Boolean editComment(Comment comment) {
        boolean update = commentRepository.findById(comment.getId()).isPresent();
        if (update) {
            commentRepository.save(comment);
            return true;
        }
        else return false;
    }
     */
}
