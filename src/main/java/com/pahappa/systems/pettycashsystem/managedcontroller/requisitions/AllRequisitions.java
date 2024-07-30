package com.pahappa.systems.pettycashsystem.managedcontroller.requisitions;

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
import java.util.stream.Collectors;

@Named
@ViewScoped
@Component
public class AllRequisitions implements Serializable {
    @Autowired
    private RequisitionService requisitionService;

    private String tabId;

    private String searchTerm;

    private List<Requisition> pendingRequisitions;
    private List<Requisition> approvedRequisitions;
    private List<Requisition> reviewedRequisitions;
    private List<Requisition> rejectedRequisitions;
    private List<Requisition> paidRequisitions;
    private List<Requisition> completedRequisitions;
    private List<Requisition> expiredRequisitions;


    @PostConstruct
    public void init() {
        reviewedRequisitions=requisitionService.getAllRequisitionsByStatus("Reviewed");
        paidRequisitions=requisitionService.getAllPaidRequisitionsByStatus();
        completedRequisitions=requisitionService.getAllCompletedRequisitionsByStatus();
        pendingRequisitions=requisitionService.getAllRequisitionsByStatus("Pending");
        rejectedRequisitions=requisitionService.getAllRequisitionsByStatus("Rejected");
        approvedRequisitions=requisitionService.getAllRequisitionsByStatus("Approved");
        expiredRequisitions=requisitionService.getAllExpiredRequisitions();
        tabId="pendingTab";
    }

    public String getTabId() {
        return tabId;
    }

    public void setTabId(String tabId) {
        this.tabId = tabId;
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
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
        return requisitionService.getAllPaidRequisitionsByStatus();
    }

    public void setPaidRequisitions(List<Requisition> paidRequisitions) {
        this.paidRequisitions = requisitionService.getAllPaidRequisitionsByStatus();;
    }

    public List<Requisition> getExpiredRequisitions() {
        return requisitionService.getAllExpiredRequisitions();
    }

    public void setExpiredRequisitions(List<Requisition> expiredRequisitions) {
        this.expiredRequisitions = requisitionService.getAllExpiredRequisitions();
    }

    public List<Requisition> getCompletedRequisitions() {
        return requisitionService.getAllCompletedRequisitionsByStatus();
    }

    public void setCompletedRequisitions(List<Requisition> completedRequisitions) {
        this.completedRequisitions = requisitionService.getAllCompletedRequisitionsByStatus();;
    }

    public List<Requisition> getReviewedRequisitions() {
       return requisitionService.getAllRequisitionsByStatus("Reviewed");
    }

    public void setReviewedRequisitions(List<Requisition> reviewedRequisitions) {
        this.reviewedRequisitions = requisitionService.getAllRequisitionsByStatus("Reviewed");
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
            case "reviewed":
                activeTab = 1;
                requisitionsForActiveTab = getReviewedRequisitions();
                break;
            case "approvedTab":
                activeTab = 2;
                requisitionsForActiveTab = getApprovedRequisitions();
                break;
            case "rejectedTab":
                activeTab = 3;
                requisitionsForActiveTab = getRejectedRequisitions();
                break;
            case "paid":
                activeTab = 4;
                requisitionsForActiveTab = getPaidRequisitions();
                break;
            case "completed":
                activeTab = 5;
                requisitionsForActiveTab = getCompletedRequisitions();
                break;
            case "expired":
                activeTab = 6;
                requisitionsForActiveTab = getExpiredRequisitions();
                break;
        }
        filterRequisitionsForActiveTab();
    }



    public void update() {
        switch (activeTab) {
            case 0:
                requisitionsForActiveTab = getPendingRequisitions();
                break;
            case 1:
                requisitionsForActiveTab= getReviewedRequisitions();
                break;
            case 2:
                requisitionsForActiveTab = getApprovedRequisitions();
                break;
            case 3:
                requisitionsForActiveTab = getRejectedRequisitions();
                break;
            case 4:
                requisitionsForActiveTab = getPaidRequisitions();
                break;
            case 5:
                requisitionsForActiveTab = getCompletedRequisitions();
                break;
            case 6:
                requisitionsForActiveTab = getExpiredRequisitions();
                break;
            default:
                System.err.println("Error: Unknown activeTab - " + activeTab);
                break;
        }
        filterRequisitionsForActiveTab();
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


    public void filterRequisitionsForActiveTab() {
        if (searchTerm == null || searchTerm.isEmpty()) {
            switch (activeTab) {
                case 0:
                    requisitionsForActiveTab = getPendingRequisitions();
                    break;
                case 1:
                    requisitionsForActiveTab = getReviewedRequisitions();
                    break;
                case 2:
                    requisitionsForActiveTab = getApprovedRequisitions();
                    break;
                case 3:
                    requisitionsForActiveTab = getRejectedRequisitions();
                    break;
                case 4:
                    requisitionsForActiveTab = getPaidRequisitions();
                    break;
                case 5:
                    requisitionsForActiveTab = getCompletedRequisitions();
                    break;
                case 6:
                    requisitionsForActiveTab = getExpiredRequisitions();
                    break;
                default:
                    System.err.println("Error: Unknown activeTab - " + activeTab);
                    break;
            }
        } else {
            requisitionsForActiveTab = requisitionsForActiveTab.stream()
                    .filter(requisition ->
                            requisition.getStatus().toLowerCase().contains(searchTerm.toLowerCase()) ||
                                    String.valueOf(requisition.getBudgetline().getDescription()).toLowerCase().contains(searchTerm.toLowerCase()) ||
                                    String.valueOf(requisition.getMaxDateNeeded()).toLowerCase().contains(searchTerm.toLowerCase()) ||
                                    String.valueOf(requisition.getUser().getUsername()).toLowerCase().contains(searchTerm.toLowerCase()) ||
                                    String.valueOf(requisition.getJustification()).toLowerCase().contains(searchTerm.toLowerCase()))
                    .collect(Collectors.toList());
        }
    }



}
