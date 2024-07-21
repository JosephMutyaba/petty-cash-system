package com.pahappa.systems.pettycashsystem.managedcontroller.admin;

import com.pahappa.systems.pettycashsystem.spring.models.Accountability;
import com.pahappa.systems.pettycashsystem.spring.services.AccountabilityService;
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

    private List<Accountability> accountabilities;

    private String searchTerm;

    @PostConstruct
    public void init() {
        accountabilities = accountabilityService.getAllAccountabilities();
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    public List<Accountability> getAccountabilities() {
        accountabilities = accountabilityService.getAllAccountabilities();
        filterAccountabilities();
        return accountabilities;
    }

    public void setAccountabilities(List<Accountability> accountabilities) {
        this.accountabilities = accountabilityService.getAllAccountabilities();
    }

    public String getReceiptImageBase64(Accountability accountability) {
        if (accountability != null && accountability.getReceiptImage() != null) {
            return Base64.getEncoder().encodeToString(accountability.getReceiptImage());
        }
        return null;
    }

    public void filterAccountabilities() {
        if (searchTerm == null || searchTerm.isEmpty()) {
            accountabilities=accountabilityService.getAllAccountabilities();
        } else {
            accountabilities = accountabilities.stream()
                    .filter(accountability ->
                            accountability.getRequisition().getJustification().toLowerCase().contains(searchTerm.toLowerCase()) ||
                                    String.valueOf(accountability.getDescription()).toLowerCase().contains(searchTerm.toLowerCase()) ||
                                    String.valueOf(accountability.getAmountSpent()).toLowerCase().contains(searchTerm.toLowerCase()) ||
                                    String.valueOf(accountability.getRequisition().getUser().getFirstname()).toLowerCase().contains(searchTerm.toLowerCase())||
                                    String.valueOf(accountability.getRequisition().getAmountGranted()).toLowerCase().contains(searchTerm.toLowerCase())
                    ).collect(Collectors.toList());
        }
    }
}
