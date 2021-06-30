package com.example.demojira.model;

import io.swagger.v3.oas.annotations.media.Schema;
import org.hibernate.annotations.ValueGenerationType;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.Date;

@Entity
@Schema(description = "Сущность сотрудника")
@Table(name = "EMPLOYEES")
public class Employee {
    //добавить имя и фамилию?


    @Id
    @Schema(description = "id сотрудника", accessMode = Schema.AccessMode.READ_ONLY)
    @Column(name = "Id_employee")
    //field
    @SequenceGenerator(name = "sei", sequenceName="sequence_employee_id", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sei")
    private Integer id;

    //@NotBlank      //не должен быть пустым
    //@NonNull
    //@Column(name = "login", nullable = false, unique = true)   //уникальный, не пустой
    @Schema(description = "логин", example = "login")
    @Column(name = "login")
    private String login;

    @Schema(description = "пароль", example = "password")
    @Column(name = "password")
    private String password;

    @Schema(description = "дата регистрации", accessMode = Schema.AccessMode.READ_ONLY)
    @Column(name = "registered_date")
    private Date registeredDate;


    //@Enumerated(EnumType.STRING)
    @Schema(description = "состояние записи (false == заблокирована)", accessMode = Schema.AccessMode.READ_ONLY)
    @Column(name = "status")
    private Boolean status;

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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
