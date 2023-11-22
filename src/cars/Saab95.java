package cars;

import java.awt.*;

public class Saab95 extends TurboCar {

    private static final double BOOST_VALUE_TURBO_OFF = 1.0;
    private static final double BOOST_VALUE_TURBO_ON = 1.3;

    public Saab95() {
        super(2, 125, Color.red, "Saab95");
    }

    @Override
    protected double speedFactor(){
        double turboBoost = getTurboOn() ? BOOST_VALUE_TURBO_ON : BOOST_VALUE_TURBO_OFF;
        return baseSpeedFactor() * turboBoost;
    }

}