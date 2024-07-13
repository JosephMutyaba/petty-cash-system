package com.pahappa.systems.pettycashsystem.spring.listener;

import com.pahappa.systems.pettycashsystem.managedcontroller.admin.AllPermissions;
import com.pahappa.systems.pettycashsystem.spring.enums.Permission;
import com.pahappa.systems.pettycashsystem.spring.models.Role;
import com.pahappa.systems.pettycashsystem.spring.models.User;
import com.pahappa.systems.pettycashsystem.spring.services.RoleService;
import com.pahappa.systems.pettycashsystem.spring.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.logging.Logger;

@Component
public class AdminInit {

    private static final Logger LOGGER = Logger.getLogger(AdminInit.class.getName());

    private final RoleService roleService;
    private final UserService userService;

    @Autowired
    public AdminInit(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @PostConstruct
    public void init() {
        LOGGER.info("Initializing Admin User...");
        initializeAdminUser();
    }

    private void initializeAdminUser() {
        LOGGER.info("Checking for existing admin role...");
        Role adminRole = roleService.findByName("ADMIN");

        if (adminRole == null) {
            LOGGER.info("Admin role not found. Creating admin role...");
            adminRole = new Role();
            adminRole.setName("ADMIN");
            adminRole.setDescription("Admin role");
//            Permissions
            Set<Permission> adminPermissions = AllPermissions.PERMISSIONS;
            adminPermissions.remove(Permission.make_requisition);
            adminRole.setPermissions(adminPermissions);
            roleService.createRole(adminRole);
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
            userService.createUser(adminUser);
        } else {
            LOGGER.info("Admin user(s) already exist.");
        }
    }
}
