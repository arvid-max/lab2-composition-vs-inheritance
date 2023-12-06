package cars;

public interface Controllable {
    void gas(int amount);
    void brake(int amount);
    void stopAll();
    void startAll();
    void setTurboOff();
    void setTurboOn();
    void raiseRamps();
    void lowerRamps();
    void addVehicle();
    void removeVehicle();
}
