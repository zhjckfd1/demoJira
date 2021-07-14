package com.example.demojira.service.mapping;

import com.example.demojira.dto.CommentAddDto;
import com.example.demojira.model.Comment;
import com.example.demojira.model.Employee;
import com.example.demojira.model.Task;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MappingCommentAddDto {
    public Comment mapToEntity(CommentAddDto commentAddDto, Task task, Employee employee) {
        Comment comment = new Comment();
        comment.setTask(task);
        comment.setEmployee(employee);
        comment.setText(commentAddDto.getText());
        comment.setCreatedDate(new Date());
        return comment;
    }
}
