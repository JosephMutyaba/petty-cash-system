package com.pahappa.systems.pettycashsystem.spring.models;

import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String Firstname;
    private String Lastname;

    private Double accountBalance=0.0;

    @Column(unique = true)
    private String Email;

    private String Password;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;

    @Column(nullable = false, unique = true)
    private String username;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
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

    public Double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(Double accountBalance) {
        this.accountBalance = accountBalance;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(getId(), user.getId()) && Objects.equals(getFirstname(), user.getFirstname()) && Objects.equals(getLastname(), user.getLastname()) && Objects.equals(getEmail(), user.getEmail()) && Objects.equals(getPassword(), user.getPassword()) && Objects.equals(getRole(), user.getRole()) && Objects.equals(getUsername(), user.getUsername()) && Objects.equals(getRequisition(), user.getRequisition());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getFirstname(), getLastname(), getEmail(), getPassword(), getRole(), getUsername(), getRequisition());
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", Firstname='" + Firstname + '\'' +
                ", Lastname='" + Lastname + '\'' +
                ", Email='" + Email +
                '}';
    }
}
