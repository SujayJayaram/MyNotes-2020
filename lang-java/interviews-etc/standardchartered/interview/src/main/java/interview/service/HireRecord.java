package interview.service;


import interview.model.Bike;
import interview.model.Client;
import interview.model.Vehicle;

import interview.util.InterviewConfig;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;

/**
 * Changes:
 * 1. Car -> Vehicle
 * 2. hireno -> hireNumber (Consistent with elsewhere)
 * 3. Removed set methods and replaced with a new ctr
 * - its always good to work with immutable objects if possible.
 * 4. Ctr now takes a Client object rather than the clientName string
 * 5. Use Joda DateTime
 *
 * Consider:
 * Not sure what the 'state' member is and if its required here? It only ever seems to have the value '1'.
 * The fact that we have to think about what this means shows this could be improved upon.
 */
public class HireRecord {
    static Logger log = Logger.getLogger(HireRecord.class.getName());

    private Vehicle vehicle;
    private Client client;
    private DateTime startDate;
    private int days;
    private double rate;
    private int state;
    private long hireNumber;

    // From the interface implied by DbService, I would suspect that this class
    // needs a default ctr but I would want to ask!
    // If it does, it will need setters for the member data too.

    public HireRecord(Vehicle vehicle, Client client, DateTime startDate, int days, double rate, int state, long hireNum) {
        this.vehicle = vehicle;
        this.client = client;
        this.startDate = startDate;
        this.days = days;
        this.rate = rate;
        this.state = state;
        this.hireNumber = hireNum;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public Client getClient() {
        return client;
    }

    public DateTime getStartDate() {
        return startDate;
    }

    public int getDays() {
        return days;
    }

    public int getState() {
        return state;
    }

    public long getHireNumber() {
        return hireNumber;
    }

    public double getRate() {
        if(vehicle instanceof Bike)
            return rate;  //no discount for bikes

        // This is a bug. Successive calling could keep diminishing the returned rate!
        // So I wrote a test that failed first, then fixed the bug!
        // return this.rate = vehicle.getAge() > 3 ? rate * 0.9 : rate;  //discount for older cars

        int ageCutOff = InterviewConfig.INSTANCE.getInt("age.cutoff");
        double discountRate = InterviewConfig.INSTANCE.getDouble("discount.rate");

        double rv = (vehicle.getAge() > ageCutOff) ? rate * discountRate : rate;  //discount for older cars
        log.debug("getRate() returns " + rv);
        return rv;

    }
}
