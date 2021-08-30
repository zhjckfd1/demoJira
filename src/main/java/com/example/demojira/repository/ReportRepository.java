package com.example.demojira.repository;

import com.example.demojira.model.Report;
import com.example.demojira.repository.custom.ReportRepositoryCustom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepository extends JpaRepository<Report, Integer>, ReportRepositoryCustom {

    List<Report> getAllByTaskIdOrderByCreatedDate(Integer taskId);

    List<Report> getAllByEmployeeIdOrderByCreatedDate(Integer employeeId);

}
