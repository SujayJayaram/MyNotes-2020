package com.interview;

/**
 * Created by sujayjayaram on 10/09/2016.
 */
public interface Car {

    // New move types can be added later
    enum TurnType {CLOCKWISE, /* ANTICLOCKWISE */}
    enum DriveType {FORWARDS, /* BACKWARDS */ }

    // I would be tempted to take a simpler approach but if you want
    // to see the use of a 'double dispath' pattern
    // and also allow the processing of a series of commands then
    // this approach is one possibility.
    // Also there are different views around the use of Runtime versus
    // checked exceptions....I have chosen to use a specific exception
    // subclass (MoveException) here.
    void move(MoveCommand[] commands) throws MoveException;

    void drive(DriveCommand driveCommand) throws MoveException;
    void turn(TurnCommand turnCommand) throws MoveException;

    // Int gives us all the type safety we need.
    int getPositionX();
    int getPositionY();

    // Enum used for better type safety
    enum Orientation {NORTH, EAST, SOUTH, WEST};
    Orientation getOrientation();
}
