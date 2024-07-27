package com.pahappa.systems.pettycashsystem.managedcontroller.admin;

import com.pahappa.systems.pettycashsystem.exceptions.IncompatibleDatesException;
import com.pahappa.systems.pettycashsystem.exceptions.NullFieldException;
import com.pahappa.systems.pettycashsystem.managedcontroller.CheckPermission;
import com.pahappa.systems.pettycashsystem.spring.enums.ModifyStatus;
import com.pahappa.systems.pettycashsystem.spring.enums.Perm;
import com.pahappa.systems.pettycashsystem.spring.models.BudgetLine;
import com.pahappa.systems.pettycashsystem.spring.models.BudgetLineCategory;
import com.pahappa.systems.pettycashsystem.spring.services.BudgetLineCategoryService;
import com.pahappa.systems.pettycashsystem.spring.services.BudgetLineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;

@Named
@ViewScoped
@Component
public class UpdateBudgetLine implements Serializable {
    @Autowired
    private BudgetLineService budgetLineService;

    @Autowired
    BudgetLineCategoryService budgetLineCategoryService;

    @Autowired
    AllBudgetLines allBudgetLines;

    @Autowired
    private CheckPermission checkPermission;

    private String description;
    private Date startDate;
    private Date endDate;
    private Double amount;
    private BudgetLineCategory budgetLineCategory;
    private BudgetLine budgetLine;

    private String status;

    private Set<ModifyStatus> statusSet;

    private String review_comments;

    private String budgetLineCategoryName;

    private Date today = new Date();

    private List<BudgetLineCategory> budgetLineCategories;

    @PostConstruct
    public void init() {
        budgetLineCategory = new BudgetLineCategory();
        budgetLine = new BudgetLine();
        budgetLineCategories = budgetLineCategoryService.getAllBudgetLineCategories();
    }

    private void setupStatusSet() {
//        CheckPermission checkPermission = new CheckPermission();
        statusSet = EnumSet.of(ModifyStatus.Null);
        if (checkPermission.hasPermission(Perm.valueOf("APPROVE_BUDGETLINE"))) statusSet.add(ModifyStatus.APPROVE);
        if (checkPermission.hasPermission(Perm.valueOf("REJECT_BUDGETLINE"))) statusSet.add(ModifyStatus.REJECT);
        if (checkPermission.hasPermission(Perm.valueOf("REQUEST_CHANGES"))) statusSet.add(ModifyStatus.REQUEST_CHANGES);
    }

    public String getReview_comments() {
        return review_comments;
    }

    public void setReview_comments(String review_comments) {
        this.review_comments = review_comments;
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

    public Set<ModifyStatus> getStatusSet() {
        setupStatusSet();
        return statusSet;
    }
    
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    public void updateBudgetLine() {
        FacesContext context = FacesContext.getCurrentInstance();

        System.out.println("\nUpdating budgetLine.........\n");
        budgetLine.setReview_comments(review_comments);
        budgetLine.setAmount(amount);
        budgetLine.setDescription(description);
        budgetLine.setStartDate(startDate);
        budgetLine.setEndDate(endDate);
        budgetLine.setBalance(amount);
        budgetLineCategory=budgetLineCategoryService.getBudgetLineCategoryByName(budgetLineCategoryName);
        budgetLine.setBudgetLineCategory(budgetLineCategory);

        budgetLine.setStatus(status);

        try {
            budgetLineService.updateBudgetLine(budgetLine);
            allBudgetLines.update();


            FacesMessage message = new FacesMessage("BudgetLine updated successfully", "Success");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",e.getMessage()));
            context.validationFailed();
        }

//        return "/pages/auth/login.xhtml?faces-redirect=true";
        setAmount(null);
        setDescription(null);
        setEndDate(null);
        setStartDate(null);
        setReview_comments(null);
    }



    public void updateBudgetLineFromEditLineStatusToPending() {
        FacesContext context = FacesContext.getCurrentInstance();

        System.out.println("\nUpdating budgetLine.........\n");
        budgetLine.setReview_comments(review_comments);
        budgetLine.setAmount(amount);
        budgetLine.setDescription(description);
        budgetLine.setStartDate(startDate);
        budgetLine.setEndDate(endDate);
        budgetLine.setBalance(amount);
        budgetLineCategory=budgetLineCategoryService.getBudgetLineCategoryByName(budgetLineCategoryName);
        budgetLine.setBudgetLineCategory(budgetLineCategory);

        budgetLine.setStatus("Pending");

        try {
            budgetLineService.updateBudgetLine(budgetLine);
            allBudgetLines.update();


            FacesMessage message = new FacesMessage("BudgetLine updated successfully", "Success");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",e.getMessage()));
            context.validationFailed();
        }

//        return "/pages/auth/login.xhtml?faces-redirect=true";
        setAmount(null);
        setDescription(null);
        setEndDate(null);
        setStartDate(null);
        setReview_comments(null);
    }




    public String getBudgetLineCategoryName() {
        return budgetLineCategoryName;
    }

    public void setBudgetLineCategoryName(String budgetLineCategoryName) {
        this.budgetLineCategoryName = budgetLineCategoryName;
    }

    public void selectedBudgetLine(BudgetLine selectedBudgetLine) {
        budgetLine = selectedBudgetLine;
        this.description=selectedBudgetLine.getDescription();
        this.startDate=selectedBudgetLine.getStartDate();
        this.endDate=selectedBudgetLine.getEndDate();
        this.amount=selectedBudgetLine.getAmount();
        this.budgetLineCategory=selectedBudgetLine.getBudgetLineCategory();
        this.budgetLineCategoryName=budgetLineCategory.getName();
        this.status=selectedBudgetLine.getStatus();
        this.review_comments=selectedBudgetLine.getReview_comments();
    }
}
