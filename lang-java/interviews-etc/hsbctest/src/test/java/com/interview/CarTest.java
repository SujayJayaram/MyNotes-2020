package com.interview;

import com.interview.impl.BoundedCar;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.*;

/**
 * Created by sujayjayaram on 11/09/2016.
 */
public class CarTest {
    private Car getTestCar() {
        ApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"spring/carTest.xml"});
        return (Car)context.getBean("car");
    }


    @Test
    public void testGetPositionX() throws Exception {

        Car car = getTestCar();
        assertThat(car.getPositionX(), equalTo(1));
        car.turn(new TurnCommand(Car.TurnType.CLOCKWISE, 1));
        car.drive(new DriveCommand(Car.DriveType.FORWARDS, 1));
        assertThat(car.getPositionX(), equalTo(2));
    }

    @Test(expected = MoveException.class)
    public void testGetPositionXException() throws Exception {

        Car car = getTestCar();
        assertThat(car.getPositionY(), equalTo(1));
        car.turn(new TurnCommand(Car.TurnType.CLOCKWISE, 1));
        car.drive(new DriveCommand(Car.DriveType.FORWARDS, 8));

    }

    @Test
    public void testGetPositionY() throws Exception {
        Car car = getTestCar();
        assertThat(car.getPositionY(), equalTo(1));
        car.drive(new DriveCommand(Car.DriveType.FORWARDS, 1));
        assertThat(car.getPositionY(), equalTo(2));
    }

    @Test(expected = MoveException.class)
    public void testGetPositionYException() throws Exception {

        Car car = getTestCar();
        assertThat(car.getPositionY(), equalTo(1));
        car.drive(new DriveCommand(Car.DriveType.FORWARDS, 8));

    }

    @Test
    public void testGetOrientation() throws Exception {

        {
            Car car = getTestCar();
            assertThat(car.getOrientation(), equalTo(Car.Orientation.NORTH));
            car.turn(new TurnCommand(Car.TurnType.CLOCKWISE, 1));
            assertThat(car.getOrientation(), equalTo(Car.Orientation.EAST));
        }

        {
            Car car = getTestCar();
            assertThat(car.getOrientation(), equalTo(Car.Orientation.NORTH));
            car.turn(new TurnCommand(Car.TurnType.CLOCKWISE, 2));
            assertThat(car.getOrientation(), equalTo(Car.Orientation.SOUTH));
        }

        {
            Car car = getTestCar();
            assertThat(car.getOrientation(), equalTo(Car.Orientation.NORTH));
            car.turn(new TurnCommand(Car.TurnType.CLOCKWISE, 3));
            assertThat(car.getOrientation(), equalTo(Car.Orientation.WEST));
        }

        {
            Car car = getTestCar();
            assertThat(car.getOrientation(), equalTo(Car.Orientation.NORTH));
            car.turn(new TurnCommand(Car.TurnType.CLOCKWISE, 3));
            assertThat(car.getOrientation(), equalTo(Car.Orientation.WEST));
        }

        {
            Car car = getTestCar();
            assertThat(car.getOrientation(), equalTo(Car.Orientation.NORTH));
            car.turn(new TurnCommand(Car.TurnType.CLOCKWISE, 4));
            assertThat(car.getOrientation(), equalTo(Car.Orientation.NORTH));
        }
    }
}