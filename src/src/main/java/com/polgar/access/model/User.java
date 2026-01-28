package com.polgar.access.model;

import java.util.Objects;

public class User {
    private final long id;
    private final String username;
    private Role role;

    public User(long id, String username, Role role) {
        if (username == null || username.isBlank()) {
            throw new IllegalArgumentException("Username must not be blank.");
        }
        this.id = id;
        this.username = username.trim();
        this.role = Objects.requireNonNull(role, "Role must not be null.");
    }

    public long getId() { return id; }
    public String getUsername() { return username; }
    public Role getRole() { return role; }

    public void setRole(Role role) {
        this.role = Objects.requireNonNull(role, "Role must not be null.");
    }

    @Override
    public String toString() {
        return "User{id=" + id + ", username='" + username + "', role=" + role + "}";
    }
}
