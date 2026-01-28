package com.polgar.access.service;

import com.polgar.access.model.Permission;
import com.polgar.access.model.Role;
import com.polgar.access.model.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class AccessServiceTest {
    @Test
     void adminShouldHaveAllPermissions() {
        AccessService service = new AccessService();
        User admin = service.createUser("admin", Role.ADMIN);

        assertTrue(service.canAccess(admin.getId(), Permission.READ));
        assertTrue(service.canAccess(admin.getId(), Permission.WRITE));
        assertTrue(service.canAccess(admin.getId(), Permission.DELETE));
    }

    @Test
    void guestShouldNotHaveWritePermission() {
        AccessService service = new AccessService();
        User guest = service.createUser("guest", Role.GUEST);

        assertTrue(service.canAccess(guest.getId(), Permission.READ));
        assertFalse(service.canAccess(guest.getId(), Permission.WRITE));
    }
}
