package cars;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/*
* This class represents the Controller part in the MVC pattern.
* It's responsibilities is to listen to the View and responds in a appropriate manner by
* modifying the model state and the updating the view.
 */

public class CarController {
    // member fields:

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with an listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    CarView frame;
    // A list of cars, modify if needed
    ArrayList<Vehicle> cars = new ArrayList<>();

    //methods:

    public static void main(String[] args) {
        // Instance of this class
        CarController cc = new CarController();

        // original
        cc.cars.add(new Volvo240());

        Saab95 saab95 = new Saab95();
        saab95.setPosition(0, 100);
        cc.cars.add(saab95);

        Scania scania = new Scania();
        scania.setPosition(0, 200);
        cc.cars.add(scania);

        // Start a new view and send a reference of self
        cc.frame = new CarView("CarSim 1.0", cc);

        // Start the timer
        cc.timer.start();
    }

    /* Each step the TimerListener moves all the cars in the list and tells the
    * view to update its images. Change this method to your needs.
    * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            for (Vehicle car : cars) {
                car.move();
                if(checkIfCarHitsWall(car)) stopAndTurnCar(car);

                int x = (int) Math.round(car.getX());
                int y = (int) Math.round(car.getY());
                int index = cars.indexOf(car);
                frame.drawPanel.moveit(x, y, index);
                // repaint() calls the paintComponent method of the panel
                frame.drawPanel.repaint();
            }
        }

        boolean checkIfCarHitsWall(Vehicle car){
            if (car.getY() > 560 || car.getY() < 0 || car.getX() > 700 || car.getX() < 0){
                return true;
            }
            return false;
        }
        void stopAndTurnCar(Vehicle car) {
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
    }

    // Calls the gas method for each car once
    void gas(int amount) {
        double gasAmount = ((double) amount) / 100.0;
        for (Vehicle car : cars) {
            car.gas(gasAmount);
        }
    }
    // Calls the brake method for each car once
    void brake(int amount) {
        double brake = ((double) amount) / 100.0;
        for (Vehicle car : cars){
            car.brake(brake);
        }
    }
    // Calls the stopEngine method for each car once
    void stopAll() {
        for (Vehicle car : cars) {
            car.stopEngine();
        }
    }
    // Calls the startEngine method for each car once
    void startAll() {
        for (Vehicle car : cars) {
            car.startEngine();
        }
    }
    // Calls the setTurboOn method for each car with turbo once
    void setTurboOn() {
        for (Vehicle car : cars)
            if (car instanceof TurboCar) ((TurboCar) car).setTurboOn();
    }
    // Calls the setTurboOff method for each car with turbo once
    void setTurboOff() {
        for (Vehicle car : cars)
            if (car instanceof TurboCar) ((TurboCar) car).setTurboOff();
    }
    // Calls the method for each truck once
    void raiseRamps() {
        for (Vehicle car : cars)
            if (car instanceof Truck) ((Truck) car).raiseRamp(((Truck) car).maxAngle);
    }
    void lowerRamps() {
        for (Vehicle car : cars)
            if (car instanceof Truck) ((Truck) car).lowerRamp(((Truck) car).maxAngle);
    }
}
