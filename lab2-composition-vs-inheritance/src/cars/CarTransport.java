package cars;

import java.awt.*;
import java.util.ArrayList;

public abstract class CarTransport extends Truck implements Loadable<Car> {
    private ArrayList<Car> loadedCars;
    private boolean rampIsDown;
    protected int carCapacity;
    protected CarTransport(int nrDoors, double enginePower, Color color, String modelName) {
        super(nrDoors, enginePower, color, modelName);
        rampIsDown = false;
        loadedCars = new ArrayList<>();
    }
    public boolean getRampIsDown() {
        return rampIsDown;
    }
    public ArrayList<Car> getLoadedCars() { return loadedCars; }
    public int getCarCapacity() { return carCapacity; }
    @Override
    public void gas(double amount){
        if (rampIsDown) throw new IllegalArgumentException("Cannot drive while ramp is down");
        checkAmountArg(amount);
        incrementSpeed(amount);
    }

    @Override
    public void raiseRamp() {
        rampIsDown = false;
    }
    @Override
    public void lowerRamp() {
        if (currentSpeed > 0) throw new IllegalArgumentException("Cannot lower ramp while driving");
        rampIsDown = true;
    }

    // TODO make the loaded car always follow the truck it is on
    public void loadCar(Car car) {
        if (loadedCars.size() == carCapacity) throw new IllegalArgumentException("Exceeds car limit");
        else if (car instanceof Truck) throw new IllegalArgumentException("Cannot load trucks");
        else if (!rampIsDown) throw new IllegalArgumentException("Cannot load cars while ramp is up");
        // car within 5 of self
        else if (!(getX() - 5 < car.getX() && getX() + 5 > car.getX() && getY() - 5 < car.getY() && getY() + 5 > car.getY()))
            throw new IllegalArgumentException("Car is too far away");
        loadedCars.add(car);
        car.setPosition(getX(), getY()); // only sets position when loading
        car.stopEngine();
    }
    public void unloadCar(Car car) {
        if (!rampIsDown) throw new IllegalArgumentException("Cannot unload cars while ramp is up");
        else if (car != loadedCars.get(loadedCars.size() - 1))
            throw new IllegalArgumentException("Cannot unload that car yet");
        loadedCars.remove(car);
        car.setPosition(getX(), getY()); // only sets position when unloading
    }
}
