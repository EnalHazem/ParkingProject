/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Seif Asharf
 */
import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ParkingSystemGUI {
    private ParkingSystem system;

    public ParkingSystemGUI() {
        this.system = new ParkingSystem();
        showLoginScreen();
    }

    private void showLoginScreen() {
        JFrame loginFrame = new JFrame("Parking System - Login");
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setSize(300, 200);
        loginFrame.setLayout(new GridLayout(3, 2));

        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();
        JButton loginButton = new JButton("Login");

        loginFrame.add(usernameLabel);
        loginFrame.add(usernameField);
        loginFrame.add(passwordLabel);
        loginFrame.add(passwordField);
        loginFrame.add(new JLabel());
        loginFrame.add(loginButton);

        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            User user = system.authenticate(username, password);
            if (user != null) {
                loginFrame.dispose();
                showMenuForUser(user);
            } else {
                JOptionPane.showMessageDialog(loginFrame, "Invalid credentials!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        loginFrame.setLocationRelativeTo(null);
        loginFrame.setVisible(true);
    }

    private void showMenuForUser(User user) {
        if (user instanceof Admin) {
            showAdminMenu((Admin) user);
        } else if (user instanceof Operator) {
            showOperatorMenu((Operator) user);
        } else if (user instanceof Customer) {
            showCustomerMenu((Customer) user);
        }
    }

    private void showAdminMenu(Admin admin) {
        JFrame adminFrame = new JFrame("Admin Menu");
        adminFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        adminFrame.setSize(400, 300);
        adminFrame.setLayout(new GridLayout(6, 1));

        JButton addSpotsButton = new JButton("Add Parking Spots");
        JButton viewSpotsButton = new JButton("View All Spots");
        JButton viewParkedSpotsButton = new JButton("View Parked Spots");
        JButton viewShiftReportsButton = new JButton("View Shift Reports");
        JButton manageUsersButton = new JButton("Manage Users");
        JButton logoutButton = new JButton("Logout");

        adminFrame.add(addSpotsButton);
        adminFrame.add(viewSpotsButton);
        adminFrame.add(viewParkedSpotsButton);
        adminFrame.add(viewShiftReportsButton);
        adminFrame.add(manageUsersButton);
        adminFrame.add(logoutButton);

        addSpotsButton.addActionListener(e -> addParkingSpots());
        viewSpotsButton.addActionListener(e -> viewAllSpots());
        viewParkedSpotsButton.addActionListener(e -> viewParkedSpots(admin));
//        viewShiftReportsButton.addActionListener(e -> viewShiftReports(admin));
        manageUsersButton.addActionListener(e -> manageUsers());
        logoutButton.addActionListener(e -> {
            adminFrame.dispose();
            showLoginScreen();
        });

        adminFrame.setLocationRelativeTo(null);
        adminFrame.setVisible(true);
    }

    private void showOperatorMenu(Operator operator) {
        JFrame operatorFrame = new JFrame("Operator Menu");
        operatorFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        operatorFrame.setSize(300, 300);
        operatorFrame.setLayout(new GridLayout(3, 1));

        JButton viewFreeSpotsButton = new JButton("View Free Spots");
        JButton calculateHoursButton = new JButton("Calculate Parking Hours");
        JButton logoutButton = new JButton("Logout");

        operatorFrame.add(viewFreeSpotsButton);
        operatorFrame.add(calculateHoursButton);
        operatorFrame.add(logoutButton);

        viewFreeSpotsButton.addActionListener(e -> viewFreeSpots());
        calculateHoursButton.addActionListener(e -> calculateParkingHours(operator));
        logoutButton.addActionListener(e -> {
            operatorFrame.dispose();
            showLoginScreen();
        });

        operatorFrame.setLocationRelativeTo(null);
        operatorFrame.setVisible(true);
    }

    private void showCustomerMenu(Customer customer) {
        JFrame customerFrame = new JFrame("Customer Menu");
        customerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        customerFrame.setSize(300, 200);
        customerFrame.setLayout(new GridLayout(3, 1));

        JButton printTicketButton = new JButton("Print Entry Ticket");
        JButton payParkingButton = new JButton("Pay for Parking");
        JButton logoutButton = new JButton("Logout");

        customerFrame.add(printTicketButton);
        customerFrame.add(payParkingButton);
        customerFrame.add(logoutButton);

        printTicketButton.addActionListener(e -> printEntryTicket());
        payParkingButton.addActionListener(e -> payForParking());
        logoutButton.addActionListener(e -> {
            customerFrame.dispose();
            showLoginScreen();
        });

        customerFrame.setLocationRelativeTo(null);
        customerFrame.setVisible(true);
    }
// admin
    private void addParkingSpots() {
        String input = JOptionPane.showInputDialog("Enter the number of spots to add:");
        if (input != null && !input.isEmpty()) {
            try {
                int count = Integer.parseInt(input);
                for (int i = 0; i < count; i++) {
                    system.getParkingSpots().add(new ParkingSpot(system.getParkingSpots().size() + 1, true));
                }
                JOptionPane.showMessageDialog(null, "Added " + count + " parking spots.");
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid number.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void viewAllSpots() {
        StringBuilder spotsInfo = new StringBuilder();
        for (ParkingSpot spot : system.getParkingSpots()) {
            spotsInfo.append(spot).append("\n");
        }
        JOptionPane.showMessageDialog(null, spotsInfo.toString(), "All Parking Spots", JOptionPane.INFORMATION_MESSAGE);
    }

    private void viewParkedSpots(Admin admin) {
       List<ParkingSpot> parkedSpots = admin.viewParkedSpots(system);
    StringBuilder spotsInfo = new StringBuilder("Parked Spots:\n");
    for (ParkingSpot spot : parkedSpots) {
        spotsInfo.append(spot).append("\n");
    }
    JOptionPane.showMessageDialog(null, spotsInfo.toString(), "Parked Spots", JOptionPane.INFORMATION_MESSAGE);
}

//private void viewShiftReports(Admin admin) {
//    String report = admin.getShiftReport(); // Ensure method name matches in Admin class
//    JOptionPane.showMessageDialog(null, report, "Shift Reports", JOptionPane.INFORMATION_MESSAGE);
//}

    private void manageUsers() {
        JOptionPane.showMessageDialog(null, "User management is under development.", "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    private void viewFreeSpots() {
        StringBuilder freeSpotsInfo = new StringBuilder();
        for (ParkingSpot spot : system.getParkingSpots()) {
            if (spot.isFree()) {
                freeSpotsInfo.append(spot).append("\n");
            }
        }
        JOptionPane.showMessageDialog(null, freeSpotsInfo.toString(), "Free Parking Spots", JOptionPane.INFORMATION_MESSAGE);
    }
// opp
    private void calculateParkingHours(Operator operator) {
        String ticketIdInput = JOptionPane.showInputDialog("Enter Ticket ID:");
        if (ticketIdInput != null && !ticketIdInput.isEmpty()) {
            try {
                int ticketId = Integer.parseInt(ticketIdInput);
                double hours = operator.calculateParkingHours(ticketId);
                JOptionPane.showMessageDialog(null, "Total Hours: " + hours, "Parking Hours", JOptionPane.INFORMATION_MESSAGE);
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid Ticket ID.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void printEntryTicket() {
    // Get plate number from user input
    String plateNumber = JOptionPane.showInputDialog(null, "Enter your plate number:", "Enter Plate Number", JOptionPane.PLAIN_MESSAGE);

    if (plateNumber == null || plateNumber.trim().isEmpty()) {
        JOptionPane.showMessageDialog(null, "Plate number cannot be empty.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

    int ticketId = system.getNextTicketId();

    for (ParkingSpot spot : system.getParkingSpots()) {
        if (spot.isFree()) {
            spot.setFree(false);
            Ticket ticket = new Ticket(ticketId, plateNumber, spot.getId());
            system.getTickets().put(ticketId, ticket);

            // Display ticket details
            JOptionPane.showMessageDialog(
                null,
                "Ticket Generated:\n" + ticket,
                "Ticket Details",
                JOptionPane.INFORMATION_MESSAGE
            );
            return;
        }
    }

    // If no spots are available
    JOptionPane.showMessageDialog(null, "No available spots.", "Info", JOptionPane.WARNING_MESSAGE);
}


    private void payForParking() {
        JOptionPane.showMessageDialog(null, "Parking payment is under development.", "Info", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(ParkingSystemGUI::new);
    }
}

