package com.pahappa.systems.pettycashsystem.spring.services;

import com.pahappa.systems.pettycashsystem.spring.dao.BudgetLineDAO;
import com.pahappa.systems.pettycashsystem.spring.models.BudgetLine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BudgetLineService {

    private final BudgetLineDAO budgetLineDAO;

    @Autowired
    public BudgetLineService(BudgetLineDAO budgetLineDAO) {
        this.budgetLineDAO = budgetLineDAO;
    }

    public List<BudgetLine> getAllBudgetLines() {
        return budgetLineDAO.getAllBudgetLines();
    }

    public BudgetLine getBudgetLineById(Long id) {
        return budgetLineDAO.getBudgetLineById(id);
    }

    public void createBudgetLine(BudgetLine budgetLine) {
        budgetLineDAO.createBudgetLine(budgetLine);
    }

    public void updateBudgetLine(Long id, BudgetLine budgetLineDetails) {
        BudgetLine budgetLine = budgetLineDAO.getBudgetLineById(id);

        if (budgetLine != null) {
            budgetLine.setDescription(budgetLineDetails.getDescription());
            budgetLine.setStartDate(budgetLineDetails.getStartDate());
            budgetLine.setEndDate(budgetLineDetails.getEndDate());
            budgetLine.setAmount(budgetLineDetails.getAmount());
            budgetLine.setBalance(budgetLineDetails.getBalance());
            budgetLine.setDateApproved(budgetLineDetails.getDateApproved());
            budgetLine.setStatus(budgetLineDetails.getStatus());
            budgetLine.setRequisitions(budgetLineDetails.getRequisitions());
            budgetLine.setBudgetLineCategory(budgetLineDetails.getBudgetLineCategory());
            budgetLineDAO.updateBudgetLine(budgetLine);
        } else {
            throw new RuntimeException("BudgetLine not found with id " + id);
        }
    }

    public void deleteBudgetLine(Long id) {
        budgetLineDAO.deleteBudgetLine(id);
    }
}
