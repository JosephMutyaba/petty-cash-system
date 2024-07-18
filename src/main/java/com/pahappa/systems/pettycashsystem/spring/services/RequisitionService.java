package com.pahappa.systems.pettycashsystem.spring.services;

import com.pahappa.systems.pettycashsystem.exceptions.IncompatibleDatesException;
import com.pahappa.systems.pettycashsystem.exceptions.MinimumLengthException;
import com.pahappa.systems.pettycashsystem.exceptions.NullFieldException;
import com.pahappa.systems.pettycashsystem.spring.dao.RequisitionDAO;
import com.pahappa.systems.pettycashsystem.spring.models.BudgetLine;
import com.pahappa.systems.pettycashsystem.spring.models.Requisition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class RequisitionService {

    private final RequisitionDAO requisitionDAO;

    @Autowired
    public RequisitionService(RequisitionDAO requisitionDAO) {
        this.requisitionDAO = requisitionDAO;
    }

    public void createRequisition(Requisition requisition) throws IncompatibleDatesException, NullFieldException {
        validateRequisition(requisition);
        requisitionDAO.createRequisition(requisition);
    }

    public Requisition getRequisitionById(Long id) {
        return requisitionDAO.getRequisitionById(id);
    }

    public List<Requisition> getAllRequisitions() {
        return requisitionDAO.getAllRequisitions();
    }

    public void updateRequisition(Requisition requisition) throws IncompatibleDatesException, NullFieldException {
        validateRequisition(requisition);
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

    public void validateRequisition(Requisition requisition) throws NullFieldException, IncompatibleDatesException {
        if (requisition.getJustification()==null || requisition.getJustification().trim().isEmpty()) {
            throw new NullFieldException("Justification must be filled");
        }

        if (requisition.getMaxDateNeeded()==null) {
            throw new NullFieldException("Date needed field cannot be empty");
        }

        if (requisition.getMaxDateNeeded().before(new Date())) {
            throw new NullFieldException("Date needed cannot be in the past.");
        }

//        if (requisition.getEstimatedAmount().isNaN()) {
//            throw new NullFieldException("Amount requested should be a number");
//        }

        if (requisition.getEstimatedAmount()==null) {
            throw new NullFieldException("Amount cannot be empty");
        }

        if (requisition.getEstimatedAmount()<1000.0) {
            throw new NullFieldException("Amount cannot be less than 1,000");
        }

        if (requisition.getEstimatedAmount()>requisition.getBudgetline().getBalance()){
            throw new NullFieldException("You cannot requisite more than"+requisition.getBudgetline().getBalance());
        }
    }


    public Requisition getRequisitionByUserIdAndStatusAndMaxDateNotExpired(Long userId) {
        return requisitionDAO.getRequisitionByUserIdAndStatusAndMaxDateNotExpired(userId);
    }

    public List<Requisition> getAllRequisitionsExpiredButNotRejectedAndNotCompleted(Long userId) {
        return requisitionDAO.getAllRequisitionsExpiredButNotRejectedAndNotCompleted(userId);
    }

    public List<Requisition> getAllRequisitionsByStatusAndUserId(String status, Long userId) {
        return requisitionDAO.getAllRequisitionsByStatusAndUserId(status, userId);
    }

    public List<Requisition> getAllExpiredRequisitions() {
        return requisitionDAO.getAllExpiredRequisitions();
    }

    public List<Requisition> getAllPaidRequisitionsByStatus() {
        return requisitionDAO.getAllPaidRequisitionsByStatus();
    }

    public List<Requisition> getAllCompletedRequisitionsByStatus() {
        return requisitionDAO.getAllCompletedRequisitionsByStatus();
    }



    //////////////// VALIDATE WHEN DISBURSING /
    public void validateBeforeDisbursement(Double budgetLineBalance, String name) throws MinimumLengthException {
        if (budgetLineBalance<0) {
            throw new MinimumLengthException("Insufficient Balance on the "+name+"to cash this requisition");
        }
    }
}
