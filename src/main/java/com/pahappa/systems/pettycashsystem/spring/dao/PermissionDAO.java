package com.pahappa.systems.pettycashsystem.spring.dao;

import com.pahappa.systems.pettycashsystem.spring.enums.Perm;
import com.pahappa.systems.pettycashsystem.spring.models.Permission;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PermissionDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void createPermission(Permission permission) {
        sessionFactory.getCurrentSession().saveOrUpdate(permission);
    }

    public Permission findByName(Perm name) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Permission where name = :name", Permission.class)
                .setParameter("name", name)
                .uniqueResult();
    }

    public List<Permission> findAll() {
        return sessionFactory.getCurrentSession().createQuery("from Permission").getResultList();
    }
}
