package cars;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertSame;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.awt.Color;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;


public class TestSaab95 {

    @Test
    public void newCarStartsAtOrigo() {
        Saab95 saab95 = new Saab95();
        assertEquals(saab95.getX(), 0.0, Constants.DOUBLE_EQUALS_ALLOWED_DIFF);
        assertEquals(saab95.getY(), 0.0, Constants.DOUBLE_EQUALS_ALLOWED_DIFF);
    }

    @Test
    public void newCarFacesEast() {
        Saab95 saab95 = new Saab95();
        assertSame(Direction.EAST, saab95.getDirection());
    }

    @Test
    public void newCarIsRed() {
        Saab95 saab95 = new Saab95();
        assertSame(Color.RED, saab95.getColor());
    }

    @Test
    public void repaintingToNewColorUpdatesColor() {
        Saab95 saab95 = new Saab95();
        assertTrue(saab95.getColor().equals(Color.RED));
        saab95.setColor(Color.BLACK);
        assertSame(saab95.getColor(), Color.BLACK);
    }

    @Test
    public void carFacingEastFacesSouthAfterTurnRight() {
        Saab95 saab95 = new Saab95();

        assertTrue(saab95.getDirection().equals(Direction.EAST));

        saab95.turnRight();
        assertEquals(saab95.getDirection(), Direction.SOUTH);
    }

    @Test
    public void carFacingEastFacesNorthAfterTurnLeft() {
        Saab95 saab95 = new Saab95();

        assertTrue(saab95.getDirection().equals(Direction.EAST));

        saab95.turnLeft();
        assertEquals(saab95.getDirection(), Direction.NORTH);
    }

    @ParameterizedTest
    @ValueSource(doubles = { 1e-3, 1e-2, 0.1, 0.5, 0.9, 0.99, 0.999, 1.0})
    public void moveEastIncreasesX(double gasAmount) {
        Saab95 saab95 = new Saab95();

        assertEquals(0, saab95.getCurrentSpeed(), Constants.DOUBLE_EQUALS_ALLOWED_DIFF);
        assertTrue(saab95.getDirection().equals(Direction.EAST));

        saab95.gas(gasAmount);
        saab95.move();
        assertTrue(saab95.getX() > 0);
    }

    // Sanity check 1: gas & break only accept values in [0,1]

    @ParameterizedTest
    @ValueSource(doubles = { -10, -5, -1, -0.5, -0.1, -1e-2, -1e-3, -1e-5 })
    public void gasThrowsIllegalArgExceptionIfArgBelow0(double gasAmount) {
        Saab95 saab95 = new Saab95();
        assertThrows(IllegalArgumentException.class, () -> {
            saab95.gas(gasAmount);
        });
    }

    @ParameterizedTest
    @ValueSource(doubles = { 10, 5, 2, 1.5, 1.1, 1 + 1e-1, 1 + 1e-2, 1 + 1e-5 })
    public void gasThrowsIllegalArgExceptionIfArgAbove1(double gasAmount) {
        Saab95 saab95 = new Saab95();
        assertThrows(IllegalArgumentException.class, () -> {
            saab95.gas(gasAmount);
        });
    }

    @ParameterizedTest
    @ValueSource(doubles = { -10, -5, -1, -0.5, -0.1, -1e-2, -1e-3, -1e-5 })
    public void brakeThrowsIllegalArgExceptionIfArgBelow0(double brakeAmount) {
        Saab95 saab95 = new Saab95();
        assertThrows(IllegalArgumentException.class, () -> {
            saab95.brake(brakeAmount);
        });
    }

    @ParameterizedTest
    @ValueSource(doubles = { 10, 5, 2, 1.5, 1.1, 1 + 1e-1, 1 + 1e-2, 1 + 1e-5 })
    public void brakeThrowsIllegalArgExceptionIfArgAbove1(double brakeAmount) {
        Saab95 saab95 = new Saab95();
        assertThrows(IllegalArgumentException.class, () -> {
            saab95.brake(brakeAmount);
        });
    }

    // Sanity check 2: current speed is in [0, enginePower]

    @Test
    public void currentSpeedIsBoundedFromBelowBy0() {
        Saab95 saab95 = new Saab95();
        saab95.brake(1);
        assertTrue(saab95.getCurrentSpeed() >= 0.0);
    }

    @Test
    public void currentSpeedIsBoundedFromAboveByEnginePower() {
        Saab95 saab95 = new Saab95();
        System.out.println(saab95.getCurrentSpeed());
        saab95.gas(1);
        assertTrue(saab95.getCurrentSpeed() >= 0.0);
        System.out.println(saab95.getCurrentSpeed());
    }

    @ParameterizedTest
    @ValueSource(doubles = { 0.0, 1e-5, 1e-4, 1e-3, 1e-2, 1e-1, 0.5, 0.9, 1 })
    public void gasCannotIncreaseSpeed(double amount) {
        Saab95 saab95 = new Saab95();
        double initialSpeed = saab95.getCurrentSpeed();
        saab95.gas(amount);
        assertTrue(saab95.getCurrentSpeed() >= initialSpeed);
    }

    @ParameterizedTest
    @ValueSource(doubles = { 0.0, 1e-5, 1e-4, 1e-3, 1e-2, 1e-1, 0.5, 0.9, 1 })
    public void brakeCannotDecreaseSpeed(double amount) {
        Saab95 saab95 = new Saab95();
        double initialSpeed = 10;
        saab95.currentSpeed = initialSpeed;
        saab95.brake(amount);
        assertTrue(saab95.getCurrentSpeed() <= initialSpeed);
    }

}

