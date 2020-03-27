package interview.model;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

/**
 * Created by sujayjayaram on 13/01/2016.
 */
public class VehicleRegTest {

    static Logger log = Logger.getLogger(VehicleRegTest.class.getName());

    @Test
    public void testRegChars() throws Exception {
        log.debug("testRegChars() called");

        // Current Style
        VehicleReg reg1 = new VehicleReg("LV12 UXJ");
        VehicleReg reg2 = new VehicleReg("LV12UXJ");
    }

    @Test(expected=RuntimeException.class)
    public void testIncorrectRegChars() throws Exception {
        log.debug("testIncorrectRegChars() called");

        // Old Style - Should throw exception
        VehicleReg reg3 = new VehicleReg("H865 GYP");
    }

    @Test
    public void testAge() throws Exception {
        log.debug("testAge() called");

        // Registered in 2012
        VehicleReg reg1 = new VehicleReg("LV12 UXJ");
        int age = reg1.getAge();

        DateTime now = DateTime.now();
        int thisYear = now.getYear();

        assertThat("Test age", age, equalTo(thisYear - 2012)); // Registered in 2012
    }
}