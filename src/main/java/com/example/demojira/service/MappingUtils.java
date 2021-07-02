package com.example.demojira.service;

import com.example.demojira.DTO.CommentDto;
import com.example.demojira.model.Comment;
import org.springframework.stereotype.Service;

@Service
public class MappingUtils {
    public static CommentDto mapToCommentDto(Comment entity){
        CommentDto commentDto = new CommentDto();
        commentDto.setId(entity.getId());
        commentDto.setTask(entity.getTask());
        commentDto.setEmployee(entity.getEmployee());
        commentDto.setText(entity.getText());
        commentDto.setCreatedDate(entity.getCreatedDate());
        return commentDto;
    }

    public static Comment mapToCommentEntity(Comment dto){
        Comment comment = new Comment();
        comment.setId(dto.getId());
        comment.setTask(dto.getTask());
        comment.setEmployee(dto.getEmployee());
        comment.setText(dto.getText());
        comment.setCreatedDate(dto.getCreatedDate());
        return comment;
    }
}
