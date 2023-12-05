package cars;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    // The delay (ms) corresponds to 20 updates a sec (hz)
    private final int delay = 50;
    // The timer is started with a listener (see below) that executes the statements
    // each step between delays.
    private Timer timer = new Timer(delay, new TimerListener());

    // The frame that represents this instance View of the MVC pattern
    private CarController cc;
    private CarView frame;
    private DrawPanel panel;

    /* Each step the TimerListener moves all the cars in the list and tells the
     * view to update its images. Change this method to your needs.
     * */
    private class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            cc.update();
            for (int i = 0; i < cc.getCars().size(); i++) {
                Vehicle car = cc.getCars().get(i);
                int x = (int) Math.round(car.getX());
                int y = (int) Math.round(car.getY());
                panel.moveit(x, y, i);
            }

            // repaint() calls the paintComponent method of the panel
            panel.repaint();
        }
    }

    public Main() {
        // Instance of this class
        cc = new CarController();
        panel = new DrawPanel(CarView.X, CarView.Y - 240);

        // original
        cc.cars.add(new Volvo240());
        panel.addVehicleRepresentation(0, 0, 0);

        Saab95 saab95 = new Saab95();
        saab95.setPosition(0, 100);
        cc.cars.add(saab95);
        panel.addVehicleRepresentation(0, 0, 1);

        Scania scania = new Scania();
        scania.setPosition(0, 200);
        cc.cars.add(scania);
        panel.addVehicleRepresentation(0, 0, 2);


        // Start a new view and send a reference of self
        frame = new CarView("CarSim 1.0", panel, cc);

        // Start the timer
        timer.start();
    }

    public static void main(String[] args) {
        new Main();
    }
}
