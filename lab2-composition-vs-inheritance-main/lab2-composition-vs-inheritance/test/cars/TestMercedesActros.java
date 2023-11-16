package cars;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestMercedesActros {
    MercedesActros mercedesActros;
    @BeforeEach
    void setup() {
            mercedesActros = new MercedesActros();
    }

    @Test
    public void LowerAndRaiseRampWhileStationary() {
        assertEquals(0, mercedesActros.getRampAngle());
        mercedesActros.lowerRamp();
        assertEquals(mercedesActros.getMaxAngle(), mercedesActros.getRampAngle());
        mercedesActros.raiseRamp();
        assertEquals(0, mercedesActros.getRampAngle());
    }
    @Test
    public void CannotDriveWhileRampIsDown() {
        mercedesActros.lowerRamp();
        assertThrows(IllegalArgumentException.class, () -> {
            mercedesActros.gas(1);
        });
    }
    @Test
    public void CannotLowerRampWhileDriving() {
        mercedesActros.gas(1);
        assertThrows(IllegalArgumentException.class, () -> {
            mercedesActros.lowerRamp(mercedesActros.getRampAngle());
        });
    }
    @Test
    public void LoadedCarsCannotExceedCarCapacity() {
        mercedesActros.lowerRamp(mercedesActros.getRampAngle());
        Volvo240 volvo240 = new Volvo240();
        for (int i = 0; i < mercedesActros.getCarCapacity(); i++) mercedesActros.loadCar(volvo240);
        assertThrows(IllegalArgumentException.class, () -> {
            mercedesActros.loadCar(volvo240);
        });
    }
    @Test
    public void CannotLoadTrucks() {
        mercedesActros.lowerRamp();
        Scania scania = new Scania();
        assertThrows(IllegalArgumentException.class, () -> {
            mercedesActros.loadCar(scania);
        });
    }
    @Test
    public void CannotLoadWhileRampIsUp() {
        MercedesActros mercedesActros = new MercedesActros();
        Saab95 saab95 = new Saab95();
        assertThrows(IllegalArgumentException.class, () -> {
            mercedesActros.loadCar(saab95);
        });
    }
    @Test
    public void CannotLoadCarOutOfBounds() {
        MercedesActros mercedesActros = new MercedesActros();
        mercedesActros.lowerRamp();
        Saab95 saab95 = new Saab95();
        saab95.incrementSpeed(10);
        saab95.move();
        assertThrows(IllegalArgumentException.class, () -> {
            mercedesActros.loadCar(saab95);
        });
    }
    @Test
    public void CannotUnloadWhileRampIsUp() {
        MercedesActros mercedesActros = new MercedesActros();
        mercedesActros.lowerRamp();
        Volvo240 volvo240 = new Volvo240();
        mercedesActros.loadCar(volvo240);
        mercedesActros.raiseRamp();
        assertThrows(IllegalArgumentException.class, () -> {
            mercedesActros.unloadCar(volvo240);
        });
    }
    @Test
    public void CannotUnloadCarThatIsNotLastIn() {
        mercedesActros.lowerRamp();
        Volvo240 volvo240 = new Volvo240();
        Saab95 saab95 = new Saab95();
        mercedesActros.loadCar(volvo240);
        mercedesActros.loadCar(saab95);
        assertThrows(IllegalArgumentException.class, () -> {
            mercedesActros.unloadCar(volvo240);
        });
    }
    @Test
    public void UnloadCarWorks() {
        mercedesActros.lowerRamp();
        Volvo240 volvo240 = new Volvo240();
        mercedesActros.loadCar(volvo240);
        mercedesActros.unloadCar(volvo240);
        assertEquals(0, mercedesActros.getLoadedCars().size());
    }
    /*@Test
    public void CarRecognizesCarTransport() {
        mercedesActros.lowerRamp();
        Volvo240 volvo240 = new Volvo240();
        assertSame(null, volvo240.getCarTransport()); // new car has a null carTransport variable
        mercedesActros.loadCar(volvo240);
        assertSame(mercedesActros, volvo240.getCarTransport());
    }*/
    @Test
    public void LoadedCarAlwaysFollowsTheCarTransport() {
        mercedesActros.lowerRamp();
        Volvo240 volvo240 = new Volvo240();
        mercedesActros.loadCar(volvo240);
        mercedesActros.raiseRamp();
        // follows after being loaded
        assertEquals(mercedesActros.getX(), volvo240.getX());
        assertEquals(mercedesActros.getY(), volvo240.getY());
        mercedesActros.gas(1);
        mercedesActros.move();
        // follows after transport has been moved
        assertEquals(mercedesActros.getX(), volvo240.getX());
        assertEquals(mercedesActros.getY(), volvo240.getY());
        mercedesActros.stopEngine();
        mercedesActros.lowerRamp();
        mercedesActros.unloadCar(volvo240);
        // follows after being unloaded
        assertEquals(mercedesActros.getX(), volvo240.getX());
        assertEquals(mercedesActros.getY(), volvo240.getY());
    }
    @Test
    public void LoadedCarCannotGas() {
        mercedesActros.lowerRamp();
        Volvo240 volvo240 = new Volvo240();
        mercedesActros.loadCar(volvo240);
        assertThrows(IllegalArgumentException.class, () -> {
            volvo240.gas(1);
        });
    }
}
