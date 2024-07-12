package com.pahappa.systems.pettycashsystem.managedcontroller.admin;

import com.pahappa.systems.pettycashsystem.managedcontroller.login.LoginBean;
import com.pahappa.systems.pettycashsystem.spring.models.BudgetLine;
import com.pahappa.systems.pettycashsystem.spring.models.Requisition;
import com.pahappa.systems.pettycashsystem.spring.models.User;
import com.pahappa.systems.pettycashsystem.spring.services.BudgetLineService;
import com.pahappa.systems.pettycashsystem.spring.services.RequisitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Named
@ViewScoped
@Component
public class UpdateRequisition implements Serializable {
    @Autowired
    RequisitionService requisitionService;

    @Autowired
    private LoginBean loginBean;

    @Autowired
    private BudgetLineService budgetLineService;

    @Autowired
    private AllRequisitions allRequisitions;

    private String justification;

    private User user;

    private List<BudgetLine> budgetLines;

    private BudgetLine budgetLine;

    private Double requestedAmount;

    private String status;

    private Date maxDate;

    private Long budgetLineId;

    private Requisition requisition;

    @PostConstruct
    public void init() {
        requisition = new Requisition();
        budgetLines = budgetLineService.getAllBudgetLines();
        user = new User();
    }

    public String getJustification() {
        return justification;
    }

    public void setJustification(String justification) {
        this.justification = justification;
    }

    public List<BudgetLine> getBudgetLines() {
        return budgetLineService.getAllBudgetLines();
    }

    public void setBudgetLines(List<BudgetLine> budgetLines) {
        this.budgetLines = budgetLines;
    }

    public BudgetLine getBudgetLine() {
        return budgetLine;
    }

    public void setBudgetLine(BudgetLine budgetLine) {
        this.budgetLine = budgetLine;
    }

    public Double getRequestedAmount() {
        return requestedAmount;
    }

    public void setRequestedAmount(Double requestedAmount) {
        this.requestedAmount = requestedAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getMaxDate() {
        return maxDate;
    }

    public void setMaxDate(Date maxDate) {
        this.maxDate = maxDate;
    }

    public Requisition getRequisition() {
        return requisition;
    }

    public void setRequisition(Requisition requisition) {
        this.requisition = requisition;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = loginBean.getLoggedInUser();
    }

    public Long getBudgetLineId() {
        return budgetLineId;
    }

    public void setBudgetLineId(Long budgetLineId) {
        this.budgetLineId = budgetLineId;
    }

    public void selectRequisition(Requisition selectedRequisition) {
        this.requisition = selectedRequisition;
        this.budgetLineId = requisition.getId();
        this.justification=requisition.getJustification();
        this.status=requisition.getStatus();
        this.maxDate=requisition.getMaxDateNeeded();
        this.budgetLine = requisition.getBudgetline();
        this.requestedAmount=requisition.getEstimatedAmount();

    }


    public void createRequisition() {
        requisition.setJustification(justification);
        requisition.setUser(loginBean.getLoggedInUser());

        budgetLine= budgetLineService.getBudgetLineById(budgetLineId);

        requisition.setBudgetline(budgetLine);

        requisition.setEstimatedAmount(requestedAmount);
        requisition.setAmountGranted(requestedAmount);
        requisition.setStatus("Pending");
        requisition.setMaxDateNeeded(maxDate);
//        requisition.setDateCreated(new Date());

        requisitionService.createRequisition(requisition);

        allRequisitions.init();
        //////////
        this.justification = null;
        this.status = null;
        this.requisition = new Requisition();
        this.maxDate=null;
        this.budgetLine=new BudgetLine();
        this.requestedAmount=null;

    }

}
