package com.pahappa.systems.pettycashsystem.managedcontroller.admin;

import com.pahappa.systems.pettycashsystem.spring.models.Accountability;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Base64;

@Named
@ViewScoped
@Component
public class HandleOneAccountability implements Serializable {
    private Accountability accountability;

    private String receiptImageBase64;

    @PostConstruct
    public void init() {
        accountability = new Accountability();
    }

    public void setReceiptImageBase64(String receiptImageBase64) {
        this.receiptImageBase64 = receiptImageBase64;
    }

    public Accountability getAccountability() {
        return accountability;
    }

    public void setAccountability(Accountability accountability) {
        this.accountability = accountability;
    }

    public void selectAccountability(Accountability accountability){
       this.accountability = accountability;
    }


    public String getReceiptImageBase64() {
        if (this.accountability != null && this.accountability.getReceiptImage() != null) {
            return Base64.getEncoder().encodeToString(accountability.getReceiptImage());
        }
        return null;
    }

}
