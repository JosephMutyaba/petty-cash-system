package com.pahappa.systems.pettycashsystem.managedcontroller.accountabilities;

import com.pahappa.systems.pettycashsystem.spring.models.Accountability;
import com.pahappa.systems.pettycashsystem.spring.models.Requisition;
import com.pahappa.systems.pettycashsystem.spring.models.User;
import com.pahappa.systems.pettycashsystem.spring.services.EmailService;
import com.pahappa.systems.pettycashsystem.spring.services.RequisitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
@Component
public class SendEmailReminder implements Serializable {
    @Autowired
    private EmailService emailService;

    @Autowired
    private RequisitionService requisitionService;

    private String emailMessage;

    private User user;

    private Requisition requisition;

    private Accountability accountability;


    public String getEmailMessage() {
        return emailMessage;
    }

    public void setEmailMessage(String emailMessage) {
        this.emailMessage=null;
        this.emailMessage = emailMessage;
    }

    public void selectCredentials(Accountability selectedAccountability) {
        this.accountability=selectedAccountability;
        this.requisition=requisitionService.getRequisitionByAccountabilityId(accountability.getId());
        this.user=requisition.getUser();

        this.emailMessage="Dear "+user.getFirstname()+",\n\nYou are kindly reminded to return a balance of Shs."+(requisition.getAmountGranted()-accountability.getAmountSpent())
                +" off the requisition \""+requisition+"\"\n\nKind regards\nFinance";
        setEmailMessage(emailMessage);
    }

    public void sendReminderMessage(){
        // Send registration email
        String subject = "BALANCE RETURN REMINDER";
        FacesContext context=FacesContext.getCurrentInstance();

        try {
            emailService.sendSimpleMessage(user.getEmail(), subject, emailMessage);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Email sent successfully", null));
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Could not send email: Make sure you have an active and stable internet connection.",null));
            context.validationFailed();
        }
//        emailService.sendSimpleMessage(user.getEmail(), subject, emailMessage);
    }
}
