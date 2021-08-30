package com.example.demojira.repository.custom;

import com.example.demojira.dto.ReportGetDto;
import com.example.demojira.model.Report;

import java.util.List;
import java.util.function.Predicate;

public interface ReportRepositoryCustom {
    List<Report> getAllByCriteria(Integer taskId, Integer employeeId);
}
