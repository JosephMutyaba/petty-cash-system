package com.pahappa.systems.pettycashsystem.spring.services;

import com.pahappa.systems.pettycashsystem.spring.dao.AccountabilityDAO;
import com.pahappa.systems.pettycashsystem.spring.models.Accountability;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountabilityService {

    @Autowired
    private AccountabilityDAO accountabilityDAO;

    public void save(Accountability accountability) {
        accountabilityDAO.save(accountability);
    }

    public Accountability findById(Long id) {
        return accountabilityDAO.findById(id);
    }

    public void update(Accountability accountability) {
        accountabilityDAO.update(accountability);
    }

    public void deleteById(Long id) {
        accountabilityDAO.deleteById(id);
    }

    public List<Accountability> findAll() {
        return accountabilityDAO.findAll();
    }
}
