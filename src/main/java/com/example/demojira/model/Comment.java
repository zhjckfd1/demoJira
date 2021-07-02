package com.example.demojira.model;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

//@Data   (Lombok)   ?
@Entity
@Schema(description = "Сущность комментария")
@Table(name = "comments") //, schema="SONIN"
public class Comment {
    //toString() для корректного вывода?

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
    //@JoinColumn(name="task_id")
    @Schema(description = "задача, к которой написан комментарий")
    @ManyToOne
    private Task task;


    /*
    @Schema(description = "id задачи, к которой относится комментарий", accessMode = Schema.AccessMode.READ_ONLY)
    @Column(name = "task_id")
    private Integer taskId;
     */

    //@JoinColumn(name="employee_id")
    @Schema(description = "сотрудник, написавший комментарий")
    @ManyToOne
    private Employee employee;

    /*
    @Schema(description = "id сотрудника, создавшего комментарий", accessMode = Schema.AccessMode.READ_ONLY)
    @Column(name = "employee_id")
    private Integer employeeId;
    */

    @Schema(description = "текст комментария", example = "текст комментария")
    @Column(name = "text")
    private String text;

    @Schema(description = "дата создания комментария", accessMode = Schema.AccessMode.READ_ONLY)
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
