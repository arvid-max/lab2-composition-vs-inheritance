package cars;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.*;

// This panel represent the animated part of the view with the car images.

public class DrawPanel extends JPanel{
    private class VehicleRepresentation {
        int posX;
        int posY;
        int image_index;
        private VehicleRepresentation(int posX, int posY, int image_index) {
            this.posX = posX;
            this.posY = posY;
            this.image_index = image_index;
        }
    }
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

    public int addVehicleRepresentation(int x, int y, int index) {
        vehicleRepresentations.add(new VehicleRepresentation(x, y, index));
        return vehicleRepresentations.size() - 1;
    }

    public void moveit(int x, int y, int index) {
        vehicleRepresentations.get(index).posX = x;
        vehicleRepresentations.get(index).posY = y;
    }

    // This method is called each time the panel updates/refreshes/repaints itself
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(VehicleRepresentation vehicleRepresentation : vehicleRepresentations) {
            // see javadoc for more info on the parameters
            g.drawImage(images.get(vehicleRepresentation.image_index),
                    vehicleRepresentation.posX, vehicleRepresentation.posY, null);
        }
    }
}
