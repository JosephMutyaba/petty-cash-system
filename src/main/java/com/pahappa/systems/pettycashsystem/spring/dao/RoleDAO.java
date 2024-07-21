package com.pahappa.systems.pettycashsystem.spring.dao;

import com.pahappa.systems.pettycashsystem.spring.models.Role;
import com.pahappa.systems.pettycashsystem.spring.models.User;
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
                .createQuery("FROM Role ORDER BY id DESC", Role.class)
                .getResultList();
    }

    public void updateRole(Role role) {
        sessionFactory.getCurrentSession().update(role);
    }

    public void deleteRole(Long id) {
        Role role = sessionFactory.getCurrentSession().load(Role.class, id);

        Role defaultRole= (Role) sessionFactory.getCurrentSession().createQuery("FROM Role WHERE name='USER'").uniqueResult();


        if (role != null) {

            List<User> users = sessionFactory.getCurrentSession().createQuery("FROM User").getResultList();

            for (User user : users) {
                if (user.getRoles().contains(role)) {
                    if (user.getRoles().size()>1) {
                        user.getRoles().remove(role);
                    }else {
                        user.getRoles().clear();
                        user.getRoles().add(defaultRole);
                    }
                    sessionFactory.getCurrentSession().update(user);
                }
            }

            ////////////////////////////////
            sessionFactory.getCurrentSession().delete(role);
        }
    }

    public Role findByName(String role_name) {
        return (Role) sessionFactory.getCurrentSession().createQuery("FROM Role WHERE name=:role_name")
                .setParameter("role_name",role_name).uniqueResult();
    }

    public void deleteAllRoles() {
//        sessionFactory.getCurrentSession().createQuery("DELETE FROM User").executeUpdate();

        List<User> users = sessionFactory.getCurrentSession().createQuery("FROM User").getResultList();

        Role defaultRole= (Role) sessionFactory.getCurrentSession().createQuery("FROM Role WHERE name='USER'").uniqueResult();

        List<Role> roles=sessionFactory.getCurrentSession().createQuery("FROM Role").getResultList();

        for (Role role : roles) {
            for (User user : users) {
                if (user.getRoles().contains(role)) {
                    if (user.getRoles().size()>1) {
                        user.getRoles().remove(role);
                    }else {
                        user.getRoles().clear();
                        user.getRoles().add(defaultRole);
                    }
                    sessionFactory.getCurrentSession().update(user);
                }
            }
        }


        for (Role role : roles) {
            if (!role.getName().equals("USER")) {
                sessionFactory.getCurrentSession().delete(role);
            }
        }
    }

    public Role getRoleByName(String name) {
        return (Role) sessionFactory.getCurrentSession()
                .createQuery("FROM Role WHERE name=:role_name")
                .setParameter("role_name",name)
                .uniqueResult();
    }
}
