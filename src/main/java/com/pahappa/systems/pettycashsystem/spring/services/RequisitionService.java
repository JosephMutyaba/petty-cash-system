package com.pahappa.systems.pettycashsystem.spring.services;

import com.pahappa.systems.pettycashsystem.spring.dao.RequisitionDAO;
import com.pahappa.systems.pettycashsystem.spring.models.Requisition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RequisitionService {

    private final RequisitionDAO requisitionDAO;

    @Autowired
    public RequisitionService(RequisitionDAO requisitionDAO) {
        this.requisitionDAO = requisitionDAO;
    }

    public void createRequisition(Requisition requisition) {
        requisitionDAO.createRequisition(requisition);
    }

    public Requisition getRequisitionById(Long id) {
        return requisitionDAO.getRequisitionById(id);
    }

    public List<Requisition> getAllRequisitions() {
        return requisitionDAO.getAllRequisitions();
    }

    public void updateRequisition(Requisition requisition) {
        requisitionDAO.updateRequisition(requisition);
    }

    public void deleteRequisition(Long id) {
        requisitionDAO.deleteRequisition(id);
    }

    public List<Requisition> getAllRequisitionsByStatus(String reqStatus) {
        return requisitionDAO.getAllRequisitionsByStatus(reqStatus);
    }

    public void deleteRequisitionsByStatus(String status) {
        requisitionDAO.deleteRequisitionsByStatus(status);
    }
}
