package com.pahappa.systems.pettycashsystem.spring.models;

import javax.persistence.*;
import java.util.List;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String Firstname;
    private String Lastname;
    private String Email;
    private String Password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id")
    private Role role;

    private String username;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Requisition> requisition;

    public User() {}

    private User(Long id, String firstname, String lastname, String email, String password, Role role, String username, List<Requisition> requisition) {
        this.id = id;
        Firstname = firstname;
        Lastname = lastname;
        Email = email;
        Password = password;
        this.role = role;
        this.username = username;
        this.requisition = requisition;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return Firstname;
    }

    public void setFirstname(String firstname) {
        Firstname = firstname;
    }

    public String getLastname() {
        return Lastname;
    }

    public void setLastname(String lastname) {
        Lastname = lastname;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Requisition> getRequisition() {
        return requisition;
    }

    public void setRequisition(List<Requisition> requisition) {
        this.requisition = requisition;
    }
}
