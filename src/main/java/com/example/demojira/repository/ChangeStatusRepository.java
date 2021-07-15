package com.example.demojira.repository;

import com.example.demojira.model.ChangeStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChangeStatusRepository extends JpaRepository<ChangeStatus, Integer> {

    List<ChangeStatus> getAllByBeginTaskStatusId(Integer id);

}