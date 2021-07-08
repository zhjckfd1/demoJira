package com.example.demojira.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tasks_statuses")
public class TaskStatus {
    @Id
    @Column(name = "status_id")
    private Integer id;

    @Column(name = "status")
    private String status;

    @Column(name = "code")
    private String code;

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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Task> getTasks() {
        return tasks;
    }
}
