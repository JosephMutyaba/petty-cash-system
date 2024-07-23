package com.pahappa.systems.pettycashsystem.managedcontroller.admin;

import org.springframework.stereotype.Component;

import javax.enterprise.context.ApplicationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;

@Named("con")
@ApplicationScoped
@Component
public class Constants implements Serializable {
    private String route = "";

    public String getNameRegex() {
        return "[a-zA-Z]{2,30}";
    }
    public String getEmailRegex() {
        return "^[a-zA-Z0-9_]{1,}@[a-zA-Z0-9_]{2,}.[a-zA-Z]{2,}$";
    }
    public String getUsernameRegex() {
        return "^[a-zA-Z0-9_]{4,20}$";
    }
    public String getPasswordRegex() {
        return "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&#^{}\\[\\]:;'\"/\\\\,.+=\\-_><])[A-Za-z\\d@$!%*?&#^{}\\[\\]:;'\"/\\\\,.+=\\-_>< ]{8,30}$";
    }
    public String formatDate(Date date) {
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd-MM-yyyy");
        return sdf.format(date);
    }
    public String getRequiredMessage() {
        return "This field is required!";
    }

    public void showErrorMessage(String msg) {
        showErrorMessageS(msg);
    }
    /** Static version of showErrorMessage()*/
    public static void showErrorMessageS(String msg) {
        FacesMessage message = new FacesMessage(msg);
        message.setSeverity(FacesMessage.SEVERITY_ERROR);
        FacesContext.getCurrentInstance().addMessage(null,message);
    }

    public void setRoute(String s) {route = s;}
    public String routeIs(String s) {
        return route.equalsIgnoreCase(s) ? " route " : "";
    }
}
