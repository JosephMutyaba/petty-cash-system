package com.pahappa.systems.pettycashsystem.managedcontroller;

import com.pahappa.systems.pettycashsystem.managedcontroller.login.LoginBean;
import com.pahappa.systems.pettycashsystem.spring.enums.Perm;
import com.pahappa.systems.pettycashsystem.spring.models.Role;
import com.pahappa.systems.pettycashsystem.spring.models.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Named
@SessionScoped
public class CheckPermission implements Serializable {
    @Autowired
    private LoginBean loginBean;

    private Set<Role> userRoles;

    @PostConstruct
    public void init() {
        userRoles = new HashSet<>();
    }

    public boolean hasPermission(Perm permission) {
        User user = loginBean.getLoggedInUser();
        userRoles = user.getRoles();

        for (Role role : userRoles) {
            if (role.getPermissions().stream().anyMatch(p -> p.getName() == permission)) {
                return true;
            }
        }
        return false;
    }
    public boolean hasPermission(Perm... permissions) {
        User user = loginBean.getLoggedInUser();
        userRoles = user.getRoles();
        for (Role role : userRoles) {
            for (Perm perm:permissions) {
                if (role.getPermissions().stream().anyMatch(p -> p.getName() == perm)){
                    return true;
                }
            }
        }

        return false;
    }
}

