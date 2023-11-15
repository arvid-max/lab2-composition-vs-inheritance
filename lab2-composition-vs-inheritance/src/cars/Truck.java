package cars;

import java.awt.*;

public abstract class Truck extends Car implements Ramp {
    protected Truck(int nrDoors, double enginePower, Color color, String modelName) {
        super(nrDoors, enginePower, color, modelName);
    }
    @Override
    protected double speedFactor() {
        return baseSpeedFactor();
    }
}
