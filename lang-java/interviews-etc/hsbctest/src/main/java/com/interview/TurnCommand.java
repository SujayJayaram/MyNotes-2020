package com.interview;

/**
 * Created by sujayjayaram on 10/09/2016.
 */
public class TurnCommand implements MoveCommand {

    private Car.TurnType type;
    private int units;

    public TurnCommand(Car.TurnType type, int units) {
        this.type = type;
        this.units = units;
    }

    public Car.TurnType getType() {
        return type;
    }

    public int getUnits() {
        return units;
    }

    @Override
    public void moveCar(Car car) throws MoveException{
        car.turn(this);
    }
}
