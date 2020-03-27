package liftproblem;

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
