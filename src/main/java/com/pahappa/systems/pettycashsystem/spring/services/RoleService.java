package com.pahappa.systems.pettycashsystem.spring.services;

import com.pahappa.systems.pettycashsystem.spring.dao.RoleDAO;
import com.pahappa.systems.pettycashsystem.spring.models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleService {

    private final RoleDAO roleDAO;

    @Autowired
    public RoleService(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    public void createRole(Role role) {
        roleDAO.createRole(role);
    }

    public Role getRoleById(Long id) {
        return roleDAO.getRoleById(id);
    }

    public List<Role> getAllRoles() {
        return roleDAO.getAllRoles();
    }

    public void updateRole(Role role) {
        roleDAO.updateRole(role);
    }

    public void deleteRole(Long id) {
        roleDAO.deleteRole(id);
    }

    public Role findByName(String role_name) {
        return roleDAO.findByName(role_name);
    }
}
