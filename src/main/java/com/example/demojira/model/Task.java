package com.example.demojira.model;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Schema(description = "Сущность задачи")
@Table(name = "tasks")
public class Task {
    @Id
    @Column(name = "task_id")
    @Schema(description = "id задачи", accessMode = Schema.AccessMode.READ_ONLY)
    @SequenceGenerator(name = "sti", sequenceName="sequence_task_id", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sti")
    private Integer id;

    /*
    @Schema(description = "id статуса задачи", accessMode = Schema.AccessMode.READ_ONLY)
    @Column(name = "status_id")
    private Integer statusId;
    */

    @ManyToOne
    private TaskStatus status;

    //???
    /*
    @Schema(description = "id сотрудника, выполняющего задачу", accessMode = Schema.AccessMode.READ_ONLY)
    @Column(name = "employee_id")
    private Integer employeeId;
    */

    @ManyToOne
    private Employee employee;

    @Schema(description = "наименование задачи", example = "Тестовая задача")
    @Column(name = "title")
    private String title;

    @Schema(description = "описание задачи", example = "Описание текущей задачи")
    @Column(name = "description")
    private String description;

    @Schema(description = "дата создания задачи", accessMode = Schema.AccessMode.READ_ONLY)
    @Column(name = "registered_date")
    private Date registeredDate;

    //@JoinColumn(name="task_id")
    @OneToMany(mappedBy = "task")
    private List<Comment> comments;

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
