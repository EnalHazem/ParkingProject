/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parkingproject;

import java.util.Scanner;

/**
 *
 * @author PC
 */
import java.util.Scanner;

public class Operator extends User {
    public Operator(String username, String password) {
        super(username, password);
    }

    @Override
    public void showMenu(ParkingSystem system, Scanner scanner) {
        System.out.println("\n--- Operator Menu ---");
        System.out.println("1. View Free Spots");
        System.out.print("Enter your choice: ");
        int choice = scanner.nextInt();

        if (choice == 1) {
            viewFreeSpots(system);
        } else {
            System.out.println("Invalid choice.");
        }
    }

    private void viewFreeSpots(ParkingSystem system) {
        System.out.println("Free Spots:");
        for (ParkingSpot spot : system.getParkingSpots()) {
            if (spot.isFree()) {
                System.out.println("Spot ID: " + spot.getId());
            }
        }
    }
}



