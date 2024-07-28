package com.pahappa.systems.pettycashsystem.spring.dao;

import com.pahappa.systems.pettycashsystem.spring.models.Accountability;
import com.pahappa.systems.pettycashsystem.spring.models.BudgetLine;
import com.pahappa.systems.pettycashsystem.spring.models.Requisition;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class BudgetLineDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public BudgetLineDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void createBudgetLine(BudgetLine budgetLine) {
        sessionFactory.getCurrentSession().save(budgetLine);
    }

    public BudgetLine getBudgetLineById(Long id) {
        return sessionFactory.getCurrentSession().get(BudgetLine.class, id);
    }

    public List<BudgetLine> getAllBudgetLines() {
        return sessionFactory.getCurrentSession()
                .createQuery("FROM BudgetLine ORDER BY id DESC", BudgetLine.class)
                .getResultList();
    }

    public void updateBudgetLine(BudgetLine budgetLine) {
        sessionFactory.getCurrentSession().update(budgetLine);
    }

    public void deleteBudgetLine(Long id) {
        BudgetLine budgetLine = sessionFactory.getCurrentSession().load(BudgetLine.class, id);
        if (budgetLine != null) {

            List<Requisition> requisitions = sessionFactory.getCurrentSession().createQuery("FROM Requisition r WHERE r.deleted=false AND r.budgetline.id=:id")
                            .setParameter("id",budgetLine.getId()).getResultList();

            if (requisitions != null) {
                for (Requisition req: requisitions){
                    Accountability acc = req.getAccountability();
                    if (acc != null) {
                        acc.setDeleted(true);
                        sessionFactory.getCurrentSession().update(acc);
                    }
                }

                sessionFactory.getCurrentSession().createQuery("UPDATE Requisition r SET r.deleted = true WHERE r.budgetline.id=:id")
                        .setParameter("id", budgetLine.getId()).executeUpdate();
            }


            budgetLine.setDeleted(true);
            sessionFactory.getCurrentSession().update(budgetLine);
        }
    }

    public List<BudgetLine> getAllBudgetlinesByStatus(String budgetLineStatus) {
        return sessionFactory.getCurrentSession().createQuery("FROM BudgetLine WHERE status=:budgetlineStatus AND endDate>=CURRENT_DATE ORDER BY id DESC")
                .setParameter("budgetlineStatus", budgetLineStatus)
                .getResultList();
    }

    public void deleteBudgetLinesByStatus(String status) {
        List<BudgetLine> budgetLines = sessionFactory.getCurrentSession().createQuery("FROM BudgetLine WHERE deleted=false AND status=:status").setParameter("status",status).getResultList();

        if (budgetLines != null) {
            for (BudgetLine budgetLine:budgetLines){
                List<Requisition> requisitions = sessionFactory.getCurrentSession().createQuery("FROM Requisition r WHERE r.deleted=false AND r.budgetline.id=:id")
                        .setParameter("id",budgetLine.getId())
                        .getResultList();

                if (requisitions != null) {
                    for (Requisition req:requisitions){
                        Accountability acc = req.getAccountability();
                        if (acc != null) {
                            acc.setDeleted(true);
                            sessionFactory.getCurrentSession().update(acc);
                        }
                    }

                    sessionFactory.getCurrentSession().createQuery("UPDATE Requisition r SET r.deleted=true WHERE r.budgetline.id =:id")
                            .setParameter("id",budgetLine.getId())
                            .executeUpdate();

                }
            }
            sessionFactory.getCurrentSession().createQuery("UPDATE BudgetLine b SET b.deleted=true WHERE b.status =:status")
                    .setParameter("status",status)
                    .executeUpdate();
        }
//        sessionFactory.getCurrentSession().createQuery("DELETE FROM BudgetLine WHERE status=:status")
//                .setParameter("status", status)
//                .executeUpdate();
    }

    public List<BudgetLine> getAllExpiredBudgetLines() {
        return sessionFactory.getCurrentSession().createQuery("FROM BudgetLine WHERE endDate<CURRENT_DATE AND status IN (:statuses) ORDER BY id DESC")
                .setParameter("statuses", Arrays.asList("Pending", "Approved", "RequestEdit"))
                .getResultList();
    }

    public List<BudgetLine> getAllBudgetlinesByStatusRejected() {
        return sessionFactory.getCurrentSession().createQuery("FROM BudgetLine WHERE status='Rejected' ORDER BY id DESC").getResultList();
    }
}
