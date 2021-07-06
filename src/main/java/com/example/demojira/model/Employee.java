package com.example.demojira.model;

import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
//@Schema(description = "Сущность сотрудника")
@Table(name = "EMPLOYEES")
public class Employee {
    //добавить имя и фамилию?

    @Id
    //@Schema(description = "id сотрудника", accessMode = Schema.AccessMode.READ_ONLY)
    @Column(name = "employee_id")
    //field
    @SequenceGenerator(name = "sei", sequenceName="sequence_employee_id", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sei")
    private Integer id;

    //@NotBlank      //не должен быть пустым
    //@NonNull
    //@Column(name = "login", nullable = false, unique = true)   //уникальный, не пустой
    //@Schema(description = "логин", example = "login")
    @Column(name = "login")
    private String login;

    //@Schema(description = "пароль", example = "password")
    @Column(name = "password")
    private String password;

    //@Schema(description = "дата регистрации", accessMode = Schema.AccessMode.READ_ONLY)
    @Column(name = "registered_date")
    private Date registeredDate;


    //@Enumerated(EnumType.STRING)
    //@Schema(description = "состояние сотрудника (false == заблокирован)", accessMode = Schema.AccessMode.READ_ONLY)
    @Column(name = "active")
    private Boolean active;

    //@JoinColumn(name="employee_id")
    //set?
    @OneToMany(mappedBy = "employee")
    private List<Comment> comments;

    @OneToMany(mappedBy = "employee")
    private List<Task> tasks;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegisteredDate() {
        return registeredDate;
    }

    public void setRegisteredDate(Date registeredDate) {
        this.registeredDate = registeredDate;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
