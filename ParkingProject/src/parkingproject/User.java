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

// abstract class User that other classes inherit form
public abstract class User {
    private String username;
    private String password;

    //constructor to initialize the attributes
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    //a mrthod that checks the password of the user
    public boolean checkPassword(String password) {
        return this.password.equals(password);
    }

    //a method that shows the starting menu
    public abstract void showMenu(ParkingSystem system, Scanner scanner);
}
