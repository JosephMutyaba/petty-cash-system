package com.pahappa.systems.pettycashsystem.spring.dao;

import com.pahappa.systems.pettycashsystem.spring.models.Accountability;
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

    public List<Requisition> getAllRequisitions() {
        return sessionFactory.getCurrentSession()
                .createQuery("FROM Requisition WHERE deleted=false ", Requisition.class)
                .getResultList();
    }

    public void updateRequisition(Requisition requisition) {
        sessionFactory.getCurrentSession().saveOrUpdate(requisition);
    }

    public void deleteRequisition(Long id) {
        Requisition requisition = sessionFactory.getCurrentSession().load(Requisition.class, id);
        if (requisition != null) {
            Accountability accountability = requisition.getAccountability();

            if (accountability != null) {
                accountability.setDeleted(true);
                sessionFactory.getCurrentSession().update(accountability);
            }

            requisition.setDeleted(true);
            sessionFactory.getCurrentSession().update(requisition);
        }
    }

    public List<Requisition> getAllRequisitionsByStatus(String req_status) {
        return sessionFactory.getCurrentSession().createQuery("FROM Requisition WHERE deleted=false AND status=:reqStatus AND maxDateNeeded >CURRENT_TIMESTAMP ORDER BY maxDateNeeded ASC")
                .setParameter("reqStatus", req_status)
                .getResultList();
    }

    public void deleteRequisitionsByStatus(String status) {
        List<Requisition> requisitions = sessionFactory.getCurrentSession().createQuery("FROM Requisition WHERE deleted=false AND status=:status")
                .setParameter("status", status)
                .getResultList();



        if (requisitions != null) {

            for (Requisition requisition:requisitions){
                Accountability accountability = requisition.getAccountability();

                if (accountability != null) {
                    accountability.setDeleted(true);
                    sessionFactory.getCurrentSession().update(accountability);
                }

            }

            sessionFactory.getCurrentSession().createQuery("UPDATE Requisition SET deleted=true where status=:status")
                    .setParameter("status", status)
                    .executeUpdate();

        }




        sessionFactory.getCurrentSession().createQuery("UPDATE Requisition SET deleted= true WHERE status=:status")
                .setParameter("status", status)
                .executeUpdate();
    }

    public Requisition getRequisitionByUserIdAndStatusAndMaxDateNotExpired(Long userId) {
        return (Requisition) sessionFactory.getCurrentSession().createQuery("FROM Requisition WHERE deleted=false AND status IN (:statuses) AND user_id = :userId AND maxDateNeeded >= CURRENT_DATE")
                .setParameterList("statuses", Arrays.asList("Draft","Pending", "Approved", "Paid", "Reviewed"))
                .setParameter("userId", userId)
                .uniqueResult();
    }

    public List<Requisition> getAllRequisitionsExpiredButNotRejectedAndNotCompleted(Long userId) {
        return sessionFactory.getCurrentSession().createQuery("FROM Requisition WHERE deleted=false AND user_id=:userId AND maxDateNeeded<CURRENT_DATE AND status IN (:statuses) ORDER BY maxDateNeeded ASC")
                .setParameter("userId",userId)
                .setParameter("statuses", Arrays.asList("Pending", "Approved", "Paid", "Reviewed"))
                .getResultList();
    }

    public List<Requisition> getAllRequisitionsByStatusAndUserId(String status, Long userId) {
        return sessionFactory.getCurrentSession().createQuery("FROM Requisition WHERE deleted=false AND user_id=:userId AND status=:reqStatus AND maxDateNeeded > CURRENT_DATE ORDER BY maxDateNeeded ASC")
                .setParameter("userId", userId)
                .setParameter("reqStatus",status)
                .getResultList();
    }

    public List<Requisition> getAllExpiredRequisitions() {
        return sessionFactory.getCurrentSession().createQuery("FROM Requisition WHERE deleted=false AND maxDateNeeded<CURRENT_DATE AND status IN (:statuses) ORDER BY maxDateNeeded ASC")
                .setParameter("statuses", Arrays.asList("Pending", "Approved", "Reviewed"))
                .getResultList();
    }

    public List<Requisition> getAllPaidRequisitionsByStatus() {
        return sessionFactory.getCurrentSession().createQuery("FROM Requisition WHERE deleted=false AND status='Paid' ORDER BY maxDateNeeded ASC").getResultList();
    }

    public List<Requisition> getAllCompletedRequisitionsByStatus() {
        return sessionFactory.getCurrentSession().createQuery("FROM Requisition WHERE deleted=false AND status='Completed' ORDER BY maxDateNeeded ASC").getResultList();
    }

    public Requisition retrieveLatestCompletedRequisitionOfCurrentlyLoggedInUser(Long loggedInUserId) {
        Session session = null;
        Requisition latestRequisition = null;
        try {
            session = sessionFactory.getCurrentSession();
            String hql = "FROM Requisition WHERE deleted=false AND user.id = :loggedInUserId AND status = 'Completed' ORDER BY dateAccountabilityWasSubmitted DESC";
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
        return (Requisition) sessionFactory.getCurrentSession().createQuery("FROM Requisition WHERE deleted=false AND accountability_id=:accId")
                .setParameter("accId",accountabilityId)
                .uniqueResult();
    }




    public List<Requisition> getRequisitionsWithSubmittedAccountability() {
        Session session = sessionFactory.getCurrentSession();
        String hql = "FROM Requisition r WHERE deleted=false AND r.accountability.id IS NOT NULL AND r.accountability.status = 'Submitted'";
        Query<Requisition> query = session.createQuery(hql, Requisition.class);
        return query.list();
    }
}
