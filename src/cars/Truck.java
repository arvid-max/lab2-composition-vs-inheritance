package cars;

import java.awt.*;

public abstract class Truck extends Vehicle implements Ramp {
    protected double rampAngle;
    protected double maxAngle;

    protected Truck(int nrDoors, double enginePower, Color color, String modelName, double maxAngle) {
        super(nrDoors, enginePower, color, modelName);
        this.maxAngle = maxAngle;
    }
    public double getRampAngle() { return rampAngle; }
    public double getMaxAngle() { return maxAngle; }

    @Override
    public void raiseRamp(double angle) {
        rampAngle = Math.max(rampAngle - angle, 0);
        if(rampAngle == 0.0) {
            setCanDrive(true);
        }
    }

    @Override
    public void lowerRamp(double angle) {
        if (currentSpeed > 0) throw new IllegalArgumentException("Cannot lower ramp while driving");
        rampAngle = Math.min(rampAngle + angle, maxAngle);
        setCanDrive(false);
    }
    @Override
    protected double speedFactor() {
        return baseSpeedFactor();
    }
}
