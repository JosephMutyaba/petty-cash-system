package com.pahappa.systems.pettycashsystem.spring.services;

import com.pahappa.systems.pettycashsystem.spring.dao.BudgetLineCategoryDAO;
import com.pahappa.systems.pettycashsystem.spring.models.BudgetLineCategory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BudgetLineCategoryService {

    private final BudgetLineCategoryDAO budgetLineCategoryDAO;

    @Autowired
    public BudgetLineCategoryService(BudgetLineCategoryDAO budgetLineCategoryDAO) {
        this.budgetLineCategoryDAO = budgetLineCategoryDAO;
    }

    public List<BudgetLineCategory> getAllBudgetLineCategories() {
        return budgetLineCategoryDAO.getAllBudgetLineCategories();
    }

    public BudgetLineCategory getBudgetLineCategoryById(Long id) {
        return budgetLineCategoryDAO.getBudgetLineCategoryById(id);
    }

    public void createBudgetLineCategory(BudgetLineCategory budgetLineCategory) {
        budgetLineCategoryDAO.createBudgetLineCategory(budgetLineCategory);
    }

    public void updateBudgetLineCategory(Long id, BudgetLineCategory budgetLineCategoryDetails) {
        BudgetLineCategory budgetLineCategory = budgetLineCategoryDAO.getBudgetLineCategoryById(id);

        if (budgetLineCategory != null) {
            budgetLineCategory.setName(budgetLineCategoryDetails.getName());
            budgetLineCategory.setBudgetLines(budgetLineCategoryDetails.getBudgetLines());
            budgetLineCategoryDAO.updateBudgetLineCategory(budgetLineCategory);
        } else {
            throw new RuntimeException("BudgetLineCategory not found with id " + id);
        }
    }

    public void deleteBudgetLineCategory(Long id) {
        budgetLineCategoryDAO.deleteBudgetLineCategory(id);
    }
}
