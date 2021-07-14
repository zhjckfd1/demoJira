package com.example.demojira.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tasks")
public class Task {
    @Id
    @Column(name = "task_id")
    @SequenceGenerator(name = "sti", sequenceName="sequence_task_id", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sti")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private TaskStatus status;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "registered_date")
    private Date registeredDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(Date registeredDate) {
        this.registeredDate = registeredDate;
    }
}
