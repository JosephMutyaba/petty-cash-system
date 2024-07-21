package com.pahappa.systems.pettycashsystem.managedcontroller.admin;

import com.pahappa.systems.pettycashsystem.managedcontroller.login.LoginBean;
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
public class MyRequisitions implements Serializable {
    @Autowired
    private RequisitionService requisitionService;

    @Autowired
    private LoginBean loginBean;

    private String tabId;

    private String searchTerm;

    private List<Requisition> completedRequisitions;
    private List<Requisition> expiredRequisitions;
    private List<Requisition> rejectedRequisitions;

    @PostConstruct
    public void init() {
        completedRequisitions=requisitionService.getAllRequisitionsByStatusAndUserId("Completed", loginBean.getLoggedInUser().getId());
        expiredRequisitions=requisitionService.getAllRequisitionsExpiredButNotRejectedAndNotCompleted(loginBean.getLoggedInUser().getId());
        rejectedRequisitions=requisitionService.getAllRequisitionsByStatusAndUserId("Rejected", loginBean.getLoggedInUser().getId());
        tabId="completedTab";
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

    public List<Requisition> getCompletedRequisitions() {
       return requisitionService.getAllRequisitionsByStatusAndUserId("Completed", loginBean.getLoggedInUser().getId());
        //        return completedRequisitions;
    }

    public void setCompletedRequisitions(List<Requisition> completedRequisitions) {
        this.completedRequisitions = requisitionService.getAllRequisitionsByStatusAndUserId("Completed", loginBean.getLoggedInUser().getId());
    }

    public List<Requisition> getExpiredRequisitions() {
        return requisitionService.getAllRequisitionsExpiredButNotRejectedAndNotCompleted(loginBean.getLoggedInUser().getId());
    }

    public void setExpiredRequisitions(List<Requisition> expiredRequisitions) {
        this.expiredRequisitions =  requisitionService.getAllRequisitionsExpiredButNotRejectedAndNotCompleted(loginBean.getLoggedInUser().getId());
    }

    public List<Requisition> getRejectedRequisitions() {
        return requisitionService.getAllRequisitionsByStatusAndUserId("Rejected", loginBean.getLoggedInUser().getId());
    }

    public void setRejectedRequisitions(List<Requisition> rejectedRequisitions) {
        this.rejectedRequisitions = requisitionService.getAllRequisitionsByStatusAndUserId("Rejected", loginBean.getLoggedInUser().getId());

    }

    private int activeTab = 0;
    private List<Requisition> requisitionsForActiveTab;

    public void onTabChange(TabChangeEvent event) {
        tabId = event.getTab().getId();
        switch (tabId) {
            case "completedTab":
                activeTab = 0;
                requisitionsForActiveTab = getCompletedRequisitions();
                break;
            case "rejectedTab":
                activeTab = 1;
                requisitionsForActiveTab = getRejectedRequisitions();
                break;
            case "expiredTab":
                activeTab = 2;
                requisitionsForActiveTab = getExpiredRequisitions();
                break;
        }

        filterRequisitionsForActiveTab();
    }



    public void update() {
        switch (activeTab) {
            case 0:
                requisitionsForActiveTab = getCompletedRequisitions();
                break;
            case 1:
                requisitionsForActiveTab = getRejectedRequisitions();
                break;
            case 2:
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
            requisitionsForActiveTab = getCompletedRequisitions();
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
                    requisitionsForActiveTab = getCompletedRequisitions();
                    break;
                case 1:
                    requisitionsForActiveTab = getRejectedRequisitions();
                    break;
                case 2:
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
