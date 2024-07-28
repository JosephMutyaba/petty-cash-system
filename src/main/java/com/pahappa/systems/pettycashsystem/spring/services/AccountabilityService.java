package com.pahappa.systems.pettycashsystem.spring.services;

import com.pahappa.systems.pettycashsystem.exceptions.IncompatibleValueException;
import com.pahappa.systems.pettycashsystem.exceptions.NullFieldException;
import com.pahappa.systems.pettycashsystem.spring.dao.AccountabilityDAO;
import com.pahappa.systems.pettycashsystem.spring.models.Accountability;
import com.pahappa.systems.pettycashsystem.spring.models.Requisition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AccountabilityService {

    private final AccountabilityDAO accountabilityDAO;

    @Autowired
    public AccountabilityService(AccountabilityDAO accountabilityDAO) {
        this.accountabilityDAO = accountabilityDAO;
    }

    public void createAccountability(Accountability accountability) {
        accountabilityDAO.createAccountability(accountability);
    }

    public Accountability getAccountabilityById(Long id) {
        return accountabilityDAO.getAccountabilityById(id);
    }

    public List<Accountability> getAllAccountabilities() {
        return accountabilityDAO.getAllAccountabilities();
    }

    public void updateAccountability(Accountability accountability) {
        accountabilityDAO.updateAccountability(accountability);
    }

    public void deleteAccountability(Long id) {
        accountabilityDAO.deleteAccountability(id);
    }

    public void validateAccountability(Accountability accountability, Requisition requisition) throws NullFieldException, IncompatibleValueException {
        if (accountability.getAmountSpent() == null || accountability.getAmountSpent() < 0) {
            throw new NullFieldException("Invalid value for amount spent");
        }

//        if (accountability.getRequisition() ==null) {
//            throw new NullFieldException("requisition cannot be null");
//        }

        if (accountability.getAmountSpent()>requisition.getAmountGranted()) {
            throw new IncompatibleValueException("Amount spent cannot be more than what was granted you.");
        }

    }

//    public Accountability getAccountabilityByRequisitionId(Long requisitionId) {
//        return accountabilityDAO.getAccountabilityByRequisitionId(requisitionId);
//    }
}
