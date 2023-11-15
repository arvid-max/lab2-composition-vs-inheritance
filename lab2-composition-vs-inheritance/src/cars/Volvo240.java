package cars;

import java.awt.*;

public class Volvo240 extends TrimmedCar {

    public final static double TRIMFACTOR = 1.25;

    public Volvo240() {
        super(4, 100, Color.black, "Volvo240");
    }

    @Override
    protected double speedFactor(){
        return baseSpeedFactor() * TRIMFACTOR;
    }

}