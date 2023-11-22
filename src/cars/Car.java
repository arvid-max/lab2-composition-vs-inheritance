package cars;

import java.awt.Color;
import java.awt.geom.Point2D;

public abstract class Car extends Vehicle implements Movable {

    protected Car(int nrDoors, double enginePower, Color color, String modelName) {
        super(nrDoors, enginePower, color, modelName);
    }

}