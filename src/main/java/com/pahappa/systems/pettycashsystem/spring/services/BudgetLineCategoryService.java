package com.pahappa.systems.pettycashsystem.spring.services;

import com.pahappa.systems.pettycashsystem.spring.dao.BudgetLineCategoryDAO;
import com.pahappa.systems.pettycashsystem.spring.models.BudgetLineCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BudgetLineCategoryService {

    private final BudgetLineCategoryDAO budgetLineCategoryDAO;

    @Autowired
    public BudgetLineCategoryService(BudgetLineCategoryDAO budgetLineCategoryDAO) {
        this.budgetLineCategoryDAO = budgetLineCategoryDAO;
    }

    public void createBudgetLineCategory(BudgetLineCategory category) {
        budgetLineCategoryDAO.createBudgetLineCategory(category);
    }

    public BudgetLineCategory getBudgetLineCategoryById(Long id) {
        return budgetLineCategoryDAO.getBudgetLineCategoryById(id);
    }

    public List<BudgetLineCategory> getAllBudgetLineCategories() {
        return budgetLineCategoryDAO.getAllBudgetLineCategories();
    }

    public void updateBudgetLineCategory(BudgetLineCategory category) {
        budgetLineCategoryDAO.updateBudgetLineCategory(category);
    }

    public void deleteBudgetLineCategory(Long id) {
        budgetLineCategoryDAO.deleteBudgetLineCategory(id);
    }
}
