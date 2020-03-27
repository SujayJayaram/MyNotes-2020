
Here are 5 suggested sprint deliveries:
---------------------------------------

SPRINT 1. User can call lift (via FloorBasedButtonPadController)
Success Criteria:
- if the lift is at that floor, with doors open, it does nothing.
- if lift is at that floor with doors closed, it opens its doors.
- lift closes its doors if they are open, moves to the floor where the user is based and opens its doors.

SPRINT 2. Lift responds to user hitting alarm (located inside lift, via LiftBasedButtonPadController)
Success Criteria:
- lift closes its doors if open, goes to the ground floor and opens its doors

SPRINT 3. Lift responds to user requesting to go to a specific floor once inside the lift (via LiftBasedButtonPadController)
Success Criteria:
- lift closes its doors if open, goes to the requested floor and opens its doors

SPRINT 4. Lift responds to multiple user requests
Success Criteria:
- two users are able to get into the lift and request different floors.
- the lift services the 'closest floor' request then the second request.

SPRINT 5. LiftService is extended to cope with multiple lifts (so far we have only used one lift)
....

CODE FOLLOWS:

/***********************************************************************************/

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

/***********************************************************************************/

public class LiftRequest {
    private final int requestedFloor;

    // Used to signal a fire alarm etc
    private final boolean priorityRequest;

    public LiftRequest(int requestedFloor, boolean priorityRequest) {
        this.requestedFloor = requestedFloor;
        this.priorityRequest = priorityRequest;
    }

    // Could be a fire alarm or a request from VIPs etc.
    public boolean isPriorityRequest() {
        return priorityRequest;
    }

    public int getRequestedFloor() {
        return requestedFloor;
    }
}

/***********************************************************************************/

/**
 * Situated outside the lift on a specific floor
 * One instance per floor
 */
public class FloorBasedButtonPadController {
    private final LiftService liftService;
    private final int locatedFloor; // The floor where this button unit is situated

    public FloorBasedButtonPadController(LiftService liftService, int locatedFloor) {
        this.liftService = liftService;
        this.locatedFloor = locatedFloor;
    }

    public void callLift() {
        LiftRequest request = new LiftRequest(locatedFloor, false);
        liftService.processRequest(request);
    }
}

/***********************************************************************************/

/**
 * Situated inside the lift
 */
public class LiftBasedButtonPadController {
    private final LiftService liftService;

    public LiftBasedButtonPadController(LiftService liftService) {
        this.liftService = liftService;
    }

    public void requestFloor(int floor) {
        LiftRequest request = new LiftRequest(floor, false);
        liftService.processRequest(request);
    }

    public void signalFireAlarm() {
        LiftRequest request = new LiftRequest(0, true);
        liftService.processRequest(request);
    }
}

/***********************************************************************************/

/**
 * Handles requests to move the lift and controls the lift itself
 */
public class LiftService {

    private final Thread serviceThread;

    private boolean stopService; /* default = false */

    enum LiftDirection {NONE, UP, DOWN};
    private LiftDirection liftDirection = LiftDirection.NONE;
    private final Lift lift;

    private List<LiftRequest> upRequests = new ArrayList<>();
    private List<LiftRequest> downRequests = new ArrayList<>();

    public LiftService(Lift lift) {
        this.lift = lift;

        serviceThread = new Thread(() -> processRequests());
        serviceThread.start();
    }

    public void processRequest(LiftRequest request) {
        // Handle emergency as a priority
        if ( request.isPriorityRequest() ) {
            synchronized(this) {
                stopService = true;
                lift.closeDoors();
                lift.moveToFloor(request.getRequestedFloor());
                return;
            }
        }

        if ( request.getRequestedFloor() == lift.getCurrentFloor() )
            return; /* Actually should check if doors are open */


        if ( request.getRequestedFloor() >= lift.getCurrentFloor() )
            upRequests.add(request);
        else
            downRequests.add(request);

    }

    private synchronized void processRequests() {
        while (!stopService) {
            // .. process the lift requests:
            // If we are travelling up, then process all the upRequests first,
            // then move onto the down requests (and vice versa)

            // MAKE THE LIFT GO TO EACH REQUESTED FLOOR
            // OPEN ITS DOORS
            // SLEEP FOR 5 SECS (*** This relinquishes the object monitor allowing priority requests to halt the process if necessary)
            // CLOSE ITS DOORS
            // PROCESS THE NEXT REQUEST
        }
    }
}