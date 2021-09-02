package com.example.demojira.service.mapping;

import com.example.demojira.dto.ReportGetDto;
import com.example.demojira.model.Report;
import org.springframework.stereotype.Service;

@Service
public class ReportGetDtoMapping {
    public ReportGetDto mapToDto(Report report) {
        ReportGetDto reportDto = new ReportGetDto();
        reportDto.setCreatedDate(report.getCreatedDate());
        reportDto.setDescription(report.getDescription());
        reportDto.setEmployeeId(report.getEmployee().getId());
        reportDto.setTaskId(report.getTask().getId());
        reportDto.setSpentTime(report.getSpentTime());
        return reportDto;
    }
}
