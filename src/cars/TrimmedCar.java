package cars;

import java.awt.*;

public abstract class TrimmedCar extends Car {
    public final static double TRIMFACTOR = 1.25;
    protected TrimmedCar(int nrDoors, double enginePower, Color color, String modelName) {
        super(nrDoors, enginePower, color, modelName);
    }
    @Override
    protected double speedFactor(){
        return baseSpeedFactor() * TRIMFACTOR;
    }
}