package com.example.demojira.service.mapping;

import com.example.demojira.dto.ReportAddDto;
import com.example.demojira.model.Employee;
import com.example.demojira.model.Report;
import com.example.demojira.model.Task;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ReportAddDtoMapping {
    public Report mapToEntity(ReportAddDto reportAddDto, Task task, Employee employee) {
        Report report = new Report();
        report.setTask(task);
        report.setEmployee(employee);
        report.setCreatedDate(LocalDateTime.now());
        report.setDescription(reportAddDto.getDescription());

        //аннотируем DTO @NotNull ?

        //отсутствие id связи обрабатываем IllegalArgumentException ?
        //Optional в DTO (если null - исключение)?     (свое исключение или IllegalArgumentException?)
        //просто через if-else?
        //nullable = false?   (hibernate исключение, лишние связи, не нужно?) (org.hibernate.PropertyValueException)
        //@NotNull ?   (javax.validation.ConstraintViolationException)
        //неправильно понял @Repository? (https://www.baeldung.com/spring-component-repository-service)

        report.setTimeSpent(reportAddDto.getTimeSpent());
        return report;
    }
}
