package com.example.demojira.dto;

public class ChangeStatusGetDto {
    private Integer beginStatusId;
    private Integer endStatusId;

    public Integer getBeginStatusId() {
        return beginStatusId;
    }

    public void setBeginStatusId(Integer beginStatusId) {
        this.beginStatusId = beginStatusId;
    }

    public Integer getEndStatusId() {
        return endStatusId;
    }

    public void setEndStatusId(Integer endStatusId) {
        this.endStatusId = endStatusId;
    }
}
