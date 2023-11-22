package cars;

public interface Loadable<T extends Vehicle> {
    void loadCar(T car);
    void unloadCar(T car);
}
