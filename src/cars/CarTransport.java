package cars;

import java.awt.*;
import java.util.ArrayList;

public abstract class CarTransport extends Truck implements Loadable<Car> {
    private ArrayList<Car> loadedCars;
    protected int carCapacity;
    protected CarTransport(int nrDoors, double enginePower, Color color, String modelName, double maxAngle, int carCapacity) {
        super(nrDoors, enginePower, color, modelName, maxAngle);
        loadedCars = new ArrayList<>();
        this.carCapacity = carCapacity;
    }
    public ArrayList<Car> getLoadedCars() { return loadedCars; }
    public int getCarCapacity() { return carCapacity; }
    public void loadCar(Car car) {
        // tests if loading is possible
        if (loadedCars.size() == carCapacity) throw new IllegalArgumentException("Exceeds car limit");
        else if (rampAngle == 0) throw new IllegalArgumentException("Cannot load cars while ramp is up");
        else if (!(getX() - 5 < car.getX() && getX() + 5 > car.getX() && getY() - 5 < car.getY() && getY() + 5 > car.getY()))
            throw new IllegalArgumentException("Car is too far away to be loaded");
        // loads the car
        car.setCanDrive(false);
        loadedCars.add(car);
        car.stopEngine();
        car.setPosition(getX(), getY());
    }
    public void unloadCar(Car car) {
        // tests if unloading is possible
        if (rampAngle == 0) throw new IllegalArgumentException("Cannot unload cars while ramp is up");
        else if (car != loadedCars.get(loadedCars.size() - 1))
            throw new IllegalArgumentException("Cannot unload that car yet");
        // unloads the car
        car.setCanDrive(true);
        loadedCars.remove(car);
        car.setPosition(getX(), getY());
    }
    @Override
    public void move() {
        super.move();
        for(Car c : loadedCars) {
            c.setPosition(getX(), getY());
        }
    }
    @Override
    public void raiseRamp(double angle) {
        super.raiseRamp(maxAngle);
    }
    @Override
    public void lowerRamp(double angle) {
        super.lowerRamp(maxAngle);
    }

}
