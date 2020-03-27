package com.interview;

/**
 * Created by sujayjayaram on 10/09/2016.
 */
public class DriveCommand implements MoveCommand {

    private Car.DriveType type;
    private int units;

    public DriveCommand(Car.DriveType type, int units) {
        this.type = type;
        this.units = units;
    }

    public Car.DriveType getType() {
        return type;
    }

    public int getUnits() {
        return units;
    }

    @Override
    public void moveCar(Car car) throws MoveException {
        car.drive(this);
    }
}
