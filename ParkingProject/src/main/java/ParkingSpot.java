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

    @Override
    public String toString() {
        return "Spot ID: " + id + ", Free: " + isFree;
    }
}

