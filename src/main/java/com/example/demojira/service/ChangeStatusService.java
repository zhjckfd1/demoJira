package com.example.demojira.service;

import com.example.demojira.dto.ChangeStatusGetDto;
import com.example.demojira.dto.CommentDto;
import com.example.demojira.dto.CommentTaskDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ChangeStatusService {

    List<ChangeStatusGetDto> getAll();

    List<ChangeStatusGetDto> getAllByBeginTaskStatusId(Integer beginStatusId);

    ChangeStatusGetDto getById(Integer changeStatusId);
}
