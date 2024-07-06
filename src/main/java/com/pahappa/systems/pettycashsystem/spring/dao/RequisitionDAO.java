package com.pahappa.systems.pettycashsystem.spring.dao;

import com.pahappa.systems.pettycashsystem.spring.models.Requisition;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class RequisitionDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Requisition> getAllRequisitions() {
        return entityManager.createQuery("from Requisition", Requisition.class).getResultList();
    }

    public Requisition getRequisitionById(Long id) {
        return entityManager.find(Requisition.class, id);
    }

    public void createRequisition(Requisition requisition) {
        entityManager.persist(requisition);
    }

    public void updateRequisition(Requisition requisition) {
        entityManager.merge(requisition);
    }

    public void deleteRequisition(Long id) {
        Requisition requisition = entityManager.find(Requisition.class, id);
        if (requisition != null) {
            entityManager.remove(requisition);
        }
    }
}
