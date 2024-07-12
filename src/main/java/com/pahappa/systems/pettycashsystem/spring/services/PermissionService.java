package com.pahappa.systems.pettycashsystem.spring.services;

import com.pahappa.systems.pettycashsystem.spring.dao.PermissionDAO;
import com.pahappa.systems.pettycashsystem.spring.enums.Perm;
import com.pahappa.systems.pettycashsystem.spring.models.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PermissionService {

    @Autowired
    private PermissionDAO permissionDAO;


    public void createPermission(Permission permission) {
        permissionDAO.createPermission(permission);
    }

    public Permission findByName(Perm name) {
        return permissionDAO.findByName(name);
    }

    public List<Permission> findAll() {
        return permissionDAO.findAll();
    }
}
