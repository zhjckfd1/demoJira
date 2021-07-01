package com.example.demojira.model;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.*;
import javax.xml.crypto.Data;
import java.util.Date;

@Entity
@Schema(description = "Сущность комментария")
@Table(name = "comments")
public class Comment {
    @Id
    @Column(name = "comment_id")
    @Schema(description = "id комментария", accessMode = Schema.AccessMode.READ_ONLY)
    @SequenceGenerator(name = "sci", sequenceName="sequence_comments_id", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sci")
    private Integer id;

    /*
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "client_id", nullable = false)
    */

    @Schema(description = "id задачи, к которой относится комментарий", accessMode = Schema.AccessMode.READ_ONLY)
    @Column(name = "task_id")
    private Integer taskId;

    @Schema(description = "id сотрудника, создавшего комментарий", accessMode = Schema.AccessMode.READ_ONLY)
    @Column(name = "employee_id")
    private Integer employeeId;

    @Schema(description = "текст комментария", example = "текст комментария")
    @Column(name = "text")
    private String text;

    @Schema(description = "дата создания комментария", accessMode = Schema.AccessMode.READ_ONLY)
    @Column(name = "registered_date")
    private Date registeredDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public Integer getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Integer employeeId) {
        this.employeeId = employeeId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(Date registeredDate) {
        this.registeredDate = registeredDate;
    }
}
