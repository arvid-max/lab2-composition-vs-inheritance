package cars;

import java.awt.Color;

public abstract class TurboCar extends Car {

    private boolean turboOn;

    protected TurboCar(int nrDoors, double enginePower, Color color, String modelName) {
        super(nrDoors, enginePower, color, modelName);
    }

    public boolean getTurboOn() {
        return turboOn;
    }

    public void setTurboOn(){
	    turboOn = true;
    }

    public void setTurboOff(){
	    turboOn = false;
    }

    @Override
    protected abstract double speedFactor();
}