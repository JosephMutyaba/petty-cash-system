package com.pahappa.systems.pettycashsystem.managedcontroller.admin;

import com.pahappa.systems.pettycashsystem.spring.models.Accountability;
import com.pahappa.systems.pettycashsystem.spring.models.Requisition;
import com.pahappa.systems.pettycashsystem.spring.models.User;
import com.pahappa.systems.pettycashsystem.spring.services.AccountabilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
@Component
public class ConfirmBalanceReturn implements Serializable {
    @Autowired
    private AccountabilityService accountabilityService;

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
        accountability.setBalanceIsReturned(balanceReturned);
        accountabilityService.updateAccountability(accountability);
        this.balanceReturned=false;
    }
}
