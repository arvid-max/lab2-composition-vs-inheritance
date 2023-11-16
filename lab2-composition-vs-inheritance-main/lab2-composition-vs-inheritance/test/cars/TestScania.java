package cars;

import static org.junit.Assert.assertSame;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;

public class TestScania {
    @Test
    public void SpeedStartsAtZero() {
        Scania scania = new Scania();
        assertEquals(0, scania.getCurrentSpeed());
    }
    @Test
    public void FullyLoweredRampCannotBeLowered() {
        Scania scania = new Scania();
        scania.lowerRamp(80);
        assertEquals(70, scania.getRampAngle());
    }
    @Test
    public void FullyRaisedRampCannotBeRaised() {
        Scania scania = new Scania();
        scania.raiseRamp(10);
        assertEquals(0, scania.getRampAngle());
    }
    @Test
    public void CannotDriveWhileRampIsDown() {
        Scania scania = new Scania();
        scania.lowerRamp(70);
        assertThrows(IllegalArgumentException.class, () -> {
            scania.gas(1);
        });
    }
    @Test
    public void CannotLowerRampWhileDriving() {
        Scania scania = new Scania();
        scania.gas(1);
        assertThrows(IllegalArgumentException.class, () -> {
            scania.lowerRamp(10);
        });
    }

    @Test
    public void StartEngineAfterLoweringRamp() {
        Scania scania = new Scania();
        scania.lowerRamp(30);
        assertThrows(IllegalArgumentException.class, () -> {
            scania.startEngine();
        });
    }
}
