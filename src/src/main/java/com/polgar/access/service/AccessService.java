package com.polgar.access.service;

import com.polgar.access.model.Permission;
import com.polgar.access.model.Role;
import com.polgar.access.model.User;

import java.util.*;

public class AccessService {
    private final List<User> users = new ArrayList<>();
    private long nextId = 1;

    private static final Map<Role, Set<Permission>> ROLE_PERMISSIONS = Map.of(
            Role.ADMIN, EnumSet.allOf(Permission.class),
            Role.USER, EnumSet.of(Permission.READ, Permission.WRITE),
            Role.GUEST, EnumSet.of(Permission.READ)
    );

    public User createUser(String username, Role role) {
        User user = new User(nextId++, username, role);
        users.add(user);
        return user;
    }

    public List<User> getAllUsers() {
        return List.copyOf(users);
    }

    public Optional<User> findById(long id) {
        return users.stream().filter(u -> u.getId() == id).findFirst();
    }

    public boolean changeRole(long userId, Role newRole) {
        Optional<User> maybe = findById(userId);
        if (maybe.isEmpty()) return false;
        maybe.get().setRole(newRole);
        return true;
    }

    public boolean canAccess(long userId, Permission permission) {
        Optional<User> maybe = findById(userId);
        if (maybe.isEmpty()) return false;

        Role role = maybe.get().getRole();
        return ROLE_PERMISSIONS.getOrDefault(role, Set.of()).contains(permission);
    }
}
