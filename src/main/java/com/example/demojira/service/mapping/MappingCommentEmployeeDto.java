package com.example.demojira.service.mapping;

import com.example.demojira.dto.CommentEmployeeDto;
import com.example.demojira.model.Comment;
import org.springframework.stereotype.Service;

@Service
public class MappingCommentEmployeeDto {
    public CommentEmployeeDto mapToDto(Comment entity) {
        CommentEmployeeDto commentEmployeeDto = new CommentEmployeeDto();
        commentEmployeeDto.setText(entity.getText());
        commentEmployeeDto.setCreatedDate(entity.getCreatedDate());
        commentEmployeeDto.setTaskId(entity.getTask().getId());
        return commentEmployeeDto;
    }
}
