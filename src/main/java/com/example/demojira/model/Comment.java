package com.example.demojira.model;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

//@Data   (Lombok)   ?
@Entity
//@Schema(description = "Сущность комментария")
@Table(name = "comments") //, schema="SONIN"
public class Comment {
    //toString() для корректного вывода?
    //именуем как в БД (и класс, и переменные?)?

    @Id
    @Column(name = "comment_id")
    //@Schema(description = "id комментария", accessMode = Schema.AccessMode.READ_ONLY)
    @SequenceGenerator(name = "sci", sequenceName="sequence_comment_id", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sci")
    private Integer id;

    //@Schema(description = "задача, к которой написан комментарий")
    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    //@Schema(description = "сотрудник, написавший комментарий")
    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    //@Schema(description = "текст комментария", example = "текст комментария")
    @Column(name = "text")
    private String text;

    //@Schema(description = "дата создания комментария", accessMode = Schema.AccessMode.READ_ONLY)
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
