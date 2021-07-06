package com.example.demojira.model;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.*;
import java.util.List;

@Entity
//@Hidden
//@Schema(description = "Статус задачи")
@Table(name = "tasks_statuses")
public class TaskStatus {
    @Id
    @Column(name = "status_id")
    //@Schema(description = "id статуса", accessMode = Schema.AccessMode.READ_ONLY)
    //@SequenceGenerator(name = "sti", sequenceName="sequence_task_id", allocationSize=1)
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sti")
    private Integer id;

    //@Schema(description = "название статуса", accessMode = Schema.AccessMode.READ_ONLY)
    @Column(name = "status")
    private String status;

    @OneToMany(mappedBy = "status")
    private List<Task> tasks;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    //enum?
    //добавляем через liquibase?
    //https://www.baeldung.com/jpa-entities     (2.7) (сломается, если переименовать значения?)
}
