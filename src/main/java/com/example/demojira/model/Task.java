package com.example.demojira.model;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
//@Schema(description = "Сущность задачи")
@Table(name = "tasks")
public class Task {
    @Id
    @Column(name = "task_id")
    //@Schema(description = "id задачи", accessMode = Schema.AccessMode.READ_ONLY)
    @SequenceGenerator(name = "sti", sequenceName="sequence_task_id", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sti")
    private Integer id;


    @ManyToOne
    @JoinColumn(name = "status_id")
    private TaskStatus status;

    @ManyToOne//(fetch = FetchType.EAGER)
    @JoinColumn(name = "employee_id")
    private Employee employee;

    //@Schema(description = "наименование задачи", example = "Тестовая задача")
    @Column(name = "title")
    private String title;

    //@Schema(description = "описание задачи", example = "Описание текущей задачи")
    @Column(name = "description")
    private String description;

    //@Schema(description = "дата создания задачи", accessMode = Schema.AccessMode.READ_ONLY)
    @Column(name = "registered_date")
    private Date registeredDate;

    //@JoinColumn(name="task_id")
    @OneToMany(mappedBy = "task")
    private List<Comment> comments;

    @OneToMany(mappedBy = "sourceTask")
    private List<TasksRelationship> tasksRelationshipsSource;

    @OneToMany(mappedBy = "subjectTask")
    private List<TasksRelationship> tasksRelationshipsSubject;

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
