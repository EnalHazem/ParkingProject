/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package parkingproject;

/**
 *
 * @author PC
 */

public class ParkingSpot {
    private int id;
    private boolean isFree;

    public ParkingSpot(int id, boolean isFree) {
        this.id = id;
        this.isFree = isFree;
    }

    public int getId() {
        return id;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean free) {
        isFree = free;
    }

    //a method to book a spot
    public void occupy() {
        setFree(false);
    }

    // a method to free a spot if the user leaves it
    public void vacate() {
        setFree(true);
    }

    @Override
    public String toString() {
        return "Spot ID: " + id + ", Free: " + isFree;
    }
}


