package com.pahappa.systems.pettycashsystem.managedcontroller.admin;

import com.pahappa.systems.pettycashsystem.managedcontroller.login.LoginBean;
import com.pahappa.systems.pettycashsystem.spring.models.Accountability;
import com.pahappa.systems.pettycashsystem.spring.models.BudgetLine;
import com.pahappa.systems.pettycashsystem.spring.models.Requisition;
import com.pahappa.systems.pettycashsystem.spring.models.User;
import com.pahappa.systems.pettycashsystem.spring.services.AccountabilityService;
import com.pahappa.systems.pettycashsystem.spring.services.BudgetLineService;
import com.pahappa.systems.pettycashsystem.spring.services.RequisitionService;
import com.pahappa.systems.pettycashsystem.spring.services.UserService;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.*;
import java.util.Date;

@Named
@ViewScoped
@Component
public class CreateAccountability implements Serializable {
    @Autowired
    private UserService userService;

    @Autowired
    private BudgetLineService budgetLineService;

    @Autowired
    private MyRequisitionHandler myRequisitionHandler;

    @Autowired
    private RequisitionService requisitionService;

    @Autowired
    private LoginBean loginBean;

    private String description;

    private String extraClaims;

    private Double amountSpent;

    private Date dateSubmitted;

    private byte[] receiptImage;

    private UploadedFile file;

    private Accountability accountability;

    private Requisition requisition;

    @PostConstruct
    public void init() {
        accountability = new Accountability();
        requisition = myRequisitionHandler.getRequisition();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getExtraClaims() {
        return extraClaims;
    }

    public void setExtraClaims(String extraClaims) {
        this.extraClaims = extraClaims;
    }

    public Double getAmountSpent() {
        return amountSpent;
    }

    public void setAmountSpent(Double amountSpent) {
        this.amountSpent = amountSpent;
    }

    public Date getDateSubmitted() {
        return dateSubmitted;
    }

    public void setDateSubmitted(Date dateSubmitted) {
        this.dateSubmitted = dateSubmitted;
    }

    public byte[] getReceiptImage() {
        return receiptImage;
    }

    public void setReceiptImage(byte[] receiptImage) {
        this.receiptImage = receiptImage;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public void handleFileUpload(FileUploadEvent event) {
        FacesContext context = FacesContext.getCurrentInstance();
        this.file = event.getFile();
        try (InputStream input = file.getInputStream()) {
//            this.receiptImage = input.readAllBytes();
            this.receiptImage = toByteArray(input);  // Use the alternative method
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Could not process file: " + e.getMessage(),null));
            context.validationFailed();
        }
    }




    private byte[] toByteArray(InputStream input) throws IOException {
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int nRead;
        byte[] data = new byte[16384];
        while ((nRead = input.read(data, 0, data.length)) != -1) {
            buffer.write(data, 0, nRead);
        }
        buffer.flush();
        return buffer.toByteArray();
    }

    public void save() {
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            requisition=myRequisitionHandler.getRequisition();
            accountability.setDescription(description);
            accountability.setExtraClaims(extraClaims);
            accountability.setDateSubmitted(new Date());
            accountability.setAmountSpent(amountSpent);
            accountability.setRequisition(requisition);
            accountability.setReceiptImage(receiptImage);

            //setting status to completed
            requisition.setStatus("Completed");
            requisition.setAccountability(accountability);  // this will lead to creation of the accountability in the db

            //balancing books
            User user=loginBean.getLoggedInUser();

            BudgetLine budgetLine=requisition.getBudgetline();

            Double budgetlineBal=budgetLine.getBalance();

            Double requisitionAmountGranted = requisition.getAmountGranted();

            Double userAccBal = user.getAccountBalance();

            Double expenditureBalance = requisitionAmountGranted-amountSpent;

            Double newUserAccBal=userAccBal-expenditureBalance;

            Double newBudgetLineBal=budgetlineBal+expenditureBalance;

            user.setAccountBalance(newUserAccBal);
            budgetLine.setBalance(newBudgetLineBal);

            //accountabilityService.createAccountability(accountability);
            userService.updateUser(user);

            budgetLineService.updateBudgetLine(budgetLine);

            requisitionService.updateRequisition(requisition);  // this automatically creates

            FacesMessage message = new FacesMessage("Success", "accountability saved successfully. Your account has been debited with shs."+expenditureBalance);
            FacesContext.getCurrentInstance().addMessage(null, message);
        } catch (Exception e) {
            context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,e.getMessage(),null));
            context.validationFailed();
        }
    }

    public InputStream getImageStream() {
        if (receiptImage != null) {
            return new ByteArrayInputStream(receiptImage);
        }
        return null;
    }
}