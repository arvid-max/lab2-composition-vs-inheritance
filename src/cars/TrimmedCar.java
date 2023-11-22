package cars;

import java.awt.*;

public abstract class TrimmedCar extends Car {

    protected TrimmedCar(int nrDoors, double enginePower, Color color, String modelName) {
        super(nrDoors, enginePower, color, modelName);
    }

    @Override
    protected abstract double speedFactor();


}