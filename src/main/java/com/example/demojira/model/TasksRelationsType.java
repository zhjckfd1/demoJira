package com.example.demojira.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "tasks_relations_types")
public class TasksRelationsType {

    @Id
    @Column(name = "relation_id")
    private Integer id;

    @Column(name = "relation_type")
    private String relationType;

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
}
