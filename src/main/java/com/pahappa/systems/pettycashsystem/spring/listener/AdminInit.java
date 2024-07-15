package com.pahappa.systems.pettycashsystem.spring.listener;

import com.pahappa.systems.pettycashsystem.exceptions.MinimumLengthException;
import com.pahappa.systems.pettycashsystem.exceptions.NameExistsException;
import com.pahappa.systems.pettycashsystem.exceptions.NullFieldException;
import com.pahappa.systems.pettycashsystem.spring.enums.Perm;
import com.pahappa.systems.pettycashsystem.spring.models.Permission;
import com.pahappa.systems.pettycashsystem.spring.models.Role;
import com.pahappa.systems.pettycashsystem.spring.models.User;
import com.pahappa.systems.pettycashsystem.spring.services.PermissionService;
import com.pahappa.systems.pettycashsystem.spring.services.RoleService;
import com.pahappa.systems.pettycashsystem.spring.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

@Component
public class AdminInit {

    private static final Logger LOGGER = Logger.getLogger(AdminInit.class.getName());

    private final RoleService roleService;
    private final UserService userService;
    private final PermissionService permissionService;

    @Autowired
    public AdminInit(RoleService roleService, UserService userService, PermissionService permissionService) {
        this.roleService = roleService;
        this.userService = userService;
        this.permissionService = permissionService;
    }

    @PostConstruct
    public void init() {
        LOGGER.info("Initializing Admin User...");
        initializeAdminUser();
    }

    private void initializeAdminUser() {
        LOGGER.info("Initializing Permissions...");
        initializePermissions();

        LOGGER.info("Checking for existing admin role...");
        Role adminRole = roleService.findByName("ADMIN");

        if (adminRole == null) {
            LOGGER.info("Admin role not found. Creating admin role...");
            adminRole = new Role();
            adminRole.setName("ADMIN");
            adminRole.setDescription("Admin role");

            // Assign all permissions except 'make_requisition' to admin role
            Set<Permission> permissions = new HashSet<>(permissionService.findAll());
            permissions.removeIf(permission -> permission.getName() == Perm.MAKE_REQUISITION);
            adminRole.setPermissions(permissions);

            try {
                roleService.createRole(adminRole);
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",e.getMessage()));
            }
        }

        LOGGER.info("Checking for existing admin users...");
        List<User> adminUsers = userService.findByRoleName("ADMIN");

        if (adminUsers.isEmpty()) {
            LOGGER.info("No admin users found. Creating admin user...");
            User adminUser = new User();
            adminUser.setUsername("admin");
            adminUser.setPassword("adminPassword"); // Encrypt password in a real application
            adminUser.setEmail("admin@example.com");
            adminUser.setRole(adminRole);
            adminUser.setFirstname("Bob");
            adminUser.setLastname("Alexander");
            try {
                userService.createUser(adminUser);
            } catch (Exception e) {
                FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",e.getMessage()));
            }
        } else {
            LOGGER.info("Admin user(s) already exist.");
        }
    }

    private void initializePermissions() {
        for (Perm perm : Perm.values()) {
            Permission permission = permissionService.findByName(perm);
            if (permission == null) {
                permission = new Permission();
                permission.setName(perm);
                permissionService.createPermission(permission);
            }
        }
    }
}
