package com.pahappa.systems.pettycashsystem.spring.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class Role {

    @Id
    private Long id;
    private String name;
    private String description;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<User> user;

    public Role() {}

    private Role(Long id, String name, String description, List<User> user) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<User> getUser() {
        return user;
    }

    public void setUser(List<User> user) {
        this.user = user;
    }
}
