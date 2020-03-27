package com.interview;

/**
 * Created by sujayjayaram on 10/09/2016.
 *
 * Interface which allows us to specify complex moves as a series of drives and turns.
 */
public interface MoveCommand {
    void moveCar(Car car) throws MoveException;
}
