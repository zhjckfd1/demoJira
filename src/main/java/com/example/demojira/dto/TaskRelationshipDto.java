package com.example.demojira.dto;

public class TaskRelationshipDto {
    private Integer sourceTaskId;
    private Integer subjectTaskId;
    private Integer relationId;

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
