package cars;

public interface Loadable<T extends Car> {
    public void loadCar(T car);
    public void unloadCar(T car);
}
