package cars;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;

// This panel represent the animated part of the view with the car images.

public class DrawPanel extends JPanel implements Observer {

    private ArrayList<VehicleRepresentation> vehicleRepresentations;
    private ArrayList<BufferedImage> images;

    // Initializes the panel and reads the images
    public DrawPanel(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);

        images = new ArrayList<>();
        vehicleRepresentations = new ArrayList<>();
        // Print an error message in case file is not found with a try/catch block
        try {
            images.add(ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Volvo240.jpg")));
            images.add(ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Saab95.jpg")));
            images.add(ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Scania.jpg")));
        } catch (IOException ex) {
            ex.printStackTrace();
        }

    }

    // Returns the id of the newly created VehicleRepresentation
    public int addVehicleRepresentation(int x, int y, int imageIndex) {
        vehicleRepresentations.add(new VehicleRepresentation(x, y, imageIndex));
        return vehicleRepresentations.size() - 1;
    }

    public void notify(int id, int x, int y) {
        vehicleRepresentations.get(id).setPosition(x, y);
    }

    // This method is called each time the panel updates/refreshes/repaints itself
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(VehicleRepresentation vehicleRepresentation : vehicleRepresentations) {
            // see javadoc for more info on the parameters
            g.drawImage(images.get(vehicleRepresentation.getImageIndex()),
                    vehicleRepresentation.getX(), vehicleRepresentation.getY(), null);
        }
    }
}
