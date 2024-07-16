package com.pahappa.systems.pettycashsystem.managedcontroller.admin;

import com.pahappa.systems.pettycashsystem.spring.models.Requisition;
import com.pahappa.systems.pettycashsystem.spring.services.RequisitionService;
import org.primefaces.event.TabChangeEvent;
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

    private String tabId;

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
        tabId="Pending";
    }

    public String getTabId() {
        return tabId;
    }

    public void setTabId(String tabId) {
        this.tabId = tabId;
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

    public boolean isStatus(String status, String compareTo) {
        return compareTo.equals(status);
    }









    private int activeTab = 0;
    private List<Requisition> requisitionsForActiveTab;


    public void onTabChange(TabChangeEvent event) {
        tabId = event.getTab().getId();
        switch (tabId) {
            case "pendingTab":
                activeTab = 0;
                requisitionsForActiveTab = getPendingRequisitions();
                break;
            case "approvedTab":
                activeTab = 1;
                requisitionsForActiveTab = getApprovedRequisitions();
                break;
            case "rejectedTab":
                activeTab = 2;
                requisitionsForActiveTab = getRejectedRequisitions();
                break;
            case "paid":
                activeTab = 3;
                requisitionsForActiveTab = getPaidRequisitions();
                break;
        }
    }



    public void update() {
        switch (activeTab) {
            case 0:
                requisitionsForActiveTab = getPendingRequisitions();
                break;
            case 1:
                requisitionsForActiveTab = getApprovedRequisitions();
                break;
            case 2:
                requisitionsForActiveTab = getRejectedRequisitions();
                break;
            case 3:
                requisitionsForActiveTab = getPaidRequisitions();
                break;
            default:
                System.err.println("Error: Unknown activeTab - " + activeTab);
                break;
        }
    }



    public List<Requisition> getRequisitionsForActiveTab() {
        if (requisitionsForActiveTab == null) {
            requisitionsForActiveTab = getPendingRequisitions();
        }
        return requisitionsForActiveTab;
    }

    public int getActiveTab() {
        return activeTab;
    }

    public void setActiveTab(int activeTab) {
        this.activeTab = activeTab;
    }
}
