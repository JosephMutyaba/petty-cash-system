package com.pahappa.systems.pettycashsystem.managedcontroller.admin;

import com.pahappa.systems.pettycashsystem.spring.models.BudgetLine;
import com.pahappa.systems.pettycashsystem.spring.models.BudgetLineCategory;
import com.pahappa.systems.pettycashsystem.spring.services.BudgetLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.el.MethodExpression;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;

@Named
@RequestScoped
@Component
public class CreateBudgetLine implements Serializable {
    @Autowired
    private BudgetLineService budgetLineService;


    private String description;
    private Date startDate;
    private Date endDate;
    private Double amount;
    private BudgetLineCategory budgetLineCategory;
    private BudgetLine budgetLine;

    private Date today = new Date();

    public Date getToday() {
        return today;
    }


    @PostConstruct
    public void init() {
        budgetLineCategory = new BudgetLineCategory();
        budgetLine = new BudgetLine();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public BudgetLineCategory getBudgetLineCategory() {
        return budgetLineCategory;
    }

    public void setBudgetLineCategory(BudgetLineCategory budgetLineCategory) {
        this.budgetLineCategory = budgetLineCategory;
    }

    public void saveBudgetLine() {
        budgetLine.setAmount(amount);
        budgetLine.setDescription(description);
        budgetLine.setStartDate(startDate);
        budgetLine.setEndDate(endDate);
        budgetLine.setBudgetLineCategory(budgetLineCategory);
//        budgetLine.setStatus("Pending");
        budgetLineService.createBudgetLine(budgetLine);

    }
}
