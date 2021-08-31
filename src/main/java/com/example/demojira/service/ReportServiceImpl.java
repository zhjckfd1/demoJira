package com.example.demojira.service;

import com.example.demojira.dto.ReportAddDto;
import com.example.demojira.dto.ReportCriteriaDto;
import com.example.demojira.dto.ReportGetDto;
import com.example.demojira.exceptions.MyEntityNotFoundException;
import com.example.demojira.model.Employee;
import com.example.demojira.model.Report;
import com.example.demojira.model.Task;
import com.example.demojira.repository.EmployeeRepository;
import com.example.demojira.repository.ReportRepository;
import com.example.demojira.repository.TaskRepository;
import com.example.demojira.service.mapping.ReportAddDtoMapping;
import com.example.demojira.service.mapping.ReportGetDtoMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportServiceImpl implements ReportService {

    private final ReportRepository reportRepository;
    private final TaskRepository taskRepository;
    private final EmployeeRepository employeeRepository;
    private final ReportAddDtoMapping reportAddDtoMapping;
    private final ReportGetDtoMapping reportGetDtoMapping;

    private static final String employeeNotFoundMessage = "Не найден сотрудник с id = ";
    private static final String taskNotFoundMessage = "Не найдена задача с id = ";

    @Autowired
    public ReportServiceImpl(ReportRepository reportRepository,
                             TaskRepository taskRepository,
                             EmployeeRepository employeeRepository,
                             ReportAddDtoMapping reportAddDtoMapping,
                             ReportGetDtoMapping reportGetDtoMapping) {
        this.reportRepository = reportRepository;
        this.taskRepository = taskRepository;
        this.employeeRepository = employeeRepository;
        this.reportAddDtoMapping = reportAddDtoMapping;
        this.reportGetDtoMapping = reportGetDtoMapping;
    }

    @Override
    @Transactional
    public void createReport(ReportAddDto reportAddDto) {
        Task task = taskRepository.findById(reportAddDto.getTaskId())
                .orElseThrow(() -> new MyEntityNotFoundException(
                        taskNotFoundMessage + reportAddDto.getTaskId()));
        Employee employee = employeeRepository.findById(reportAddDto.getEmployeeId())
                .orElseThrow(() -> new MyEntityNotFoundException(
                        employeeNotFoundMessage + reportAddDto.getEmployeeId()));

        Report report = reportAddDtoMapping.mapToEntity(reportAddDto, task, employee);
        reportRepository.save(report);
    }

    @Override
    @Transactional
    public List<ReportGetDto> getAll() {
        return reportRepository.findAll()
                .stream()
                .map(reportGetDtoMapping::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<ReportGetDto> getAllReportsOnTheTaskId(Integer taskId) {
        return reportRepository.getAllByTaskIdOrderByCreatedDate(taskId)
                .stream()
                .map(reportGetDtoMapping::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<ReportGetDto> getAllReportsOnTheEmployeeId(Integer employeeId) {
        return reportRepository.getAllByEmployeeIdOrderByCreatedDate(employeeId)
                .stream()
                .map(reportGetDtoMapping::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<ReportGetDto> getAllByCriteria(ReportCriteriaDto reportCriteriaDto) {
        return reportRepository.getAllByCriteria(reportCriteriaDto.getTaskId(), reportCriteriaDto.getEmployeeId())
                .stream()
                .map(reportGetDtoMapping::mapToDto)
                .collect(Collectors.toList());
        //переименовываем в ReportGetDtoConverter?
        //попробовать библиотеку ModelMapper? (в теории избавимся от пакета mapping)
    }

    @Override
    @Transactional
    public ReportGetDto getById(Integer reportId) {
        return reportGetDtoMapping
                .mapToDto(reportRepository
                        .findById(reportId)
                        .orElseThrow(() -> new MyEntityNotFoundException(reportId)));
    }
}
