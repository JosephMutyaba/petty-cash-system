package com.pahappa.systems.pettycashsystem.managedcontroller.admin;

import com.pahappa.systems.pettycashsystem.spring.models.BudgetLine;
import com.pahappa.systems.pettycashsystem.spring.models.BudgetLineCategory;
import com.pahappa.systems.pettycashsystem.spring.services.BudgetLineCategoryService;
import com.pahappa.systems.pettycashsystem.spring.services.BudgetLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Named
@RequestScoped
@Component
public class CreateBudgetLine implements Serializable {
    @Autowired
    private BudgetLineService budgetLineService;

    @Autowired
    BudgetLineCategoryService budgetLineCategoryService;

    @Autowired
    AllBudgetLines allBudgetLines;

    private String description;
    private Date startDate;
    private Date endDate;
    private Double amount;
    private BudgetLineCategory budgetLineCategory;
    private BudgetLine budgetLine;

    private String budgetLineCategoryName;

    private Date today = new Date();

    private List<BudgetLineCategory> budgetLineCategories;

    @PostConstruct
    public void init() {
        budgetLineCategory = new BudgetLineCategory();
        budgetLine = new BudgetLine();
        budgetLineCategories = budgetLineCategoryService.getAllBudgetLineCategories();
    }

    public Date getToday() {
        return today;
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


    public List<BudgetLineCategory> getBudgetLineCategories() {
        return budgetLineCategoryService.getAllBudgetLineCategories();
    }

    public void setBudgetLineCategories(List<BudgetLineCategory> budgetLineCategories) {
        this.budgetLineCategories = budgetLineCategories;
    }

    public void saveBudgetLine() {
        System.out.println("\nSaving budgetLine.........\n");
        saveOrUpdateBudgetLine("Pending");
    }

    public void saveDraftBudgetLine() {
        System.out.println("\nSaving budgetLine.........\n");
        saveOrUpdateBudgetLine("Draft");
    }

    private void saveOrUpdateBudgetLine(String givenStatus){
        budgetLine.setStatus(givenStatus);

        budgetLine.setAmount(amount);
        budgetLine.setDescription(description);
        budgetLine.setStartDate(startDate);
        budgetLine.setEndDate(endDate);
        budgetLine.setBalance(amount);
        budgetLineCategory=budgetLineCategoryService.getBudgetLineCategoryByName(budgetLineCategoryName);
        budgetLine.setBudgetLineCategory(budgetLineCategory);

        FacesContext context=FacesContext.getCurrentInstance();
        try {
            budgetLineService.createBudgetLine(budgetLine);
            FacesMessage message = new FacesMessage("BudgetLine saved successfully", "Success");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,  e.getMessage(), null));
            context.validationFailed();
        }
        allBudgetLines.update();
        setAmount(null);
        setDescription(null);
        setEndDate(null);
        setStartDate(null);
    }

    public String getBudgetLineCategoryName() {
        return budgetLineCategoryName;
    }

    public void setBudgetLineCategoryName(String budgetLineCategoryName) {
        this.budgetLineCategoryName = budgetLineCategoryName;
    }
}
