package com.pahappa.systems.pettycashsystem.managedcontroller.admin;

import org.springframework.stereotype.Component;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Date;

@Named("con")
@ApplicationScoped
@Component
public class Constants implements Serializable {
    public String getNameRegex() {
        return "[a-zA-Z]{2,30}";
    }
    public String getEmailRegex() {
        return "^[a-zA-Z0-9]{1,}@[a-zA-Z0-9_]{2,}.[a-zA-Z]{2,}$";
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
}
