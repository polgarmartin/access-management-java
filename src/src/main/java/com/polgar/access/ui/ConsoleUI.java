package com.polgar.access.ui;

import com.polgar.access.service.AccessService;
import com.polgar.access.model.Permission;
import com.polgar.access.model.Role;
import com.polgar.access.model.User;

import java.util.List;
import java.util.Scanner;

public class ConsoleUI {
    private final AccessService accessService = new AccessService();
    private final Scanner scanner = new Scanner(System.in);

    public void start() {
        System.out.println("=== Access Management System ===");

        boolean running = true;
        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> createUserFlow();
                case "2" -> listUsersFlow();
                case "3" -> changeRoleFlow();
                case "4" -> checkAccessFlow();
                case "0" -> running = false;
                default -> System.out.println("Invalid choice. Try again.");
            }
        }

        System.out.println("Bye!");
    }

    private void printMenu() {
        System.out.println();
        System.out.println("1) Create user");
        System.out.println("2) List users");
        System.out.println("3) Change user role");
        System.out.println("4) Check permission");
        System.out.println("0) Exit");
        System.out.print("Choose: ");
    }

    private void createUserFlow() {
        try {
            System.out.print("Username: ");
            String username = scanner.nextLine();

            System.out.print("Role (ADMIN/USER/GUEST): ");
            Role role = Role.valueOf(scanner.nextLine().trim().toUpperCase());

            User user = accessService.createUser(username, role);
            System.out.println("Created: " + user);
        } catch (Exception e) {
            System.out.println("Could not create user: " + e.getMessage());
        }
    }

    private void listUsersFlow() {
        List<User> users = accessService.getAllUsers();
        if (users.isEmpty()) {
            System.out.println("No users yet.");
            return;
        }
        users.forEach(System.out::println);
    }

    private void changeRoleFlow() {
        try {
            System.out.print("User id: ");
            long id = Long.parseLong(scanner.nextLine().trim());

            System.out.print("New role (ADMIN/USER/GUEST): ");
            Role role = Role.valueOf(scanner.nextLine().trim().toUpperCase());

            boolean ok = accessService.changeRole(id, role);
            System.out.println(ok ? "Role updated." : "User not found.");
        } catch (Exception e) {
            System.out.println("Could not change role: " + e.getMessage());
        }
    }

    private void checkAccessFlow() {
        try {
            System.out.print("User id: ");
            long id = Long.parseLong(scanner.nextLine().trim());

            System.out.print("Permission (READ/WRITE/DELETE): ");
            Permission perm = Permission.valueOf(scanner.nextLine().trim().toUpperCase());

            boolean allowed = accessService.canAccess(id, perm);
            System.out.println(allowed ? "ALLOWED" : "DENIED");
        } catch (Exception e) {
            System.out.println("Could not check access: " + e.getMessage());
        }
    }
}
