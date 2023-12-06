package cars;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.imageio.ImageIO;
import javax.swing.*;

// This panel represent the animated part of the view with the car images.

public class VehicleSimulatorView extends JPanel implements Observer {

    private ArrayList<VehicleRepresentation> vehicleRepresentations;
    private HashMap<String, BufferedImage> images;

    // Initializes the panel and reads the images
    public VehicleSimulatorView(int x, int y) {
        this.setDoubleBuffered(true);
        this.setPreferredSize(new Dimension(x, y));
        this.setBackground(Color.green);

        images = new HashMap<>();
        vehicleRepresentations = new ArrayList<>();
    }

    public void loadImage(String name, String imageName) {
        // Print an error message in case file is not found with a try/catch block
        try {
            images.put(name, ImageIO.read(VehicleSimulatorView.class.getResourceAsStream(imageName)));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    // Returns the id of the newly created VehicleRepresentation
    private int addVehicleRepresentation(int x, int y, String imageName) {
        if(!images.containsKey(imageName)) {
            throw new IllegalArgumentException("image \"" + imageName + "\" does not exist");
        }

        vehicleRepresentations.add(new VehicleRepresentation(x, y, imageName));
        return vehicleRepresentations.size() - 1;
    }

    public void notifyMove(int id, int x, int y) {
        vehicleRepresentations.get(id).setPosition(x, y);
    }

    public void notifyAdd(String imageName, int x, int y) {
        addVehicleRepresentation(x, y, imageName);
    }

    public void notifyRemove(int id) {
        vehicleRepresentations.remove(vehicleRepresentations.size() - 1);
    }

    // This method is called each time the panel updates/refreshes/repaints itself
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(VehicleRepresentation vehicleRepresentation : vehicleRepresentations) {
            // see javadoc for more info on the parameters
            BufferedImage image = images.get(vehicleRepresentation.getImageName());
            if(image == null) {
                throw new IllegalArgumentException("found non-existing image ???");
            }

            g.drawImage(image, vehicleRepresentation.getX(), vehicleRepresentation.getY(), null);
        }
    }
}
