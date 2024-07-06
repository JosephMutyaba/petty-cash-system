package com.pahappa.systems.pettycashsystem.spring.services;

import com.pahappa.systems.pettycashsystem.spring.dao.RequisitionDAO;
import com.pahappa.systems.pettycashsystem.spring.models.Requisition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequisitionService {

    private final RequisitionDAO requisitionDAO;

    @Autowired
    public RequisitionService(RequisitionDAO requisitionDAO) {
        this.requisitionDAO = requisitionDAO;
    }

    public List<Requisition> getAllRequisitions() {
        return requisitionDAO.getAllRequisitions();
    }

    public Requisition getRequisitionById(Long id) {
        return requisitionDAO.getRequisitionById(id);
    }

    public void createRequisition(Requisition requisition) {
        requisitionDAO.createRequisition(requisition);
    }

    public void updateRequisition(Long id, Requisition requisitionDetails) {
        Requisition requisition = requisitionDAO.getRequisitionById(id);

        if (requisition != null) {
            requisition.setJustification(requisitionDetails.getJustification());
            requisition.setUser(requisitionDetails.getUser());
            requisition.setAccountability(requisitionDetails.getAccountability());
            requisition.setBudgetline(requisitionDetails.getBudgetline());
            requisition.setOps_man_review(requisitionDetails.getOps_man_review());
            requisition.setCeo_review(requisitionDetails.getCeo_review());
            requisition.setEstimatedAmount(requisitionDetails.getEstimatedAmount());
            requisition.setAmountGranted(requisitionDetails.getAmountGranted());
            requisition.setStatus(requisitionDetails.getStatus());
            requisition.setDateCreated(requisitionDetails.getDateCreated());
            requisition.setMaxDateNeeded(requisitionDetails.getMaxDateNeeded());
            requisition.setDateApproved(requisitionDetails.getDateApproved());

            requisitionDAO.updateRequisition(requisition);
        } else {
            throw new RuntimeException("Requisition not found with id " + id);
        }
    }

    public void deleteRequisition(Long id) {
        requisitionDAO.deleteRequisition(id);
    }
}
