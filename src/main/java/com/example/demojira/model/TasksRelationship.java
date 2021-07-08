package com.example.demojira.model;

import javax.persistence.*;

@Entity
@Table(name = "tasks_relationships")
public class TasksRelationship {
    @Id
    @Column(name = "relationship_id")
    @SequenceGenerator(name = "stri", sequenceName="sequence_tasks_relationship_id", allocationSize=1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "stri")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "source_task_id")
    private Task sourceTask;

    @ManyToOne
    @JoinColumn(name = "subject_task_id")
    private Task subjectTask;

    @ManyToOne
    @JoinColumn(name = "relation_id")
    private TasksRelationsType tasksRelationsType;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Task getSourceTask() {
        return sourceTask;
    }

    public void setSourceTask(Task sourceTask) {
        this.sourceTask = sourceTask;
    }

    public Task getSubjectTask() {
        return subjectTask;
    }

    public void setSubjectTask(Task subjectTask) {
        this.subjectTask = subjectTask;
    }

    public TasksRelationsType getTasksRelationsType() {
        return tasksRelationsType;
    }

    public void setTasksRelationsType(TasksRelationsType tasksRelationsType) {
        this.tasksRelationsType = tasksRelationsType;
    }
}
