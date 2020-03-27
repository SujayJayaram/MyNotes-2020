package com.interview.impl;

import com.interview.*;
import org.apache.log4j.Logger;

/**
 * Created by sujayjayaram on 10/09/2016.
 */
public class BoundedCar implements Car {

    static Logger log = Logger.getLogger(BoundedCar.class);

    private int positionX;
    private int positionY;
    private Orientation orientation;

    // From Car Park dimensions
    private int maxPosX;
    private int maxPosY;

    public BoundedCar(int positionX, int positionY, Orientation orientation, int maxPosX, int maxPosY) {
        this.positionX = positionX;
        this.positionY = positionY;
        this.orientation = orientation;
        this.maxPosX = maxPosX;
        this.maxPosY = maxPosY;
    }

    @Override
    public void move(MoveCommand[] commands) throws MoveException {
        log.debug("move() called for " + commands.length + " commands");

        for(MoveCommand command : commands)
            command.moveCar(this);

    }

    @Override
    public void drive(DriveCommand driveCommand) throws MoveException {
        log.debug("drive() called");

        DriveType type = driveCommand.getType();
        switch(type) {

            case FORWARDS: {
                int units = driveCommand.getUnits();
                switch (orientation) {

                    case NORTH: {
                        positionY += units;
                        break;
                    }
                    case EAST: {
                        positionX += units;
                        break;
                    }
                    case SOUTH: {
                        positionY -= units;
                        break;
                    }
                    case WEST: {
                        positionX -= units;
                        break;
                    }
                }

                break;
            }

            default:
                // Ensure we fail when new types are added but not handled here.
                throw new IllegalArgumentException("Not yet implemented");
        }

        validatePositionInCarPark();
    }

    private void validatePositionInCarPark() throws MoveException {

        // Only validate if constraints are set

        if ( maxPosX > 1 ) {
            if ( (positionX < 1) || (positionX > maxPosX) )
                throw new MoveException("Cannot move beyound the current limit constaints");
        }

        if ( maxPosY > 1 ) {
            if ( (positionY < 1) || (positionY > maxPosY) )
                throw new MoveException("Cannot move beyound the current limit constaints");
        }
    }

    @Override
    public void turn(TurnCommand turnCommand) throws MoveException {
        log.debug("turn() called");

        TurnType type = turnCommand.getType();
        switch(type) {

            case CLOCKWISE: {
                int units = turnCommand.getUnits();
                while (units-- > 0) {
                    if ( orientation == Orientation.NORTH )
                        orientation = Orientation.EAST;
                    else if ( orientation == Orientation.EAST )
                        orientation = Orientation.SOUTH;
                    else if ( orientation == Orientation.SOUTH )
                        orientation = Orientation.WEST;
                    else
                        orientation = Orientation.NORTH;
                }
                break;
            }

            default:
                // Ensure we fail when new types are added but not handled here.
                throw new IllegalArgumentException("Not yet implemented");
        }
    }

    @Override
    public int getPositionX() {
        return positionX;
    }

    @Override
    public int getPositionY() {
        return positionY;
    }

    @Override
    public Orientation getOrientation() {
        return orientation;
    }
}
