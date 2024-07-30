package com.pahappa.systems.pettycashsystem.managedcontroller.roles;

import com.pahappa.systems.pettycashsystem.spring.models.Role;
import com.pahappa.systems.pettycashsystem.spring.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Named
@ViewScoped
@Component
public class AllRoles implements Serializable {
    @Autowired
    private RoleService roleService;

    private List<Role> roles;

    private String searchTerm;


    @PostConstruct
    public void init() {
        roles = roleService.getAllRoles();
    }

    public List<Role> getRoles() {
        roles = roleService.getAllRoles();
        filterRoles();
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    public void filterRoles() {
        if (searchTerm == null || searchTerm.isEmpty()) {
            roles=roleService.getAllRoles();
        } else {
            roles = roles.stream()
                    .filter(role ->
                            role.getName().toLowerCase().contains(searchTerm.toLowerCase()) ||
                                    String.valueOf(role.getDescription()).toLowerCase().contains(searchTerm.toLowerCase())
                    ).collect(Collectors.toList());
        }
    }

}
