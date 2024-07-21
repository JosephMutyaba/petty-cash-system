package com.pahappa.systems.pettycashsystem.spring.services;

import com.pahappa.systems.pettycashsystem.exceptions.IncompatibleDatesException;
import com.pahappa.systems.pettycashsystem.exceptions.NullFieldException;
import com.pahappa.systems.pettycashsystem.spring.dao.BudgetLineDAO;
import com.pahappa.systems.pettycashsystem.spring.models.BudgetLine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class BudgetLineService {

    private final BudgetLineDAO budgetLineDAO;

    @Autowired
    public BudgetLineService(BudgetLineDAO budgetLineDAO) {
        this.budgetLineDAO = budgetLineDAO;
    }

    public void createBudgetLine(BudgetLine budgetLine) throws IncompatibleDatesException, NullFieldException {
        validateBudgetLine(budgetLine);
        budgetLineDAO.createBudgetLine(budgetLine);
    }

    public BudgetLine getBudgetLineById(Long id) {
        return budgetLineDAO.getBudgetLineById(id);
    }

    public List<BudgetLine> getAllBudgetLines() {
        return budgetLineDAO.getAllBudgetLines();
    }

    public void updateBudgetLine(BudgetLine budgetLine) throws IncompatibleDatesException, NullFieldException {
        validateBudgetLine(budgetLine);
        budgetLineDAO.updateBudgetLine(budgetLine);
    }

    public void deleteBudgetLine(Long id) {
        budgetLineDAO.deleteBudgetLine(id);
    }

    public List<BudgetLine> getAllBudgetlinesByStatus(String budgetLineStatus) {
        return budgetLineDAO.getAllBudgetlinesByStatus(budgetLineStatus);
    }

    public void deleteBudgetLinesByStatus(String status) {
        budgetLineDAO.deleteBudgetLinesByStatus(status);
    }

    public void validateBudgetLine(BudgetLine budgetLine) throws NullFieldException, IncompatibleDatesException {
        if (budgetLine.getDescription()==null || budgetLine.getDescription().trim().isEmpty()) {
            throw new NullFieldException("Description is empty");
        }

        if (budgetLine.getEndDate()==null) {
            throw new NullFieldException("End date cannot be empty");
        }

        if (budgetLine.getStartDate()==null) {
            throw new NullFieldException("StartDate cannot be empty");
        }
        
        if (budgetLine.getEndDate().before(budgetLine.getStartDate())) {
            throw new IncompatibleDatesException("End date cannot be before start date");
        }

        if (budgetLine.getEndDate().equals(new Date())) {
            throw new IncompatibleDatesException("End date cannot be today otherwise new budgetLine would expire almost immediately.");
        }

        if (budgetLine.getAmount().isNaN()) {
            throw new NullFieldException("Amount should be a number");
        }

        if (budgetLine.getAmount()==null) {
            throw new NullFieldException("Amount cannot be empty");
        }

        if (budgetLine.getAmount()<200000.0) {
            throw new NullFieldException("Amount cannot be less than 200,000");
        }

        if (budgetLine.getBudgetLineCategory()==null){
            throw new NullFieldException("Budget Line Category cannot be null");
        }
    }

    public List<BudgetLine> getAllExpiredBudgetLines() {
        return budgetLineDAO.getAllExpiredBudgetLines();
    }

    public List<BudgetLine> getAllBudgetlinesByStatusRejected() {
        return budgetLineDAO.getAllBudgetlinesByStatusRejected();
    }
}
