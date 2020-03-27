package com.interview;

/**
 * Created by sujayjayaram on 12/09/2016.
 */
import com.interview.impl.BoundedCar;
import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class DriveCarStepDefinitions {
    private Car car;
    private int initalX;
    private int initalY;
    private boolean lastDriveCausedException = false;

    @Given("^the Car is in position X = (\\d+) and Y = (\\d+) and facing (\\S+)$")
    public void the_Car_is_in_position_X_and_Y_and_facing_Direction(int x, int y, String direction) throws Throwable {
        initalX = x;
        initalY = y;
        car = new BoundedCar(initalX, initalY, Car.Orientation.valueOf(direction), 100, 100);
    }

    @When("^the Car turns (\\S+)$")
    public void the_Car_turns(String direction) throws Throwable {
        Car.TurnType turnType = Car.TurnType.valueOf(direction);
        TurnCommand command = new TurnCommand(turnType, 1);
        car.turn(command);
    }

    @Then("^the Car is still in the same position$")
    public void the_Car_is_still_in_the_same_position() throws Throwable {
        assertThat(car.getPositionX(), is(initalX));
        assertThat(car.getPositionY(), is(initalY));
    }

    @Then("^the Car is facing (\\S+)$")
    public void is_now_facing(String direction) throws Throwable {
        assertThat(car.getOrientation(), is(Car.Orientation.valueOf(direction)));
    }

    @When("^the Car drives (\\S+) (\\d+)$")
    public void the_Car_drives_direction_units(String direction, int units) throws Throwable {
        lastDriveCausedException = false;
        try {
            Car.DriveType driveType = Car.DriveType.valueOf(direction);
            DriveCommand command = new DriveCommand(driveType, units);
            car.drive(command);
        }
        catch(Exception ex) {
            lastDriveCausedException = true;
        }
    }

    @Then("^is in position X = (\\d+) and Y = (\\d+)$")
    public void is_in_position_X_and_Y(int x, int y) throws Throwable {
        assertThat(car.getPositionX(), is(x));
        assertThat(car.getPositionY(), is(y));
    }

    @Then("^an exception is thrown$")
    public void an_exception_is_thrown() throws Throwable {
        assertThat(lastDriveCausedException, is(true));
    }

}
