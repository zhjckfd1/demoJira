package com.example.demojira.model;

import org.dozer.Mapping;

import javax.persistence.*;

@Table(name = "changes_status")
@Entity
public class ChangeStatus {

    @Id
    @Column(name = "change_status_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "begin_status_id")
    private TaskStatus beginTaskStatus;

    @ManyToOne
    @JoinColumn(name = "end_status_id")
    private TaskStatus endTaskStatus;

    public TaskStatus getEndTaskStatus() {
        return endTaskStatus;
    }

    public void setEndTaskStatus(TaskStatus endTaskStatus) {
        this.endTaskStatus = endTaskStatus;
    }

    public TaskStatus getBeginTaskStatus() {
        return beginTaskStatus;
    }

    public void setBeginTaskStatus(TaskStatus beginTaskStatus) {
        this.beginTaskStatus = beginTaskStatus;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /*
    @Mapping("beginStatusId")
    public Integer getBeginTaskStatusIdMapping(){
        //без Mapping в конце - конфликт (с "List<ChangeStatus> getAllByBeginTaskStatusId(Integer id);" в ChangeStatusRepository)
        return beginTaskStatus.getId();
    }

    @Mapping("endStatusId")
    public Integer getEndTaskStatusIdMapping(){
        return endTaskStatus.getId();
    }*/
}