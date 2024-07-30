package com.pahappa.systems.pettycashsystem.managedcontroller.requisitions;

import com.pahappa.systems.pettycashsystem.managedcontroller.timeutil.TimeUtil;
import com.pahappa.systems.pettycashsystem.managedcontroller.budgetlines.AllBudgetLines;
import com.pahappa.systems.pettycashsystem.spring.models.BudgetLine;
import com.pahappa.systems.pettycashsystem.spring.models.Requisition;
import com.pahappa.systems.pettycashsystem.spring.models.User;
import com.pahappa.systems.pettycashsystem.spring.services.BudgetLineService;
import com.pahappa.systems.pettycashsystem.spring.services.RequisitionService;
import com.pahappa.systems.pettycashsystem.spring.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;

@Named
@ViewScoped
@Component
public class DisburseCash implements Serializable {
    @Autowired
    private UserService userService;

    @Autowired
    private BudgetLineService budgetLineService;

    @Autowired
    private RequisitionService requisitionService;

    @Autowired
    private AllRequisitions allRequisitions;

    @Autowired
    private AllBudgetLines allBudgetLines;

    private Requisition requisition;

    private Double amountGranted;

    @PostConstruct
    public void init() {
        requisition = new Requisition();
    }

    public Requisition getRequisition() {
        return requisition;
    }

    public void selectRequisition(Requisition selectedRequisition){
        this.requisition = selectedRequisition;
    }

    public void disburseCash() {
        FacesContext context=FacesContext.getCurrentInstance();

        BudgetLine budgetLine = new BudgetLine();
        User user = new User();

        //get budgetLine and user
        budgetLine = requisition.getBudgetline();
        user = requisition.getUser();

        // Performing computations
        Double amountGranted=requisition.getAmountGranted();

        Double userAccBal=requisition.getUser().getAccountBalance();

        Double budgetLineAccBal=requisition.getBudgetline().getBalance();

        Double newUserAccBal=userAccBal+amountGranted;

        Double newBudgetLineAccBal=budgetLineAccBal-amountGranted;

        try {
            // updating acc_balances
            user.setAccountBalance(newUserAccBal);
            budgetLine.setBalance(newBudgetLineAccBal);

            requisitionService.validateBeforeDisbursement(budgetLineAccBal,budgetLine.getDescription());

            // setting requisition status to paid
            requisition.setStatus("Paid");

            // setting the dateOfDisbursement of the cash
            requisition.setDateOfCashDisbursement(TimeUtil.getCurrentDate());

            //updating the db
            requisitionService.updateRequisition(requisition);
            userService.updateUser(user);
            budgetLineService.updateBudgetLine(budgetLine);

            //updating the requisitions table
            allRequisitions.update();

            //updating budgetLines table
            allBudgetLines.update();

            FacesMessage message = new FacesMessage("Transaction was successful.", "Success");
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,e.getMessage(),e.getMessage()));
            context.validationFailed();
        }

        //clearing the requisition
        requisition=new Requisition();
    }

}