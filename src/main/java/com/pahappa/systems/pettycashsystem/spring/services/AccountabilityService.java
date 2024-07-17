package com.pahappa.systems.pettycashsystem.spring.services;

import com.pahappa.systems.pettycashsystem.exceptions.NullFieldException;
import com.pahappa.systems.pettycashsystem.spring.dao.AccountabilityDAO;
import com.pahappa.systems.pettycashsystem.spring.models.Accountability;
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

    public void validateAccountability(Accountability accountability) throws NullFieldException {
        if (accountability.getAmountSpent() == null || accountability.getAmountSpent() < 0) {
            throw new NullFieldException("Invalid value for amount spent");
        }

        if (accountability.getRequisition() ==null) {
            throw new NullFieldException("requisition cannot be null");
        }

    }

}
