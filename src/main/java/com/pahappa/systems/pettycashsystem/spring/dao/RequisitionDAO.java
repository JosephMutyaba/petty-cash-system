package com.pahappa.systems.pettycashsystem.spring.dao;

import com.pahappa.systems.pettycashsystem.spring.models.Requisition;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
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
        sessionFactory.getCurrentSession().saveOrUpdate(requisition);
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
        sessionFactory.getCurrentSession().saveOrUpdate(requisition);
    }

    public void deleteRequisition(Long id) {
        Requisition requisition = sessionFactory.getCurrentSession().load(Requisition.class, id);
        if (requisition != null) {
            sessionFactory.getCurrentSession().delete(requisition);
        }
    }

    public List<Requisition> getAllRequisitionsByStatus(String req_status) {
        return sessionFactory.getCurrentSession().createQuery("FROM Requisition WHERE status=:reqStatus AND maxDateNeeded >CURRENT_TIMESTAMP ORDER BY maxDateNeeded ASC")
                .setParameter("reqStatus", req_status)
                .getResultList();
    }

    public void deleteRequisitionsByStatus(String status) {
        sessionFactory.getCurrentSession().createQuery("DELETE FROM Requisition WHERE status=:status")
                .setParameter("status", status)
                .executeUpdate();
    }

    public Requisition getRequisitionByUserIdAndStatusAndMaxDateNotExpired(Long userId) {
        return (Requisition) sessionFactory.getCurrentSession().createQuery("FROM Requisition WHERE status IN (:statuses) AND user_id = :userId AND maxDateNeeded >= CURRENT_DATE")
                .setParameterList("statuses", Arrays.asList("Draft","Pending", "Approved", "Paid", "Accepted"))
                .setParameter("userId", userId)
                .uniqueResult();
    }

    public List<Requisition> getAllRequisitionsExpiredButNotRejectedAndNotCompleted(Long userId) {
        return sessionFactory.getCurrentSession().createQuery("FROM Requisition WHERE user_id=:userId AND maxDateNeeded<CURRENT_DATE AND status IN (:statuses) ORDER BY maxDateNeeded ASC")
                .setParameter("userId",userId)
                .setParameter("statuses", Arrays.asList("Pending", "Approved", "Paid", "Accepted"))
                .getResultList();
    }

    public List<Requisition> getAllRequisitionsByStatusAndUserId(String status, Long userId) {
        return sessionFactory.getCurrentSession().createQuery("FROM Requisition WHERE user_id=:userId AND status=:reqStatus AND maxDateNeeded > CURRENT_DATE ORDER BY maxDateNeeded ASC")
                .setParameter("userId", userId)
                .setParameter("reqStatus",status)
                .getResultList();
    }

    public List<Requisition> getAllExpiredRequisitions() {
        return sessionFactory.getCurrentSession().createQuery("FROM Requisition WHERE maxDateNeeded<CURRENT_DATE AND status IN (:statuses) ORDER BY maxDateNeeded ASC")
                .setParameter("statuses", Arrays.asList("Pending", "Approved", "Accepted"))
                .getResultList();
    }

    public List<Requisition> getAllPaidRequisitionsByStatus() {
        return sessionFactory.getCurrentSession().createQuery("FROM Requisition WHERE status='Paid' ORDER BY maxDateNeeded ASC").getResultList();
    }

    public List<Requisition> getAllCompletedRequisitionsByStatus() {
        return sessionFactory.getCurrentSession().createQuery("FROM Requisition WHERE status='Completed' ORDER BY maxDateNeeded ASC").getResultList();
    }

//    public Requisition retrieveLatestCompletedRequisitionOfCurrentlyLoggedInUser(Long loggedInUserId) {
//        return (Requisition) sessionFactory.getCurrentSession().createQuery("FROM Requisition WHERE user_id=:loggedInUserId AND status='Completed' ORDER BY dateAccountabilityWasSubmitted DESC")
//                .setParameter("loggedInUserId",loggedInUserId)
//                .setMaxResults(1);
//    }

    public Requisition retrieveLatestCompletedRequisitionOfCurrentlyLoggedInUser(Long loggedInUserId) {
        Session session = null;
        Requisition latestRequisition = null;
        try {
            session = sessionFactory.getCurrentSession();
            String hql = "FROM Requisition WHERE user.id = :loggedInUserId AND status = 'Completed' ORDER BY dateAccountabilityWasSubmitted DESC";
            Query<Requisition> query = session.createQuery(hql, Requisition.class);
            query.setParameter("loggedInUserId", loggedInUserId);
            query.setMaxResults(1);
            latestRequisition = query.uniqueResult();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return latestRequisition;
    }

    public Requisition getRequisitionByAccountabilityId(Long accountabilityId) {
        return (Requisition) sessionFactory.getCurrentSession().createQuery("FROM Requisition WHERE accountability_id=:accId")
                .setParameter("accId",accountabilityId)
                .uniqueResult();
    }




    public List<Requisition> getRequisitionsWithSubmittedAccountability() {
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM Requisition r WHERE r.accountability.id IS NOT NULL AND r.accountability.status = 'Submitted'";
        Query<Requisition> query = session.createQuery(hql, Requisition.class);
        return query.list();
    }
}
