package com.pahappa.systems.pettycashsystem.managedcontroller;

import com.pahappa.systems.pettycashsystem.managedcontroller.login.LoginBean;
import com.pahappa.systems.pettycashsystem.spring.enums.Perm;
import com.pahappa.systems.pettycashsystem.spring.models.User;
import org.springframework.beans.factory.annotation.Autowired;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;

@Named
@SessionScoped
public class CheckPermission implements Serializable {
    @Autowired
    private LoginBean loginBean;

    public boolean hasPermission(Perm permission) {
        User user = loginBean.getLoggedInUser();
        return user.getRole().getPermissions().stream()
                .anyMatch(p -> p.getName() == permission);
    }
    public boolean hasPermission(Perm... permissions) {
        User user = loginBean.getLoggedInUser();
        for (Perm perm:permissions) {
            if (user.getRole().getPermissions().stream()
                    .anyMatch(p -> p.getName() == perm))
                return true;
        }
        return false;
    }
}

