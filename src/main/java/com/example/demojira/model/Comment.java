package com.example.demojira.model;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @Column(name = "comment_id")
    @SequenceGenerator(name = "sci", sequenceName="sequence_comment_id", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sci")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    @Column(name = "text")
    private String text;

    //@Temporal(TemporalType.DATE)
    @Column(name = "created_date")
    private Date createdDate;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date registeredDate) {
        this.createdDate = registeredDate;
    }
}
