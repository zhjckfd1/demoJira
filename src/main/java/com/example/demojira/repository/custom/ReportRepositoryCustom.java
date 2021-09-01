package com.example.demojira.repository.custom;

import com.example.demojira.model.Report;

import java.util.List;

public interface ReportRepositoryCustom {
    List<Report> getAllByCriteria(Integer taskId, Integer employeeId);
}
