/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parkingproject;

/**
 *
 * @author PC
 */
import java.util.Scanner;


public class Admin extends User {
    public Admin(String username, String password) {
        super(username, password);
    }

    @Override
    public void showMenu(ParkingSystem system, Scanner scanner) {
        System.out.println("\n--- Admin Menu ---");
        System.out.println("1. Add Parking Spots");
        System.out.println("2. View All Spots");
        System.out.println("3. Manage Users");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1 -> addParkingSpots(system, scanner);
            case 2 -> viewAllSpots(system);
            case 3 -> manageUsers(system, scanner);
            default -> System.out.println("Invalid choice.");
        }
    }

    //a method to increase the capacity of the parking
    private void addParkingSpots(ParkingSystem system, Scanner scanner) {
        System.out.print("Enter the number of spots to add: ");
        int count = scanner.nextInt();
        int currentSize = system.getParkingSpots().size();

        for (int i = 1; i <= count; i++) {
            system.getParkingSpots().add(new ParkingSpot(currentSize + i, true));
        }
        System.out.println("Added " + count + " parking spots.");
    }

    //a method to retrieve all the spots
    private void viewAllSpots(ParkingSystem system) {
        for (ParkingSpot spot : system.getParkingSpots()) {
            System.out.println(spot);
        }
    }

    private void manageUsers(ParkingSystem system, Scanner scanner) {
        System.out.println("\n--- Manage Users ---");
        System.out.println("1. Add User");
        System.out.println("2. Delete User");
        System.out.println("3. View All Users");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1 -> addUser(system, scanner);
            case 2 -> deleteUser(system, scanner);
            case 3 -> viewAllUsers(system);
            default -> System.out.println("Invalid choice.");
        }
    }

    //a method to add a new user (only avaialble for the admin)
    private void addUser(ParkingSystem system, Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.next();
        System.out.print("Enter password: ");
        String password = scanner.next();
        System.out.print("Enter role (Customer/Operator/Admin): ");
        String role = scanner.next();

        switch (role.toLowerCase()) {
            case "customer" -> system.getUsers().add(new Customer(username, password));
            case "operator" -> system.getUsers().add(new Operator(username, password));
            case "admin" -> system.getUsers().add(new Admin(username, password));
            default -> System.out.println("Invalid role.");
        }
    }

    private void deleteUser(ParkingSystem system, Scanner scanner) {
        System.out.print("Enter username to delete: ");
        String username = scanner.next();
        system.getUsers().removeIf(user -> user.getUsername().equals(username));
        System.out.println("User deleted if existed.");
    }

    private void viewAllUsers(ParkingSystem system) {
        for (User user : system.getUsers()) {
            System.out.println("Username: " + user.getUsername() + ", Role: " + user.getClass().getSimpleName());
        }
    }
}



