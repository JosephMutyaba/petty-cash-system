package com.pahappa.systems.pettycashsystem.managedcontroller.admin;

import com.pahappa.systems.pettycashsystem.spring.enums.Permission;
import org.springframework.stereotype.Component;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

@Named
@ApplicationScoped
@Component
public class AllPermissions {
    public static final Set<Permission> PERMISSIONS = new HashSet<>(EnumSet.allOf(Permission.class));

    public Permission[] getPermissionsList() {
        return Permission.values();
    }

}
