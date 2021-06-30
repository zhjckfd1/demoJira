package com.example.demojira.model;

import javax.persistence.*;
import javax.xml.crypto.Data;
import java.util.Date;

@Entity
@Table(name = "comments")
public class Comment {
    @Id
    @Column(name = "Id_comment")
    @SequenceGenerator(name = "sci", sequenceName="sequence_comments_id", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sci")
    private Integer id;

    /*
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "client_id", nullable = false)
    */

    @Column(name = "Id_task")
    private Integer idTask;

    @Column(name = "Id_employee")
    private Integer idEmployee;

    @Column(name = "text")
    private String text;

    @Column(name = "date_of_creation")
    private Date dateOfCreation;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdTask() {
        return idTask;
    }

    public void setIdTask(Integer idTask) {
        this.idTask = idTask;
    }

    public Integer getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(Integer idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDateOfCreation() {
        return dateOfCreation;
    }

    public void setDateOfCreation(Date dateOfCreation) {
        this.dateOfCreation = dateOfCreation;
    }
}
