package interview.service;

import interview.dao.DbService;
import interview.model.Bike;
import interview.model.Car;
import interview.model.Client;
import interview.model.Vehicle;
import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;

import java.sql.SQLException;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 * Created by sujayjayaram on 14/01/2016.
 */
public class HireServiceTest {
    static Logger log = Logger.getLogger(HireServiceTest.class.getName());

    private static final String CONNECTION_DETAILS = "XXX";
    private static final long HIRE_NUMBER = 99L;
    private static final String MARK_RETURNED_SQL = "select * from crs where hrrnm = " + HIRE_NUMBER;
    private static final String GET_VEHICLE_DETAILS_SQL = "select * from crs where rg = '" + HireTestObjectFactory.CAR_REG + "'";
    private static final String GET_CLIENT_SQL = "select * from clients where clientId = '" + HireTestObjectFactory.CLIENT_NAME + "'";

    private static HireService hireService;

    @BeforeClass
    public static void init() throws Exception {
        log.debug("init() called");

        DbService mockedService = mock(DbService.class);

        when(mockedService.loadFromDb(CONNECTION_DETAILS, MARK_RETURNED_SQL, Vehicle.class)
        ).thenReturn(HireTestObjectFactory.createCar());

        when(mockedService.loadFromDb(CONNECTION_DETAILS, GET_VEHICLE_DETAILS_SQL, Vehicle.class)
        ).thenReturn(HireTestObjectFactory.createCar());

        when(mockedService.loadFromDb(CONNECTION_DETAILS, GET_CLIENT_SQL, Client.class)
        ).thenReturn(HireTestObjectFactory.createClient());

        hireService = new HireService(mockedService, CONNECTION_DETAILS);
    }

    @Test
    public void testGetVehicleDetails() throws Exception {
        log.debug("testGetVehicleDetails() called");

        Vehicle vehicleDetails = hireService.getVehicleDetails(HireTestObjectFactory.CAR_REG);
        assertThat("Vehicle Details", vehicleDetails.getReg(), equalTo(HireTestObjectFactory.CAR_REG));
    }

    @Test
    public void testGetClient() throws Exception {
        log.debug("testGetClient() called");

        Client client = hireService.getClient(HireTestObjectFactory.CLIENT_NAME, HireTestObjectFactory.CLIENT_LICENCE);
        assertThat("Get Client Name", client.getName(), equalTo(HireTestObjectFactory.CLIENT_NAME));
        assertThat("Get Client Licence", client.getLicenseNumber(), equalTo(HireTestObjectFactory.CLIENT_LICENCE));
    }

    @Test
    public void testHireGoodDate() throws Exception {
        log.debug("testHireGoodDate() called");

        long hireNum = hireService.hire(HireTestObjectFactory.CLIENT_NAME,
                HireTestObjectFactory.CLIENT_LICENCE,
                HireTestObjectFactory.CAR_REG, "02/02/2016", 10, 10.0);

    }

    @Test(expected = IllegalArgumentException.class)
    public void testHireBadDate() throws Exception {
        log.debug("testHireBadDate() called");

        long hireNum = hireService.hire(HireTestObjectFactory.CLIENT_NAME,
                HireTestObjectFactory.CLIENT_LICENCE,
                HireTestObjectFactory.CAR_REG, "92/02/2016", 10, 10.0);

    }

    @Test
    public void testMarkReturned() throws Exception {
        log.debug("testMarkReturned() called");

        hireService.markReturned(HIRE_NUMBER);
    }
}