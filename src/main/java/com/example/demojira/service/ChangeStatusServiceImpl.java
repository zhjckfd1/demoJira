package com.example.demojira.service;

import com.example.demojira.dto.ChangeStatusGetDto;
import com.example.demojira.exceptions.MyEntityNotFoundException;
import com.example.demojira.repository.ChangeStatusRepository;
import com.example.demojira.service.mapping.ChangeStatusGetDtoMapping;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChangeStatusServiceImpl implements ChangeStatusService{

    private final ChangeStatusRepository changeStatusRepository;
    private final ChangeStatusGetDtoMapping mappingChangeStatusGetDto;
    private final DozerBeanMapper mapper;

    @Autowired
    public ChangeStatusServiceImpl(ChangeStatusRepository changeStatusRepository,
                                   ChangeStatusGetDtoMapping mappingChangeStatusGetDto,
                                   DozerBeanMapper mapper){
        this.changeStatusRepository = changeStatusRepository;
        this.mappingChangeStatusGetDto = mappingChangeStatusGetDto;
        this.mapper = mapper;
        mapper.setMappingFiles(List.of("dozer/changeStatusMapping.xml"));
    }

    @Override
    @Transactional
    public List<ChangeStatusGetDto> getAll(){
        return changeStatusRepository
                .findAll()
                .stream()
                .map((changeStatus) -> mapper.map(changeStatus, ChangeStatusGetDto.class))
                //.map(mappingChangeStatusGetDto::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<ChangeStatusGetDto> getAllByBeginTaskStatusId(Integer beginStatusId){
        return changeStatusRepository
                .getAllByBeginTaskStatusId(beginStatusId)
                .stream()
                .map(mappingChangeStatusGetDto::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<ChangeStatusGetDto> getAllByEndTaskStatusId(Integer endStatusId){
        return changeStatusRepository
                .getAllByEndTaskStatusId(endStatusId)
                .stream()
                //.map()
                .map(mappingChangeStatusGetDto::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public ChangeStatusGetDto getById(Integer changeStatusId){
        return mappingChangeStatusGetDto
                .mapToDto(changeStatusRepository
                        .findById(changeStatusId)
                        .orElseThrow(() -> new MyEntityNotFoundException(changeStatusId)));
    }
}
