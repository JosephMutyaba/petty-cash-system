package com.pahappa.systems.pettycashsystem.managedcontroller.admin;

import com.pahappa.systems.pettycashsystem.spring.models.Requisition;
import com.pahappa.systems.pettycashsystem.spring.services.RequisitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
@Component
public class AllRequisitions implements Serializable {
    @Autowired
    private RequisitionService requisitionService;

    private List<Requisition> pendingRequisitions;
    private List<Requisition> approvedRequisitions;
    private List<Requisition> rejectedRequisitions;
    private List<Requisition> paidRequisitions;

    @PostConstruct
    public void init() {
        paidRequisitions=requisitionService.getAllRequisitionsByStatus("Paid");
        pendingRequisitions=requisitionService.getAllRequisitionsByStatus("Pending");
        rejectedRequisitions=requisitionService.getAllRequisitionsByStatus("Rejected");
        approvedRequisitions=requisitionService.getAllRequisitionsByStatus("Approved");
    }

    public List<Requisition> getPendingRequisitions() {
        return requisitionService.getAllRequisitionsByStatus("Pending");
    }

    public void setPendingRequisitions(List<Requisition> pendingRequisitions) {
        this.pendingRequisitions = pendingRequisitions;
    }

    public List<Requisition> getApprovedRequisitions() {
        return requisitionService.getAllRequisitionsByStatus("Approved");
    }

    public void setApprovedRequisitions(List<Requisition> approvedRequisitions) {
        this.approvedRequisitions = approvedRequisitions;
    }

    public List<Requisition> getRejectedRequisitions() {
        return requisitionService.getAllRequisitionsByStatus("Rejected");
    }

    public void setRejectedRequisitions(List<Requisition> rejectedRequisitions) {
        this.rejectedRequisitions = rejectedRequisitions;
    }

    public List<Requisition> getPaidRequisitions() {
        return requisitionService.getAllRequisitionsByStatus("Paid");
    }

    public void setPaidRequisitions(List<Requisition> paidRequisitions) {
        this.paidRequisitions = paidRequisitions;
    }
}
