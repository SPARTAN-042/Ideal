package com.spartan.ideal.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.micrometer.common.lang.NonNull;
import jakarta.persistence.*;
import org.springframework.security.core.GrantedAuthority;

import java.util.List;

@Entity
@Table(name = "user", uniqueConstraints = {
        @UniqueConstraint(columnNames = "user_name"),
        @UniqueConstraint(columnNames = "email")
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", unique = true)
    private Long userId;

    @Column(name = "user_name", unique = true)
    private String username;

    @Column(name = "email", unique = true)
    private String email;

    @JsonIgnore
    private String password;

    @Column(name = "role")
    private String role;


    @OneToMany(mappedBy = "user")
    private List<TrackedItem> trackedItems;

    public User() {
    }

    public User(Long userId, @NonNull String username, @NonNull String email, @NonNull String password, @NonNull String role) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.role = role;
    }


    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername( String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail( String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword( String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole( String role) {
        this.role = role;
    }

    public List<TrackedItem> getTrackedItems() {
        return trackedItems;
    }

    public void setTrackedItems(List<TrackedItem> trackedItems) {
        this.trackedItems = trackedItems;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                ", trackedItems=" + trackedItems +
                '}';
    }


}
