package liftproblem;

import java.util.ArrayList;
import java.util.List;

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
            // SLEEP FOR 5 SECS (*** This relinquishes the object monitor allowing priority requests to halt the prcess if necessary)
            // CLOSE ITS DOORS
            // PROCESS THE NEXT REQUEST
        }
    }
}
