package com.example.demojira.repository;

import com.example.demojira.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Employee findByLogin(String login);
}
