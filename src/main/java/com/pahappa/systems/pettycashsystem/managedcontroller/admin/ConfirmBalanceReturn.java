package com.pahappa.systems.pettycashsystem.managedcontroller.admin;

import com.pahappa.systems.pettycashsystem.exceptions.IncompatibleDatesException;
import com.pahappa.systems.pettycashsystem.exceptions.NullFieldException;
import com.pahappa.systems.pettycashsystem.spring.models.Accountability;
import com.pahappa.systems.pettycashsystem.spring.models.BudgetLine;
import com.pahappa.systems.pettycashsystem.spring.models.Requisition;
import com.pahappa.systems.pettycashsystem.spring.models.User;
import com.pahappa.systems.pettycashsystem.spring.services.AccountabilityService;
import com.pahappa.systems.pettycashsystem.spring.services.BudgetLineService;
import com.pahappa.systems.pettycashsystem.spring.services.RequisitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
@Component
public class ConfirmBalanceReturn implements Serializable {
    @Autowired
    private AccountabilityService accountabilityService;

    @Autowired
    private RequisitionService requisitionService;

    @Autowired
    private BudgetLineService budgetLineService;

    @Autowired
    private AllBudgetLines allBudgetLines;

    private Boolean balanceReturned;

    private Accountability accountability;

    private User user;

    private Requisition requisition;

    @PostConstruct
    public void init() {
        accountability = new Accountability();
        user = new User();
        requisition = new Requisition();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Accountability getAccountability() {
        return accountability;
    }

    public void setAccountability(Accountability accountability) {
        this.accountability = accountability;
    }

    public Requisition getRequisition() {
        return requisition;
    }

    public void setRequisition(Requisition requisition) {
        this.requisition = requisition;
    }

    public Boolean getBalanceReturned() {
        return balanceReturned;
    }

    public void setBalanceReturned(Boolean balanceReturned) {
        this.balanceReturned = balanceReturned;
    }

    public void selectAccountability(Accountability selectedAccountability){
        this.accountability = selectedAccountability;
        this.balanceReturned = accountability.getBalanceIsReturned();
    }

    public void selectUser(User selectedUser){
        this.user = selectedUser;
//        this.balanceReturned = accountability.getBalanceIsReturned();
    }

    public void selectRequisition(Requisition selectedRequisition){
        this.requisition = selectedRequisition;
//        this.balanceReturned = accountability.getBalanceIsReturned();
    }


    public void updateAccountabilityBalanceReturnStatus() {
        FacesContext context = FacesContext.getCurrentInstance();

        if (balanceReturned) {
            Requisition req= requisitionService.getRequisitionByAccountabilityId(accountability.getId());
            BudgetLine budgetLine = req.getBudgetline();

            // START OF ACCOUNTING
            // add the balance returned to the budgetLine balance
            Double currentBudgetLineBalance = budgetLine.getBalance();
            Double accountabilityBalance = req.getAmountGranted() - accountability.getAmountSpent();

            Double newBudgetLineBalance = currentBudgetLineBalance + accountabilityBalance;

            budgetLine.setBalance(newBudgetLineBalance);
            // END OF ACCOUNTING

            try {
                //updating thr budgetLine balance in the db
                budgetLineService.updateBudgetLine(budgetLine);

                // updating the budgetLine table in the budgetLine page
                allBudgetLines.update();

                accountability.setBalanceIsReturned(balanceReturned);

                // updating the accountability status in the db
                accountabilityService.updateAccountability(accountability);

                // clear bean values
                this.balanceReturned=false;
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"cleared successfully", null));
            } catch (Exception e) {
                context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Something went wrong, please try again",null));
                context.validationFailed();
            }

        }else {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Not cleared", "Not cleared"));
        }
    }
}
