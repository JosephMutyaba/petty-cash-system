package com.pahappa.systems.pettycashsystem.managedcontroller.admin;

import com.pahappa.systems.pettycashsystem.spring.models.Accountability;
import com.pahappa.systems.pettycashsystem.spring.models.Requisition;
import com.pahappa.systems.pettycashsystem.spring.services.AccountabilityService;
import com.pahappa.systems.pettycashsystem.spring.services.RequisitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Base64;
import java.util.List;
import java.util.stream.Collectors;

@Named
@ViewScoped
@Component
public class AllAccountability implements Serializable {
    @Autowired
    private AccountabilityService accountabilityService;

    @Autowired
    private RequisitionService requisitionService;

    private List<Requisition> requisitions;

    private String searchTerm;

    @PostConstruct
    public void init() {
        requisitions = requisitionService.getRequisitionsWithSubmittedAccountability();
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    public List<Requisition> getRequisitions() {
        this.requisitions = requisitionService.getRequisitionsWithSubmittedAccountability();
        filterAccountabilities();
        return requisitions;
    }

    public void setRequisitions(List<Requisition> requisitions) {
        this.requisitions = requisitionService.getRequisitionsWithSubmittedAccountability();
    }

    public String getReceiptImageBase64(Accountability accountability) {
        if (accountability != null && accountability.getReceiptImage() != null) {
            return Base64.getEncoder().encodeToString(accountability.getReceiptImage());
        }
        return null;
    }

    public void filterAccountabilities() {
        if (searchTerm == null || searchTerm.isEmpty()) {
            requisitions=requisitionService.getRequisitionsWithSubmittedAccountability();
        } else {
            requisitions = requisitions.stream()
                    .filter(requisition ->
                            requisition.getJustification().toLowerCase().contains(searchTerm.toLowerCase()) ||
                                    String.valueOf(requisition.getAccountability().getDescription()).toLowerCase().contains(searchTerm.toLowerCase()) ||
                                    String.valueOf(requisition.getAccountability().getAmountSpent()).toLowerCase().contains(searchTerm.toLowerCase()) ||
                                    String.valueOf(requisition.getUser().getFirstname()).toLowerCase().contains(searchTerm.toLowerCase())||
                                    String.valueOf(requisition.getAmountGranted()).toLowerCase().contains(searchTerm.toLowerCase())
                    ).collect(Collectors.toList());
        }
    }
}
