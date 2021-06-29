package com.example.demojira.model;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "EMPLOYEES")
public class Employee {
    //добавить имя и фамилию?

    @Id
    @Column(name = "Id_employee")
    //field
    @SequenceGenerator(name = "sei", sequenceName="sequence_employee_id", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sei")
    private Integer id;

    //@NotBlank      //не должен быть пустым
    @Column(name = "login")
    private String login;

    @Column(name = "password")
    private String password;

    @Column(name = "registered_date")
    private Date registeredDate;

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
}
