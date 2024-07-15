package com.pahappa.systems.pettycashsystem.managedcontroller.admin;

import com.pahappa.systems.pettycashsystem.exceptions.IncompatibleDatesException;
import com.pahappa.systems.pettycashsystem.exceptions.NullFieldException;
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

    public void selectRequisition(Requisition selectedRequisition){
        this.requisition = selectedRequisition;
    }

    public void disburseCash() {
        BudgetLine budgetLine = new BudgetLine();
        User user = new User();

        //get budgetLine and user
        budgetLine = requisition.getBudgetline();
        user = requisition.getUser();

        // Performing computations
        Double amountGranted=requisition.getAmountGranted();

        Double userAccBal=requisition.getUser().getAccountBalance();

        Double budgetLineAccBal=requisition.getBudgetline().getBalance();

        userAccBal=userAccBal+amountGranted;
        budgetLineAccBal=budgetLineAccBal-amountGranted;

        // updating acc_balances
        user.setAccountBalance(userAccBal);
        budgetLine.setBalance(budgetLineAccBal);


        // setting requisition status to paid
        requisition.setStatus("Paid");


        try {
            //updating the db
            requisitionService.updateRequisition(requisition);
            userService.updateUser(user);
            budgetLineService.updateBudgetLine(budgetLine);

            //updating the requisitions table
            allRequisitions.update();

            //updating budgetLines table
            allBudgetLines.update();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
        }



        //clearing the requisition
        requisition=new Requisition();
    }



}
