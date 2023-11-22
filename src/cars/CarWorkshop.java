package cars;

import java.util.ArrayList;

public class CarWorkshop<T extends Vehicle> implements Loadable<T> {
    protected int carCapacity;
    protected ArrayList<T> loadedCars;
    protected CarWorkshop(int carCapacity) {
        this.carCapacity = carCapacity;
        loadedCars = new ArrayList<>();
    }
    public ArrayList<T> getLoadedCars() {
        return loadedCars;
    }
    public int getCarCapacity() {
        return carCapacity;
    }
    @Override
    public void loadCar(T car) {
        if (loadedCars.size() == carCapacity) throw new IllegalArgumentException("Exceeds car limit");
        loadedCars.add(car);
    }
    @Override
    public void unloadCar(T car) {
        loadedCars.remove(car);
    }
}
