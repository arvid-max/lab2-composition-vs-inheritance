package cars;

import java.awt.*;

public class VehicleRepresentation {
    private Point pos;
    private int imageIndex;

    public VehicleRepresentation(int posX, int posY, int imageIndex) {
        this.pos = new Point(posX, posY);
        this.imageIndex = imageIndex;
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
    public int getImageIndex() {
        return imageIndex;
    }
}
