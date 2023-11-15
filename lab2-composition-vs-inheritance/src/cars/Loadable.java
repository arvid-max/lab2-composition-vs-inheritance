package cars;

public interface Loadable<T extends Car> {
    void loadCar(T car);
    void unloadCar(T car);
}
