package com.example.demojira.model;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reports")
public class Report {

    @Id
    @Column(name = "report_id")
    @SequenceGenerator(name = "sri", sequenceName="sequence_report_id", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sri")
    private Integer id;

    //, nullable = false
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    //проверяем через DTO => проверки убираем?
    //@NotNull
    //@NotEmpty
    //@NotBlank(message = "Description is mandatory")
    @Column(name = "description")
    private String description;

    //храним в секундах
    @Column(name = "time_spent")
    private Long spentTime;

    //@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date", columnDefinition = "TIMESTAMP")
    private LocalDateTime createdDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getSpentTime() {
        return spentTime;
    }

    public void setSpentTime(Long timeSpent) {
        this.spentTime = timeSpent;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
}
