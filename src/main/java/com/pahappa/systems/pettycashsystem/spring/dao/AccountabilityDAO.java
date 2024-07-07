package com.pahappa.systems.pettycashsystem.spring.dao;

import com.pahappa.systems.pettycashsystem.spring.models.Accountability;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AccountabilityDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public AccountabilityDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void createAccountability(Accountability accountability) {
        sessionFactory.getCurrentSession().save(accountability);
    }

    public Accountability getAccountabilityById(Long id) {
        return sessionFactory.getCurrentSession().get(Accountability.class, id);
    }

    public List<Accountability> getAllAccountabilities() {
        return sessionFactory.getCurrentSession()
                .createQuery("FROM Accountability", Accountability.class)
                .getResultList();
    }

    public void updateAccountability(Accountability accountability) {
        sessionFactory.getCurrentSession().update(accountability);
    }

    public void deleteAccountability(Long id) {
        Accountability accountability = sessionFactory.getCurrentSession().load(Accountability.class, id);
        if (accountability != null) {
            sessionFactory.getCurrentSession().delete(accountability);
        }
    }
}
