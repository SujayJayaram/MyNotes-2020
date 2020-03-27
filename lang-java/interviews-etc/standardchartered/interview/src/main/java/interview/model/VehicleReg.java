package interview.model;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by sujayjayaram on 13/01/2016.
 *
 * This class serves a number of purposes:
 * - Validation: validating data when you know it's expected format leads to more robust systems!
 *
 * - Using a specific type for a vehicle reg (rather than just a string)
 * means that client constructors are now more type safe/less error prone.
 *
 * - In low latency/high throughput systems, we can implement factory/flyweight
 * patterns here instead of allowing an explosion of string objects to be created.
 *
 */
public class VehicleReg {
    static Logger log = Logger.getLogger(Vehicle.class.getName());

    // see https://gist.github.com/danielrbradley/7567269 (via DVLA site)
    private static final String REGISTRATION_REGEXP = "(^[A-Z][A-Z][0-9][0-9] [A-Z][A-Z][A-Z]$)"
                                                        + "|(^[A-Z][A-Z][0-9][0-9][A-Z][A-Z][A-Z]$)";
    private String regString;

    // If this were a low latency/high throughput environment, we may wish to use
    // a Factory/FlyWeight pattern here instead to avoid repeated creation of objects
    // of the same reg.
    public VehicleReg(String regString) {
        this.regString = regString.toUpperCase(); // Standardise on upper case regs
        checkValidReg();
    }

    private void checkValidReg() {
        log.debug("checkValidReg() called");

        // Match our member data against what we would expect.
        Pattern r = Pattern.compile(REGISTRATION_REGEXP);

        Matcher m = r.matcher(regString);
        if (!m.find( ))
            throw new RuntimeException("Encountered an invalid registration number");
    }

    public String getRegString() {
        return regString;
    }

    public int getAge() {
        log.debug("getAge() called");

        // The year of reg is determined by chars 3 and 4 of the reg.
        // This is given by 'regString.substring(2, 4)'
        // If that number is 50 or greater, we need to subtract 50.
        // We then add 2000 to get the year of registration.
        // This is why we see the 'magic' numbers in the code below.
        // Making these numbers configuarable doesn't make sense as
        // they never change.
        int numpart = Integer.parseInt(regString.substring(2, 4));
        if ( numpart >= 50 )
            numpart = numpart - 50;

        DateTime now = DateTime.now();
        int thisYear = now.getYear();

        return thisYear - (2000 + numpart);
    }
}
