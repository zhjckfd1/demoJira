package com.example.demojira.service.mapping;

import com.example.demojira.dto.CommentDto;
import com.example.demojira.model.Comment;
import org.springframework.stereotype.Service;

@Service
public class MappingCommentDto {
    public CommentDto mapToDto(Comment entity) {
        CommentDto commentDto = new CommentDto();
        commentDto.setTaskId(entity.getTask().getId());
        commentDto.setEmployeeId(entity.getEmployee().getId());
        commentDto.setText(entity.getText());
        commentDto.setCreatedDate(entity.getCreatedDate());
        return commentDto;
    }
}
