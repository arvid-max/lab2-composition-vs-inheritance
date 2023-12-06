package cars;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VehicleSimulator {

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    private VehicleGroup vg;
    private VehicleView frame;
    private DrawPanel panel;

    /* Each step the TimerListener moves all the cars in the list and tells the
     * view to update its images. Change this method to your needs.
     * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            vg.update();
            // repaint() calls the paintComponent method of the panel
            panel.repaint();
        }
    }

    public VehicleSimulator() {
        // Instance of this class
        vg = new VehicleGroup();
        panel = new DrawPanel(VehicleView.X, VehicleView.Y - 240);
        vg.addObserver(panel);

        // original
        vg.addVehicle(new Volvo240());
        panel.addVehicleRepresentation(0, 0, 0);

        Saab95 saab95 = new Saab95();
        saab95.setPosition(0, 100);
        vg.addVehicle(saab95);
        panel.addVehicleRepresentation(0, 0, 1);

        Scania scania = new Scania();
        scania.setPosition(0, 200);
        vg.addVehicle(scania);
        panel.addVehicleRepresentation(0, 0, 2);


        // Start a new view and send a reference of self
        frame = new VehicleView("VehicleSim 1.0", panel, vg);

        // Start the timer
        timer.start();
    }

    public static void main(String[] args) {
        new VehicleSimulator();
    }
}
