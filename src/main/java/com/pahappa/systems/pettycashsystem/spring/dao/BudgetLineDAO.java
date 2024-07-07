package com.pahappa.systems.pettycashsystem.spring.dao;

import com.pahappa.systems.pettycashsystem.spring.models.BudgetLine;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
                .createQuery("FROM BudgetLine", BudgetLine.class)
                .getResultList();
    }

    public void updateBudgetLine(BudgetLine budgetLine) {
        sessionFactory.getCurrentSession().update(budgetLine);
    }

    public void deleteBudgetLine(Long id) {
        BudgetLine budgetLine = sessionFactory.getCurrentSession().load(BudgetLine.class, id);
        if (budgetLine != null) {
            sessionFactory.getCurrentSession().delete(budgetLine);
        }
    }
}
