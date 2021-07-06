package com.example.demojira.dto;

import com.example.demojira.model.Task;
import com.example.demojira.model.TasksRelationsType;

public class TaskRelationshipDto {
    Integer sourceTaskId;
    Integer subjectTaskId;
    Integer relationId;

    public Integer getSourceTaskId() {
        return sourceTaskId;
    }

    public void setSourceTaskId(Integer sourceTaskId) {
        this.sourceTaskId = sourceTaskId;
    }

    public Integer getSubjectTaskId() {
        return subjectTaskId;
    }

    public void setSubjectTaskId(Integer subjectTaskId) {
        this.subjectTaskId = subjectTaskId;
    }

    public Integer getRelationId() {
        return relationId;
    }

    public void setRelationId(Integer relationId) {
        this.relationId = relationId;
    }
}
