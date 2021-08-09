package com.example.demojira.service.mapping;

import com.example.demojira.dto.CommentTaskDto;
import com.example.demojira.model.Comment;
import org.springframework.stereotype.Service;

@Service
public class CommentTaskDtoMapping {
    public CommentTaskDto mapToDto(Comment entity) {
        CommentTaskDto commentTaskDto = new CommentTaskDto();
        commentTaskDto.setText(entity.getText());
        commentTaskDto.setCreatedDate(entity.getCreatedDate());
        commentTaskDto.setEmployeeId(entity.getEmployee().getId());
        return commentTaskDto;
    }
}
