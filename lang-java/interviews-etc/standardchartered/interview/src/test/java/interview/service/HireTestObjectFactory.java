package interview.service;

import interview.model.Bike;
import interview.model.Car;
import interview.model.Client;
import interview.model.VehicleReg;

/**
 * Created by sujayjayaram on 14/01/2016.
 */
public class HireTestObjectFactory {

    public static String CAR_REG = "HT07 UBH";
    public static String BIKE_REG = "LV62 RTT";
    public static String CLIENT_NAME = "Fred Smith";
    public static String CLIENT_LICENCE = "UKQW6757";

    public static Car createCar() {
        return new Car(new VehicleReg(CAR_REG));
    }

    public static Client createClient() {
        return new Client(CLIENT_NAME, CLIENT_LICENCE);
    }

    public static Bike createBike() {
        return new Bike(new VehicleReg(BIKE_REG));
    }
}
