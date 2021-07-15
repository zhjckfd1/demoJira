package com.example.demojira.service;

import com.example.demojira.dto.ChangeStatusGetDto;
import com.example.demojira.exceptions.MyEntityNotFoundException;
import com.example.demojira.repository.ChangeStatusRepository;
import com.example.demojira.service.mapping.MappingChangeStatusGetDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChangeStatusServiceImpl implements ChangeStatusService{

    @Autowired
    private ChangeStatusRepository changeStatusRepository;

    @Autowired
    private MappingChangeStatusGetDto mappingChangeStatusGetDto;

    public List<ChangeStatusGetDto> getAll(){
        return changeStatusRepository.findAll()
                .stream()
                .map(mappingChangeStatusGetDto::mapToDto)
                .collect(Collectors.toList());
    }

    public List<ChangeStatusGetDto> getAllByBeginTaskStatusId(Integer beginStatusId){
        return changeStatusRepository.getAllByBeginTaskStatusId(beginStatusId)
                .stream()
                .map(mappingChangeStatusGetDto::mapToDto)
                .collect(Collectors.toList());
    }

    public List<ChangeStatusGetDto> getAllByEndTaskStatusId(Integer endStatusId){
        return changeStatusRepository.getAllByEndTaskStatusId(endStatusId)
                .stream()
                .map(mappingChangeStatusGetDto::mapToDto)
                .collect(Collectors.toList());
    }

    public ChangeStatusGetDto getById(Integer changeStatusId){
        return mappingChangeStatusGetDto
                .mapToDto(changeStatusRepository.findById(changeStatusId).orElseThrow(MyEntityNotFoundException::new));
    }
}
