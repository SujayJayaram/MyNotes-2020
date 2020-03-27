package liftproblem;

/**
 * Encapsulates opening and closing doors but movement of this lift is controlled
 * by the LiftService
 */
public class Lift {
    private int floor;

    public Lift(int currentFloor) {
        this.floor = currentFloor;
    }

    public void openDoors() {
        // Do nothing if doors are open else open doors
    }

    public void closeDoors() {
        // Do nothing if doors are closed else close doors
    }

    public int getCurrentFloor() {
        return floor;
    }

    // For simplicity, we assume that the move is immediate.
    public void moveToFloor(int floor) {
        this.floor = floor;
    }
}
