package com.example.demojira.service;

import com.example.demojira.dto.ReportAddDto;
import com.example.demojira.dto.ReportGetDto;

import java.util.List;

public interface ReportService {

    void createReport(ReportAddDto reportAddDto);

    List<ReportGetDto> getAll();

    List<ReportGetDto> getAllReportsOnTheTaskId(Integer taskId);

    List<ReportGetDto> getAllReportsOnTheEmployeeId(Integer employeeId);

    ReportGetDto getById(Integer reportId);
}
