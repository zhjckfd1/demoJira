package com.example.demojira.service.mapping;

import com.example.demojira.dto.TaskGetDto;
import com.example.demojira.model.Task;
import org.springframework.stereotype.Service;

@Service
public class TaskGetDtoMapping {
    public TaskGetDto mapToDto(Task entity) {
        TaskGetDto taskGetDto = new TaskGetDto();
        taskGetDto.setDescription(entity.getDescription());
        taskGetDto.setTitle(entity.getTitle());
        taskGetDto.setTaskStatusId(entity.getStatus().getId());
        taskGetDto.setEmployeeId(entity.getEmployee().getId());
        taskGetDto.setRegisteredDate(entity.getRegisteredDate());
        return taskGetDto;
    }
}
