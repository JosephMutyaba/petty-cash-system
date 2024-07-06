package com.pahappa.systems.pettycashsystem.spring.services;

import com.pahappa.systems.pettycashsystem.spring.dao.RoleDAO;
import com.pahappa.systems.pettycashsystem.spring.models.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    private final RoleDAO roleDAO;

    @Autowired
    public RoleService(RoleDAO roleDAO) {
        this.roleDAO = roleDAO;
    }

    public List<Role> getAllRoles() {
        return roleDAO.getAllRoles();
    }

    public Role getRoleById(Long id) {
        return roleDAO.getRoleById(id);
    }

    public void createRole(Role role) {
        roleDAO.createRole(role);
    }

    public void updateRole(Long id, Role roleDetails) {
        Role role = roleDAO.getRoleById(id);

        if (role != null) {
            role.setName(roleDetails.getName());
            role.setDescription(roleDetails.getDescription());
            role.setUser(roleDetails.getUser());

            roleDAO.updateRole(role);
        } else {
            throw new RuntimeException("Role not found with id " + id);
        }
    }

    public void deleteRole(Long id) {
        roleDAO.deleteRole(id);
    }
}
