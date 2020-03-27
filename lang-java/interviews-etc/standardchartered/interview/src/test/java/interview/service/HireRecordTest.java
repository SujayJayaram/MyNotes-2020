package interview.service;

import interview.model.*;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

/**
 * Created by sujayjayaram on 14/01/2016.
 */
public class HireRecordTest {

    static Logger log = Logger.getLogger(HireRecordTest.class.getName());

    @Test
    public void testGetRateForBike() throws Exception {
        log.debug("testGetRateForBike() called");

        Bike bike = HireTestObjectFactory.createBike();
        Client client = HireTestObjectFactory.createClient();
        HireRecord hireRecord = new HireRecord(bike, client, DateTime.now(), 3, 10.0 /* rate */, 1, 100L);

        double rate = hireRecord.getRate();
        assertThat(rate, equalTo(10.0));
    }

    @Test
    public void testGetRateForCar() throws Exception {
        log.debug("testGetRateForCar() called");

        Car car = HireTestObjectFactory.createCar();
        Client client = HireTestObjectFactory.createClient();
        HireRecord hireRecord = new HireRecord(car, client, DateTime.now(), 3, 10.0 /* rate */, 1, 100L);

        double rate1 = hireRecord.getRate();
        assertThat("Rate Test First Time", rate1, equalTo(9.0)); // get 10% discount for cars

        // I suspected that the old code would not return the same rate if called twice so test again.
        double rate2 = hireRecord.getRate();
        assertThat("Rate Test Second Time", rate2, equalTo(9.0)); // get 10% discount for cars
    }
}