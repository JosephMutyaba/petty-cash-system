package com.pahappa.systems.pettycashsystem.spring.dao;

import com.pahappa.systems.pettycashsystem.spring.models.Accountability;
import com.pahappa.systems.pettycashsystem.spring.models.BudgetLine;
import com.pahappa.systems.pettycashsystem.spring.models.BudgetLineCategory;
import com.pahappa.systems.pettycashsystem.spring.models.Requisition;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BudgetLineCategoryDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public BudgetLineCategoryDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void createBudgetLineCategory(BudgetLineCategory category) {
        sessionFactory.getCurrentSession().save(category);
    }

    public BudgetLineCategory getBudgetLineCategoryById(Long id) {
        return (BudgetLineCategory) sessionFactory.getCurrentSession().createQuery("FROM BudgetLineCategory WHERE deleted=false AND id=:id")
                .setParameter("id", id)
                .uniqueResult();
    }

    public List<BudgetLineCategory> getAllBudgetLineCategories() {
        return sessionFactory.getCurrentSession()
                .createQuery("FROM BudgetLineCategory WHERE deleted=false ORDER BY id DESC", BudgetLineCategory.class)
                .getResultList();
    }

    public void updateBudgetLineCategory(BudgetLineCategory category) {
        sessionFactory.getCurrentSession().update(category);
    }


    public void deleteBudgetLineCategory(Long id) {
        BudgetLineCategory category = sessionFactory.getCurrentSession().get(BudgetLineCategory.class, id);
        if (category != null) {
            List<BudgetLine> budgetLines = sessionFactory.getCurrentSession().createQuery("FROM BudgetLine b WHERE b.deleted=false AND b.budgetLineCategory.id= :id")
                    .setParameter("id", category.getId()).getResultList();

            if (budgetLines != null){
                for (BudgetLine budgetLine:budgetLines){
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
                                .setParameter("id", budgetLine.getId())
                                .executeUpdate();
                    }
                }

                sessionFactory.getCurrentSession().createQuery("UPDATE BudgetLine b SET b.deleted = true WHERE b.budgetLineCategory.id=:id")
                        .setParameter("id", category.getId())
                        .executeUpdate();
            }

            category.setDeleted(true);
            sessionFactory.getCurrentSession().update(category);
        }

    }




//    public void deleteBudgetLineCategory(Long id) {
//        BudgetLineCategory category = sessionFactory.getCurrentSession().load(BudgetLineCategory.class, id);
//        if (category != null) {
//            sessionFactory.getCurrentSession().delete(category);
//        }
//    }

    public BudgetLineCategory getBudgetLineCategoryByName(String budgetLineCategoryName) {
        return (BudgetLineCategory) sessionFactory.getCurrentSession().createQuery("FROM BudgetLineCategory WHERE name = :budgetLineCategoryName")
                .setParameter("budgetLineCategoryName", budgetLineCategoryName)
                .uniqueResult();
    }



    public void deleteAllCategories() {
        sessionFactory.getCurrentSession().createQuery("UPDATE Accountability SET deleted=true").executeUpdate();
        sessionFactory.getCurrentSession().createQuery("UPDATE Requisition SET deleted=true").executeUpdate();
        sessionFactory.getCurrentSession().createQuery("UPDATE BudgetLine SET deleted=true").executeUpdate();
        sessionFactory.getCurrentSession().createQuery("UPDATE BudgetLineCategory SET deleted=true").executeUpdate();

    }
}
