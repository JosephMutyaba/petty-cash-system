package com.pahappa.systems.pettycashsystem.spring.dao;

import com.pahappa.systems.pettycashsystem.spring.models.Role;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RoleDAO {

    private final SessionFactory sessionFactory;

    @Autowired
    public RoleDAO(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void createRole(Role role) {
        sessionFactory.getCurrentSession().save(role);
    }

    public Role getRoleById(Long id) {
        return sessionFactory.getCurrentSession().get(Role.class, id);
    }

    public List<Role> getAllRoles() {
        return sessionFactory.getCurrentSession()
                .createQuery("FROM Role", Role.class)
                .getResultList();
    }

    public void updateRole(Role role) {
        sessionFactory.getCurrentSession().update(role);
    }

    public void deleteRole(Long id) {
        Role role = sessionFactory.getCurrentSession().load(Role.class, id);
        if (role != null) {
            sessionFactory.getCurrentSession().delete(role);
        }
    }
}
