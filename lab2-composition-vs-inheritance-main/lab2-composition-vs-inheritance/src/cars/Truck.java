package cars;

import java.awt.*;

public abstract class Truck extends Car implements Ramp {
    protected double rampAngle;
    protected double maxAngle;

    protected Truck(int nrDoors, double enginePower, Color color, String modelName, double maxAngle) {
        super(nrDoors, enginePower, color, modelName);
        this.maxAngle = maxAngle;
    }
    public double getRampAngle() { return rampAngle; }
    public double getMaxAngle() { return maxAngle; }
    @Override
    public void gas(double amount){
        if (rampAngle > 0) throw new IllegalArgumentException("Cannot drive while ramp is down");
        super.gas(amount);
    }
    @Override
    public void startEngine() {
        if (rampAngle > 0) throw new IllegalArgumentException("Cannot drive while ramp is down");
        super.startEngine();
    }

    @Override
    public void raiseRamp(double angle) {
        rampAngle = Math.max(rampAngle - angle, 0);
    }

    @Override
    public void lowerRamp(double angle) {
        if (currentSpeed > 0) throw new IllegalArgumentException("Cannot lower ramp while driving");
        rampAngle = Math.min(rampAngle + angle, maxAngle);
    }
    @Override
    protected double speedFactor() {
        return baseSpeedFactor();
    }
}
