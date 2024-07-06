package com.pahappa.systems.pettycashsystem.spring.dao;

import com.pahappa.systems.pettycashsystem.spring.models.Accountability;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class AccountabilityDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public void save(Accountability accountability) {
        entityManager.persist(accountability);
    }

    public Accountability findById(Long id) {
        return entityManager.find(Accountability.class, id);
    }

    public void update(Accountability accountability) {
        entityManager.merge(accountability);
    }

    public void deleteById(Long id) {
        Accountability accountability = findById(id);
        if (accountability != null) {
            entityManager.remove(accountability);
        }
    }

    @SuppressWarnings("unchecked")
    public List<Accountability> findAll() {
        return entityManager.createQuery("FROM Accountability").getResultList();
    }
}
