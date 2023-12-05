package cars;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/*
* This class represents the Controller part in the MVC pattern.
* Its responsibilities are to listen to the View and responds in an appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarController implements Controllable {
    // member fields:
    // A list of cars, modify if needed
    ArrayList<Vehicle> cars = new ArrayList<>();

    //methods:
    public ArrayList<Vehicle> getCars() {
        return cars;
    }

    private boolean checkIfCarHitsWall(Vehicle car){
        if (car.getY() > 560 || car.getY() < 0 || car.getX() > 700 || car.getX() < 0){
            return true;
        }
        return false;
    }

    private void stopAndTurnCar(Vehicle car) {
        car.stopEngine();
        switch(car.getDirection()) {
            case NORTH -> car.setPosition(car.getX(), 0);
            case SOUTH -> car.setPosition(car.getX(), 500);
            case WEST  -> car.setPosition(0, car.getY());
            case EAST  -> car.setPosition(700, car.getY());
        }
        car.turnLeft();
        car.turnLeft();
        // car.startEngine();
    }
    
    public void update() {
        for (Vehicle car : cars) {
            car.move();
            if (checkIfCarHitsWall(car)) stopAndTurnCar(car);
        }
    }

    // Calls the gas method for each car once
    public void gas(int amount) {
        double gasAmount = ((double) amount) / 100.0;
        for (Vehicle car : cars) {
            car.gas(gasAmount);
        }
    }
    // Calls the brake method for each car once
    public void brake(int amount) {
        double brake = ((double) amount) / 100.0;
        for (Vehicle car : cars){
            car.brake(brake);
        }
    }
    // Calls the stopEngine method for each car once
    public void stopAll() {
        for (Vehicle car : cars) {
            car.stopEngine();
        }
    }
    // Calls the startEngine method for each car once
    public void startAll() {
        for (Vehicle car : cars) {
            car.startEngine();
        }
    }
    // Calls the setTurboOn method for each car with turbo once
    public void setTurboOn() {
        for (Vehicle car : cars)
            if (car instanceof TurboCar) ((TurboCar) car).setTurboOn();
    }
    // Calls the setTurboOff method for each car with turbo once
    public void setTurboOff() {
        for (Vehicle car : cars)
            if (car instanceof TurboCar) ((TurboCar) car).setTurboOff();
    }
    // Calls the method for each truck once
    public void raiseRamps() {
        for (Vehicle car : cars)
            if (car instanceof Truck) ((Truck) car).raiseRamp(((Truck) car).maxAngle);
    }
    public void lowerRamps() {
        for (Vehicle car : cars)
            if (car instanceof Truck) ((Truck) car).lowerRamp(((Truck) car).maxAngle);
    }
}
