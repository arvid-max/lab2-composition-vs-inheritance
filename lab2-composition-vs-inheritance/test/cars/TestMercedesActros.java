package cars;

import static org.junit.Assert.assertSame;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;

public class TestMercedesActros {
    @Test
    public void LowerAndRaiseRampWhileStationary() {
        MercedesActros mercedesActros = new MercedesActros();
        assertSame(false, mercedesActros.getRampIsDown());
        mercedesActros.lowerRamp();
        assertSame(true, mercedesActros.getRampIsDown());
        mercedesActros.raiseRamp();
        assertSame(false, mercedesActros.getRampIsDown());
    }
    @Test
    public void CannotDriveWhileRampIsDown() {
        MercedesActros mercedesActros = new MercedesActros();
        mercedesActros.lowerRamp();
        assertThrows(IllegalArgumentException.class, () -> {
            mercedesActros.gas(1);
        });
    }
    @Test
    public void CannotLowerRampWhileDriving() {
        MercedesActros mercedesActros = new MercedesActros();
        mercedesActros.gas(1);
        assertThrows(IllegalArgumentException.class, mercedesActros::lowerRamp);
    }
    @Test
    public void LoadedCarsCannotExceedCarCapacity() {
        MercedesActros mercedesActros = new MercedesActros();
        mercedesActros.lowerRamp();
        Volvo240 volvo240 = new Volvo240();
        for (int i = 0; i < mercedesActros.getCarCapacity(); i++) mercedesActros.loadCar(volvo240);
        assertThrows(IllegalArgumentException.class, () -> {
            mercedesActros.loadCar(volvo240);
        });
    }
    @Test
    public void CannotLoadTrucks() {
        MercedesActros mercedesActros = new MercedesActros();
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
        MercedesActros mercedesActros = new MercedesActros();
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
        MercedesActros mercedesActros = new MercedesActros();
        mercedesActros.lowerRamp();
        Volvo240 volvo240 = new Volvo240();
        mercedesActros.loadCar(volvo240);
        mercedesActros.unloadCar(volvo240);
        assertEquals(0, mercedesActros.getLoadedCars().size());
    }

    // TODO make tests for loadCar and unloadCar
}
