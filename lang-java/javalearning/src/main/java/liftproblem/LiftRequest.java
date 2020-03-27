package liftproblem;

/**
 * Created by sujayjayaram on 09/03/2016.
 */
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
