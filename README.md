# Access Management System (Java)

Simple Java-based application demonstrating role-based access control and clean service-layer design.

## Features

- User creation with assigned roles (ADMIN, USER, GUEST)
- Role-based permission handling (READ, WRITE, DELETE)
- Centralized access rules implemented in the service layer
- Input validation and basic error handling
- Unit tests for access control logic using JUnit 5

## Project Structure
├─ main
│ └─ java
│ └─ com.polgar.access
│ ├─ app // Application entry point
│ ├─ model // User, Role, Permission
│ ├─ service // Access control business logic
│ └─ ui // Console interaction
└─ test
└─ java
└─ com.polgar.access.service
└─ AccessServiceTest

## Technologies Used

- Java
- JUnit 5
- Enums for roles and permissions
- Java Collections
- Git & GitHub for version control

## Unit Tests

The project includes unit tests verifying:
- Full access for ADMIN users
- Restricted access for USER and GUEST roles
- Proper handling of invalid user access

Tests are implemented using **JUnit 5** and focus on the service layer.

## How to Run

1. Clone the repository
2. Open the project in IntelliJ IDEA
3. Run the `Main` class
4. Use the console menu to interact with the application

## Project Status

This project is under active development and will be continuously improved with additional features and refinements.