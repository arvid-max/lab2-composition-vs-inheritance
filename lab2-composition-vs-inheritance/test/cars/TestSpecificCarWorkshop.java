package cars;

import static org.junit.Assert.assertSame;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import org.junit.jupiter.api.Test;
public class TestSpecificCarWorkshop {
    @Test
    public void OnlyOneTypeOfCarCanBeLoaded() {
        SpecificCarWorkshop<Saab95> saabWorkshop = new SpecificCarWorkshop<>(1);
        Saab95 saab95 = new Saab95();
        saabWorkshop.loadCar(saab95);
        Volvo240 volvo240 = new Volvo240();
        // saabWorkshop.loadCar(volvo240); compile error
    }
}
