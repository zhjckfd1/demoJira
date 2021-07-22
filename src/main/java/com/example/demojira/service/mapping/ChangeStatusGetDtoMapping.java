package com.example.demojira.service.mapping;

import com.example.demojira.dto.ChangeStatusGetDto;
import com.example.demojira.model.ChangeStatus;
import org.springframework.stereotype.Service;

@Service
public class ChangeStatusGetDtoMapping {
    public ChangeStatusGetDto mapToDto(ChangeStatus entity) {
        ChangeStatusGetDto changeStatusGetDto = new ChangeStatusGetDto();
        changeStatusGetDto.setBeginStatusId(entity.getBeginTaskStatus().getId());
        changeStatusGetDto.setEndStatusId(entity.getEndTaskStatus().getId());
        return changeStatusGetDto;
    }
}
