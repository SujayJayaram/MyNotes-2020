package liftproblem;

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
