package com.pahappa.systems.pettycashsystem.spring.dao;

import com.pahappa.systems.pettycashsystem.spring.models.BudgetLineCategory;
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
        return sessionFactory.getCurrentSession().get(BudgetLineCategory.class, id);
    }

    public List<BudgetLineCategory> getAllBudgetLineCategories() {
        return sessionFactory.getCurrentSession()
                .createQuery("FROM BudgetLineCategory", BudgetLineCategory.class)
                .getResultList();
    }

    public void updateBudgetLineCategory(BudgetLineCategory category) {
        sessionFactory.getCurrentSession().update(category);
    }

    public void deleteBudgetLineCategory(Long id) {
        BudgetLineCategory category = sessionFactory.getCurrentSession().load(BudgetLineCategory.class, id);
        if (category != null) {
            sessionFactory.getCurrentSession().delete(category);
        }
    }
}
