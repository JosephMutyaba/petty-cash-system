package com.pahappa.systems.pettycashsystem.spring.services;

import com.pahappa.systems.pettycashsystem.exceptions.MinimumLengthException;
import com.pahappa.systems.pettycashsystem.exceptions.NameExistsException;
import com.pahappa.systems.pettycashsystem.exceptions.NullFieldException;
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

    public void createRole(Role role) throws NullFieldException, MinimumLengthException, NameExistsException {
        validateRole(role, "create");
        roleDAO.createRole(role);
    }

    public Role getRoleById(Long id) {
        return roleDAO.getRoleById(id);
    }

    public List<Role> getAllRoles() {
        return roleDAO.getAllRoles();
    }

    public void updateRole(Role role) throws NullFieldException, MinimumLengthException, NameExistsException {
        validateRole(role, "update");
        roleDAO.updateRole(role);
    }

    public void deleteRole(Long id) {
        roleDAO.deleteRole(id);
    }

    public Role findByName(String role_name) {
        return roleDAO.findByName(role_name);
    }

    public void deleteAllRoles() {
        roleDAO.deleteAllRoles();
    }

    public void validateRole(Role role, String action) throws MinimumLengthException, NullFieldException, NameExistsException {
        if(role.getName().isEmpty() || role.getName()==null) {
            throw new NullFieldException("Role name cannot be less than 3 characters");
        }

        if(role.getName().length() < 3) {
            throw new MinimumLengthException("Role name cannot be less than 3 characters");
        }

        if(role.getDescription().length() < 3) {
            throw new MinimumLengthException("Role name cannot be longer less 3 characters");
        }

        if(role.getDescription().isEmpty() || role.getDescription()==null) {
            throw new NullFieldException("Role description cannot be empty");
        }

        if (action.equals("create")) {
            if(roleDAO.getRoleByName(role.getName())!=null) {
                throw new NameExistsException("Role with that name already exists");
            }
        }

    }
}
