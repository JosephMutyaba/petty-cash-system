package com.pahappa.systems.pettycashsystem.spring.dao;

import com.pahappa.systems.pettycashsystem.spring.models.Role;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class RoleDAO {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Role> getAllRoles() {
        return entityManager.createQuery("from Role", Role.class).getResultList();
    }

    public Role getRoleById(Long id) {
        return entityManager.find(Role.class, id);
    }

    public void createRole(Role role) {
        entityManager.persist(role);
    }

    public void updateRole(Role role) {
        entityManager.merge(role);
    }

    public void deleteRole(Long id) {
        Role role = entityManager.find(Role.class, id);
        if (role != null) {
            entityManager.remove(role);
        }
    }
}
