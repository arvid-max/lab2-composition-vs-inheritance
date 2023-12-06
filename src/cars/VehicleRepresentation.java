package cars;

import java.awt.*;

public class VehicleRepresentation {
    private Point pos;
    private String imageName;

    public VehicleRepresentation(int posX, int posY, String imageName) {
        this.pos = new Point(posX, posY);
        this.imageName = imageName;
    }

    public int getX() {
        return pos.x;
    }

    public int getY() {
        return pos.y;
    }
    public void setPosition(int x, int y) {
        pos = new Point(x, y);
    }
    public String getImageName() {
        return imageName;
    }
    public String toString() {
        return "pos: " + pos.getX() + ", " + pos.getY() + " imageName: " + imageName;
    }
}
