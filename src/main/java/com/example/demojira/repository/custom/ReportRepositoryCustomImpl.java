package com.example.demojira.repository.custom;

import com.example.demojira.model.Report;
import com.example.demojira.model.Report_;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ReportRepositoryCustomImpl implements ReportRepositoryCustom{

    @PersistenceContext
    EntityManager em;

    @Override
    public List<Report> getAllByCriteria(Integer taskId, Integer employeeId) {
        CriteriaBuilder criteriaBuilder = em.getCriteriaBuilder();
        CriteriaQuery<Report> criteriaQuery = criteriaBuilder.createQuery(Report.class);
        Root<Report> root = criteriaQuery.from(Report.class);

        List<Predicate> predicates = new ArrayList<>();
        if (taskId != null) {
            predicates.add(criteriaBuilder.equal(root.get(Report_.TASK), taskId));
        }
        if (employeeId != null) {
            predicates.add(criteriaBuilder.equal(root.get(Report_.EMPLOYEE), employeeId));
        }

        criteriaQuery.where(predicates.toArray(new Predicate[0]));

        return em.createQuery(criteriaQuery).getResultList();
    }
}
