package com.pahappa.systems.pettycashsystem.managedcontroller.requisitions;

import com.pahappa.systems.pettycashsystem.spring.models.Requisition;
import com.pahappa.systems.pettycashsystem.spring.services.RequisitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@ViewScoped
@Component
public class DeleteRequisition implements Serializable {

    @Autowired
    private RequisitionService requisitionService;

    private Requisition requisition;

    @Autowired
    private AllRequisitions allRequisitions;

    public void deleteSpecificRequisition() {
        requisitionService.deleteRequisition(requisition.getId());
        allRequisitions.update();
    }

    public void selectSpecificRequisition(Requisition sel_requisition) {
        this.requisition = sel_requisition;
    }

    public void deleteAllRequisitionsOfStatus() {
        String tabId = allRequisitions.getTabId();
        if (tabId == null) {
            System.err.println("Error: tabId is null");
            return;
        }

        switch (tabId) {
            case "pendingTab":
                System.out.println("\n\nPending deletion status......................\n\n");

                requisitionService.deleteRequisitionsByStatus("Pending");
                allRequisitions.update();
                break;
            case "approvedTab":
                System.out.println("\n\nApproved deletion status......................\n\n");
                requisitionService.deleteRequisitionsByStatus("Approved");
                allRequisitions.update();
                break;
            case "rejectedTab":
                requisitionService.deleteRequisitionsByStatus("Rejected");
                allRequisitions.update();
                break;
            case "editLineTab":
                requisitionService.deleteRequisitionsByStatus("Paid");
                allRequisitions.update();
                break;
            default:
                System.err.println("Error: Unknown tabId - " + tabId);
                break;
        }
    }

}
