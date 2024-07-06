package com.pahappa.systems.pettycashsystem.spring.dao;

import com.pahappa.systems.pettycashsystem.spring.models.BudgetLineCategory;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class BudgetLineCategoryDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public List<BudgetLineCategory> getAllBudgetLineCategories() {
        return entityManager.createQuery("from BudgetLineCategory", BudgetLineCategory.class).getResultList();
    }

    public BudgetLineCategory getBudgetLineCategoryById(Long id) {
        return entityManager.find(BudgetLineCategory.class, id);
    }

    public void createBudgetLineCategory(BudgetLineCategory budgetLineCategory) {
        entityManager.persist(budgetLineCategory);
    }

    public void updateBudgetLineCategory(BudgetLineCategory budgetLineCategory) {
        entityManager.merge(budgetLineCategory);
    }

    public void deleteBudgetLineCategory(Long id) {
        BudgetLineCategory budgetLineCategory = entityManager.find(BudgetLineCategory.class, id);
        if (budgetLineCategory != null) {
            entityManager.remove(budgetLineCategory);
        }
    }
}
