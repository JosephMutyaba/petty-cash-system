package com.pahappa.systems.pettycashsystem.spring.dao;

import com.pahappa.systems.pettycashsystem.spring.models.BudgetLine;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class BudgetLineDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public List<BudgetLine> getAllBudgetLines() {
        return entityManager.createQuery("from BudgetLine", BudgetLine.class).getResultList();
    }

    public BudgetLine getBudgetLineById(Long id) {
        return entityManager.find(BudgetLine.class, id);
    }

    public void createBudgetLine(BudgetLine budgetLine) {
        entityManager.persist(budgetLine);
    }

    public void updateBudgetLine(BudgetLine budgetLine) {
        entityManager.merge(budgetLine);
    }

    public void deleteBudgetLine(Long id) {
        BudgetLine budgetLine = entityManager.find(BudgetLine.class, id);
        if (budgetLine != null) {
            entityManager.remove(budgetLine);
        }
    }
}
