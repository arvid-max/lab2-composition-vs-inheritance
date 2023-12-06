package cars;

import javax.swing.*;
import java.awt.*;

public class SimulatorWindow extends JFrame {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 800;

    private JPanel view;
    private JPanel controllerView;
    private String title;

    public SimulatorWindow(String title, JPanel view, JPanel controllerView){
        this.view = view;
        this.controllerView = controllerView;
        this.title = title;

        this.setTitle(title);
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));

        this.add(view);
        this.add(controllerView);

        // Make the frame pack all it's components by respecting the sizes if possible.
        this.pack();

        // Get the computer screen resolution
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        // Center the frame
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        // Make the frame visible
        this.setVisible(true);
        // Make sure the frame exits when "x" is pressed
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
