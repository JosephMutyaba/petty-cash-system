package com.pahappa.systems.pettycashsystem.managedcontroller.accountabilities;

import com.pahappa.systems.pettycashsystem.spring.models.Accountability;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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

    public void downloadReceiptImage() throws IOException {
        if (this.accountability != null && this.accountability.getReceiptImage() != null) {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            ExternalContext externalContext = facesContext.getExternalContext();
            externalContext.responseReset();
            externalContext.setResponseContentType("image/png");
            externalContext.setResponseHeader("Content-Disposition", "attachment;filename=receipt.png");

            try (OutputStream output = externalContext.getResponseOutputStream();
                 InputStream input = new ByteArrayInputStream(this.accountability.getReceiptImage())) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = input.read(buffer)) != -1) {
                    output.write(buffer, 0, bytesRead);
                }
            }

            facesContext.responseComplete();
        }
    }
}