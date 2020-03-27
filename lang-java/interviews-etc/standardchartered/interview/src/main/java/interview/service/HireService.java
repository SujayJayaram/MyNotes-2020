package interview.service;


import interview.dao.DbService;
import interview.model.Car;
import interview.model.Client;
import interview.model.Vehicle;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.sql.Date;
import java.sql.SQLException;


/*
 * Main hire service class
 * Changes:
 * 1. Now uses Vehicle.isHired() method.
 * 2. Removed getDb() method. If you don't want people to use it, remove it!
 * 3. getVehicleDetails() -> getVehicleDetails()
 * - this method returns NULL if the item is not found rather than a default
 * un-persisted object, forcing the caller to think about what is happening.
 *
 * Consider:
 * What is the 'cd' param? It's very unclear to me!
 * If the calling webservice can be changed, I would remove the connection details
 * string from each call and just supply it once in the ctr.
 *
 * This class does not address how the 'DbService db' member is created and initialised.
 * I don't like the way the connection details are sent over as a parameter on each call
 * rather than initalising once.
 */
public class HireService {
    static Logger log = Logger.getLogger(HireService.class.getName());

    private DbService db;
    private String connectionDetails;

    // Add this explicit ctr.
    public HireService(DbService db, String connectionDetails){
        this.db = db;
        this.connectionDetails = connectionDetails;
    }

    //called by rest servlet to make booking
    // *** I don't line the fact that the start date is passed as a string with an unknown format.
    // I don't like the fact that this throws a SQLException - the fact that this DB related should be
    // logged locally and the exception context translated.
    public long hire(String name, String licenseNumber, String reg, String start, int days, double rate) throws Exception {
        log.debug("hire() called for name=" + name +
                    " reg=" + reg +
                    " start=" + start +
                    " days=" + days +
                    " rate=" + rate);

        Client client = getClient(name, licenseNumber);
        Vehicle vehicle = getVehicleDetails(reg);
        if(vehicle == null || vehicle.isHired()) {
            return -1;
        }

        // ***** Don't like this. I would prefer to let the db determine the unique Id using an identity column.
        long hireNumber = System.nanoTime(); //use as unique id

        // Will throw IllegalArgumentException if the date format string is incorrect.
        DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy");
        DateTime dtStart = formatter.parseDateTime(start);

        // **** Again not sure what a hard-coded '1' means for 'state'
        // I don't like this MAGIC NUMBER.
        HireRecord hire = new HireRecord(vehicle, client, dtStart, days, rate, 1, hireNumber);
        client.addHireRecord(hire);
        vehicle.hire(hire);

        // *** I don't like the fact that these two steps are not done atomically
        // Not sure we are given enough info to correct thus.
        db.saveToDatabase(client, connectionDetails);
        db.saveToDatabase(vehicle, connectionDetails);

        return hireNumber;
    }

    public void markReturned(long hireno)  {
        log.debug("markReturned() called for hireno=" + hireno);

        try {
            // Can only make these changes if there is not an existing Prod database with the old persistence model.
            Vehicle vehicle = (Vehicle) db.loadFromDb(connectionDetails, "select * from crs where hrrnm = " + hireno, Vehicle.class);
            vehicle.release();
            db.saveToDatabase(vehicle, connectionDetails);
        } catch (SQLException e) {
            log.error("Could not persist object with hireNumber: " + hireno);

            // Q - do we stop the world here?
            throw new RuntimeException("markReturned encountered a problem persisting this object", e);
        }
    }

    public Vehicle getVehicleDetails(String rg)  {
        log.debug("getClient() called for rg=" + rg);

        try {
            // *** There is a bug here as the string needs escaping!
            // return (Car) db.loadFromDb(cd, "select * from crs where rg = " + rg,Car.class);
            return (Vehicle) db.loadFromDb(connectionDetails, "select * from crs where rg = '" + rg + "'", Vehicle.class);
        } catch (SQLException e) {
            log.warn("getClient() could not find object for rg=" + rg, e);
            return null;
        }
    }

    // I would prefer to remove this.
    public void setDb(DbService db) {
        this.db = db;
    }

    public Client getClient(String name, String licenseNumber) throws Exception {
        log.debug("getClient() called for name=" + name +
                " licenseNumber=" + licenseNumber);

        Client client;
        try {
            // Bug - SQL String needs escaping
            client = (Client) db.loadFromDb(connectionDetails, "select * from clients where clientId = '" + name + "'",Client.class);
        } catch (SQLException e) {
            log.error(e.getMessage());
            log.warn("getClient() could not find object for name=" + name);

            throw new Exception("Could not get details from the DB", e);
        }

        // *** I DO NOT LIKE THIS SIDE EFFECT SO AM COMMENTING IT OUT
        // (I would normally remove this).
        // THE CLIENT SHOULD EXPLICITLY PERSIST NEW OBJECTS.
        /*
        if(client == null) {
            client = new Client(name,licenseNumber);
            try {
                db.saveToDatabase(client, cd);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        */

        return client;
    }
}
