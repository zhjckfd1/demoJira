package com.example.demojira.service;

import com.example.demojira.dto.ChangeStatusGetDto;

import java.util.List;

public interface ChangeStatusService {

    List<ChangeStatusGetDto> getAll();

    List<ChangeStatusGetDto> getAllByBeginTaskStatusId(Integer beginStatusId);

    List<ChangeStatusGetDto> getAllByEndTaskStatusId(Integer endStatusId);

    ChangeStatusGetDto getById(Integer changeStatusId);
}
