package com.example.demojira.model;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.persistence.*;

@Entity
@Hidden
@Schema(description = "Сущность связанных задач")
@Table(name = "tasks_relationships")
public class TasksRelationship {
    @Id
    @Schema(description = "id отношения связи", accessMode = Schema.AccessMode.READ_ONLY)
    @Column(name = "relationship_id")
    @SequenceGenerator(name = "stri", sequenceName="sequence_tasks_relationship_id", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stri")
    private Integer id;

    /*
    //какое наименование является корректным?  Как связываем?
    @Schema(description = "Id основной задачи", accessMode = Schema.AccessMode.READ_ONLY)
    @Column(name = "task1_id")
    private String task1Id;
    */

    @OneToOne
    @Schema(description = "основная задача")
    private Task task1;


    /*
    //какое наименование является корректным?  Как связываем?
    @Schema(description = "Id дополнительной задачи", accessMode = Schema.AccessMode.READ_ONLY)
    @Column(name = "task2_id")
    private String task2Id;*/

    @OneToOne
    @Schema(description = "дополнительная задача")
    private Task task2;

    /*
    //какое наименование является корректным?  Как связываем?
    @Schema(description = "Id связи между задачами (основной к дополнительной)", accessMode = Schema.AccessMode.READ_ONLY)
    @Column(name = "relation_id")
    private String relationId;
    */

    @Schema(description = "тип связи между задачами (основной к дополнительной)", accessMode = Schema.AccessMode.READ_ONLY)
    @ManyToOne
    private TasksRelationsType tasksRelationsType;

}
