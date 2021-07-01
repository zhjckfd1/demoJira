package com.example.demojira.service;

import com.example.demojira.model.Comment;
import com.example.demojira.model.Employee;
import com.example.demojira.repository.CommentRepository;
import com.example.demojira.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{

    //@Inject
    @Autowired
    private CommentRepository commentRepository;

    @Override
    public List<Comment> getAllCommentsOnTheTask(Integer taskId) {
        return commentRepository.getAllCommentsOnTheTask(taskId);
    }

    @Override
    public List<Comment> getAllCommentsOnTheEmployee(Integer employeeId) {
        return commentRepository.getAllCommentsOnTheEmployee(employeeId);
    }

    @Override
    public void addComment(Comment comment) {
        comment.setRegisteredDate(new Date());
        commentRepository.save(comment);
    }

    @Override
    public List<Comment> getAll() {
        return commentRepository.findAll();
    }

    @Override
    public Comment getById(Integer commentId) {
        return commentRepository.getById(commentId);
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
