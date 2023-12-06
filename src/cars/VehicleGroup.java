package cars;
import java.util.ArrayList;

/*
* This class represents the Controller part in the MVC pattern.
* Its responsibilities are to listen to the View and responds in an appropriate manner by
* modifying the model state and the updating the view.
 */

public class VehicleGroup implements Controllable {
    // member fields:
    // A list of cars, modify if needed
    private ArrayList<Vehicle> vehicles = new ArrayList<>();
    private ArrayList<Observer> observers = new ArrayList<>();

    public void addObserver(Observer o) {
        observers.add(o);
    }

    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    private void notifyObservers() {
        for(int i = 0; i < vehicles.size(); i++) {
            Vehicle vehicle = vehicles.get(i);
            for(Observer o : observers) {
                o.notify(i, (int) vehicle.getX(), (int) vehicle.getY());
            }
        }
    }

    //methods:
    public ArrayList<Vehicle> getVehicles() {
        return vehicles;
    }
    public void addVehicle(Vehicle vehicle) { vehicles.add(vehicle); }
    public void removeVehicle(Vehicle vehicle) { vehicles.remove(vehicle); }


    private boolean checkIfVehicleHitsWall(Vehicle vehicle){
        if (vehicle.getY() > 560 || vehicle.getY() < 0 || vehicle.getX() > 700 || vehicle.getX() < 0){
            return true;
        }
        return false;
    }

    private void stopAndTurnVehicle(Vehicle vehicle) {
        vehicle.stopEngine();
        switch(vehicle.getDirection()) {
            case NORTH -> vehicle.setPosition(vehicle.getX(), 0);
            case SOUTH -> vehicle.setPosition(vehicle.getX(), 500);
            case WEST  -> vehicle.setPosition(0, vehicle.getY());
            case EAST  -> vehicle.setPosition(700, vehicle.getY());
        }
        vehicle.turnLeft();
        vehicle.turnLeft();
    }
    
    public void update() {
        for (Vehicle vehicle : vehicles) {
            vehicle.move();
            if (checkIfVehicleHitsWall(vehicle)) stopAndTurnVehicle(vehicle);
        }
        notifyObservers();
    }

    // Calls the gas method for each vehicle once
    public void gas(int amount) {
        double gasAmount = ((double) amount) / 100.0;
        for (Vehicle vehicle : vehicles) {
            vehicle.gas(gasAmount);
        }
    }
    // Calls the brake method for each vehicle once
    public void brake(int amount) {
        double brake = ((double) amount) / 100.0;
        for (Vehicle vehicle : vehicles){
            vehicle.brake(brake);
        }
    }
    // Calls the stopEngine method for each vehicle once
    public void stopAll() {
        for (Vehicle vehicle : vehicles) {
            vehicle.stopEngine();
        }
    }
    // Calls the startEngine method for each vehicle once
    public void startAll() {
        for (Vehicle vehicle : vehicles) {
            vehicle.startEngine();
        }
    }
    // Calls the setTurboOn method for each vehicle with turbo once
    public void setTurboOn() {
        for (Vehicle vehicle : vehicles)
            if (vehicle instanceof TurboCar) ((TurboCar) vehicle).setTurboOn();
    }
    // Calls the setTurboOff method for each vehicle with turbo once
    public void setTurboOff() {
        for (Vehicle vehicle : vehicles)
            if (vehicle instanceof TurboCar) ((TurboCar) vehicle).setTurboOff();
    }
    // Calls the method for each truck once
    public void raiseRamps() {
        for (Vehicle vehicle : vehicles)
            if (vehicle instanceof Truck) ((Truck) vehicle).raiseRamp(((Truck) vehicle).maxAngle);
    }
    public void lowerRamps() {
        for (Vehicle vehicle : vehicles)
            if (vehicle instanceof Truck) ((Truck) vehicle).lowerRamp(((Truck) vehicle).maxAngle);
    }
}
