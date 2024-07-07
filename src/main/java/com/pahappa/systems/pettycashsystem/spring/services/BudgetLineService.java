package com.pahappa.systems.pettycashsystem.spring.services;

import com.pahappa.systems.pettycashsystem.spring.dao.BudgetLineDAO;
import com.pahappa.systems.pettycashsystem.spring.models.BudgetLine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BudgetLineService {

    private final BudgetLineDAO budgetLineDAO;

    @Autowired
    public BudgetLineService(BudgetLineDAO budgetLineDAO) {
        this.budgetLineDAO = budgetLineDAO;
    }

    public void createBudgetLine(BudgetLine budgetLine) {
        budgetLineDAO.createBudgetLine(budgetLine);
    }

    public BudgetLine getBudgetLineById(Long id) {
        return budgetLineDAO.getBudgetLineById(id);
    }

    public List<BudgetLine> getAllBudgetLines() {
        return budgetLineDAO.getAllBudgetLines();
    }

    public void updateBudgetLine(BudgetLine budgetLine) {
        budgetLineDAO.updateBudgetLine(budgetLine);
    }

    public void deleteBudgetLine(Long id) {
        budgetLineDAO.deleteBudgetLine(id);
    }
}
