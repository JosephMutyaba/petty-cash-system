package com.pahappa.systems.pettycashsystem.spring.models;

import com.pahappa.systems.pettycashsystem.spring.enums.Perm;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Perm name;

    // Getters and Setters
    public Permission() {}

    private Permission(Perm name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Perm getName() {
        return name;
    }

    public void setName(Perm name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Permission that = (Permission) o;
        return Objects.equals(getId(), that.getId()) && getName() == that.getName();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }

    @Override
    public String toString() {
        return name.toString();
    }
}