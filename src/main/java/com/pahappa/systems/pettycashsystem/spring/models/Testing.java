package com.pahappa.systems.pettycashsystem.spring.models;

import com.pahappa.systems.pettycashsystem.spring.enums.Permission;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

public class Testing {
    public static void main(String[] args) {

        Set<Permission> permissions = new HashSet<>(EnumSet.allOf(Permission.class));
        permissions.remove(Permission.make_requisition);
        System.out.println(permissions);
    }
}
