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
        Point position;
        BufferedImage image;
        VehicleRepresentation(BufferedImage image) {
            this.position = new Point();
            this.image = image;
        }
    }
    ArrayList<VehicleRepresentation> vehicleRepresentations;
    ArrayList<BufferedImage> images;
    void addVehicleRepresentation(BufferedImage image) {
        vehicleRepresentations.add(new VehicleRepresentation(image));
    }
    void moveit(int x, int y, int index) {
        vehicleRepresentations.get(index).position.x = x;
        vehicleRepresentations.get(index).position.y = y;
    }

    // Initializes the panel and reads the images
    public DrawPanel(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);

        images = new ArrayList<>();
        vehicleRepresentations = new ArrayList<>();
        // Print an error message in case file is not found with a try/catch block
        try {
            // You can remove the "pics" part if running outside of IntelliJ and
            // everything is in the same main folder.
            // volvoImage = ImageIO.read(new File("Volvo240.jpg"));

            // Rememember to rightclick src New -> Package -> name: pics -> MOVE *.jpg to pics.
            // if you are starting in IntelliJ.
            images.add(ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Volvo240.jpg")));
            images.add(ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Saab95.jpg")));
            images.add(ImageIO.read(DrawPanel.class.getResourceAsStream("pics/Scania.jpg")));
            addVehicleRepresentation(images.get(0));
            addVehicleRepresentation(images.get(1));
            addVehicleRepresentation(images.get(2));

        } catch (IOException ex)
        {
            ex.printStackTrace();
        }

    }

    // This method is called each time the panel updates/refreshes/repaints itself
    // TODO: Change to suit your needs.
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(VehicleRepresentation vehicleRepresentation : vehicleRepresentations) {
            // see javadoc for more info on the parameters
            g.drawImage(vehicleRepresentation.image, vehicleRepresentation.position.x, vehicleRepresentation.position.y, null);
        }
    }
}
