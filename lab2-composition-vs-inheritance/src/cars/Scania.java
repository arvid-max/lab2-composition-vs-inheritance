package cars;

import java.awt.*;

public class Scania extends Truck {
    private double rampAngle;
    public Scania() {
        super(2, 500, Color.white, "Scania");
        rampAngle = 0;
    }
    @Override
    public void gas(double amount){
        if (rampAngle > 0) throw new IllegalArgumentException("Cannot drive while ramp is down");
        checkAmountArg(amount);
        incrementSpeed(amount);
    }
    public double getRampAngle() { return rampAngle; }

    @Override
    public void raiseRamp() {
        rampAngle = Math.max(rampAngle - 10, 0); // raise by 10 degrees
    }
    @Override
    public void lowerRamp() {
        if (currentSpeed > 0) throw new IllegalArgumentException("Cannot lower ramp while driving");
        rampAngle = Math.min(rampAngle + 10, 70); // lower by 10 degrees
    }
}
