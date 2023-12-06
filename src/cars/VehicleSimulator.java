package cars;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VehicleSimulator {

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    private VehicleGroup vehicleGroup;
    private VehicleSimulatorController simulatorController;
    private VehicleSimulatorView simulatorView;

    /* Each step the TimerListener moves all the cars in the list and tells the
     * view to update its images. Change this method to your needs.
     * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            vehicleGroup.update();
            // repaint() calls the paintComponent method of the panel
            simulatorView.repaint();
        }
    }

    public VehicleSimulator() {
        vehicleGroup = new VehicleGroup();
        simulatorView = new VehicleSimulatorView(SimulatorWindow.WIDTH, SimulatorWindow.HEIGHT - 240);
        vehicleGroup.addObserver(simulatorView);
        simulatorController = new VehicleSimulatorController(vehicleGroup, SimulatorWindow.WIDTH);

        VehicleFactory factory = new VehicleFactory();
        ArrayList<String> names = factory.getPossibleVehiclesNames();
        for(String name : names) {
            System.out.println("pics/" + name + ".jpg");
            simulatorView.loadImage(name, "pics/" + name + ".jpg");
        }

        vehicleGroup.addVehicle(factory.createVolvo(0, 0));
        vehicleGroup.addVehicle(factory.createSaab(0, 100));
        vehicleGroup.addVehicle(factory.createScania(0, 200));

        // Start a new view and send a reference of self
        new SimulatorWindow("VehicleSim 1.0", simulatorView, simulatorController);

        // Start the timer
        timer.start();
    }

    public static void main(String[] args) {
        new VehicleSimulator();
    }
}
