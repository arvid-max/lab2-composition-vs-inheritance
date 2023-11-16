package cars;

import static org.junit.Assert.assertSame;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;
public class TestGeneralCarWorkshop {
    @Test
    public void CannotExceedCarCapacity() {
        GeneralCarWorkshop generalCarWorkshop = new GeneralCarWorkshop(1);
        Volvo240 volvo240 = new Volvo240();
        for (int i = 0; i < generalCarWorkshop.getCarCapacity(); i++) generalCarWorkshop.loadCar(volvo240);
        assertThrows(IllegalArgumentException.class, () -> {
            generalCarWorkshop.loadCar(volvo240);
        });
    }
    @Test
    public void UnloadCarWorks() {
        GeneralCarWorkshop generalCarWorkshop = new GeneralCarWorkshop(1);
        Volvo240 volvo240 = new Volvo240();
        generalCarWorkshop.loadCar(volvo240);
        generalCarWorkshop.unloadCar(volvo240);
        assertEquals(0, generalCarWorkshop.getLoadedCars().size());
    }
    @Test
    public void AnyCarCanBeLoaded() {
        GeneralCarWorkshop generalCarWorkshop = new GeneralCarWorkshop(4);
        Volvo240 volvo240 = new Volvo240();
        Saab95 saab95 = new Saab95();
        Scania scania = new Scania();
        MercedesActros mercedesActros = new MercedesActros();
        generalCarWorkshop.loadCar(volvo240);
        generalCarWorkshop.loadCar(saab95);
        generalCarWorkshop.loadCar(scania);
        generalCarWorkshop.loadCar(mercedesActros);
        assertEquals(4, generalCarWorkshop.getLoadedCars().size());
    }

}
