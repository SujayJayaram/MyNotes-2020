package interview.model;


/*
 * Andyr request want to hire bikes too
 *
 * Changes:
 * 1. Improve the above comment to give more details about the request?
 * 2. Bike now extends Vehicle (it was Car!!)
 * 3. cc member made private and accessor method provided.
 * Note the comment about the cc attribute below though.
 *
 * Consider adding toString(), equals() and hashCode() methods as good practice
 * I am not doing this here in order to not clutter the code but these methods
 * are easily auto-generated by the IDE.
 */
public class Bike extends Vehicle {
    // From the interface implied by DbService, I would suspect that this class
    // also needs a default ctr but I would want to double check!

    // This member data is not currently set anywhere so we need to check if it is used.
    // Also, could we model the cc attribute in the Vehicle class??
    // We know that vehicles have a 'cc' attribute too but it may not
    // be as important to people looking to hire a car as a bike.
    private int cc;

    public Bike(VehicleReg reg) {
        super(reg);
    }

    // Not currently used!
    public int getCc() {
        return cc;
    }
}
