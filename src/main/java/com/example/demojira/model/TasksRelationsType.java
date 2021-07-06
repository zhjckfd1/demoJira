package com.example.demojira.model;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
//@Hidden
//@Schema(description = "Сущность связи задач")
@Table(name = "tasks_relations_types")
public class TasksRelationsType {

    @Id
    //@Schema(description = "id связи", accessMode = Schema.AccessMode.READ_ONLY)
    @Column(name = "relation_id")
    //field
    //@SequenceGenerator(name = "stri", sequenceName="sequence_tasks_relationship_id", allocationSize=1)
    //@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stri")
    private Integer id;

    //@Schema(description = "вид связи между задачами", example = "блокирует")
    @Column(name = "relation_type")
    private String relationType;

    //@JoinColumn(name = "relationship_id")
    @OneToMany(mappedBy = "tasksRelationsType")
    private List<TasksRelationship> tasksRelationships;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRelationType() {
        return relationType;
    }

    public void setRelationType(String relationType) {
        this.relationType = relationType;
    }

    //enum?
    //@Converter?      //https://www.baeldung.com/jpa-persisting-enums-in-jpa   ?
    //добавляем через liquibase?
    //https://www.baeldung.com/jpa-entities     (2.7) (сломается, если переименовать значения?)
}
