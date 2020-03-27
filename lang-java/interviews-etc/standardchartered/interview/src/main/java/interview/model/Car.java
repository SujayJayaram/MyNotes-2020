package interview.model;

/**
 * Changes:
 * 1. Class now extends Vehicle and calls super ctr
 * 2. Removed disabled ctr - why confuse clients, old version should be in svn anyway!
 *
 * Consider adding toString(), equals() and hashCode() methods as good practice
 * I am not doing this here in order to not clutter the code but these methods
 * are easily auto-generated by the IDE.
 *
 * Should category be an enum? It feels like it should but I don't know its values or what it is for!
 */
public class Car extends Vehicle {
    // From the interface implied by DbService, I would suspect that this class
    // needs a default ctr but I would want to double check!

    public Car(VehicleReg reg, String make, int category) {
        super(reg, make, category);
    }

    // When/why would this be used
    public Car(VehicleReg reg) {
        super(reg);
    }
}

