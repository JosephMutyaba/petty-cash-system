package com.pahappa.systems.pettycashsystem.spring.dao;

import com.pahappa.systems.pettycashsystem.spring.models.Requisition;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.List;

@Repository
public class RequisitionDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public RequisitionDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void createRequisition(Requisition requisition) {
        sessionFactory.getCurrentSession().save(requisition);
    }

    public Requisition getRequisitionById(Long id) {
        return sessionFactory.getCurrentSession().get(Requisition.class, id);
    }

    public List<Requisition> getAllRequisitions() {
        return sessionFactory.getCurrentSession()
                .createQuery("FROM Requisition", Requisition.class)
                .getResultList();
    }

    public void updateRequisition(Requisition requisition) {
        sessionFactory.getCurrentSession().update(requisition);
    }

    public void deleteRequisition(Long id) {
        Requisition requisition = sessionFactory.getCurrentSession().load(Requisition.class, id);
        if (requisition != null) {
            sessionFactory.getCurrentSession().delete(requisition);
        }
    }

    public List<Requisition> getAllRequisitionsByStatus(String req_status) {
        return sessionFactory.getCurrentSession().createQuery("FROM Requisition WHERE status=:reqStatus")
                .setParameter("reqStatus", req_status)
                .getResultList();
    }

    public void deleteRequisitionsByStatus(String status) {
        sessionFactory.getCurrentSession().createQuery("DELETE FROM Requisition WHERE status=:status")
                .setParameter("status", status)
                .executeUpdate();
    }

    public Requisition getRequisitionByUserIdAndStatusAndMaxDateNotExpired(Long userId) {
        return (Requisition) sessionFactory.getCurrentSession().createQuery("FROM Requisition WHERE status IN (:statuses) AND user_id = :userId AND maxDateNeeded > CURRENT_DATE ")
                .setParameterList("statuses", Arrays.asList("Pending", "Approved", "Paid"))
                .setParameter("userId", userId)
                .uniqueResult();
    }

    public List<Requisition> getAllRequisitionsExpiredButNotRejectedAndNotCompleted(Long userId) {
        return sessionFactory.getCurrentSession().createQuery("FROM Requisition WHERE user_id=:userId AND maxDateNeeded<CURRENT_DATE AND status IN (:statuses)")
                .setParameter("userId",userId)
                .setParameter("statuses", Arrays.asList("Pending", "Approved", "Paid"))
                .getResultList();
    }

    public List<Requisition> getAllRequisitionsByStatusAndUserId(String status, Long userId) {
        return sessionFactory.getCurrentSession().createQuery("FROM Requisition WHERE user_id=:userId AND status=:reqStatus AND maxDateNeeded > CURRENT_DATE ")
                .setParameter("userId", userId)
                .setParameter("reqStatus",status)
                .getResultList();
    }
}
