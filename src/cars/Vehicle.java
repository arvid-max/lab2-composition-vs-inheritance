package cars;

import java.awt.*;
import java.awt.geom.Point2D;

public abstract class Vehicle implements Movable {
    private static final double TIME_INTERVAL_LENGTH = 0.5;
    private static final double ENGINE_POWER_RESCALE_AMOUNT = 0.01;

    private int nrDoors; // Number of doors on the car
    private double enginePower; // Engine power of the car
    private Color color; // Color of the car
    private String modelName; // The car model name

    // State of motion
    private Point2D.Double position;
    private Direction direction;

    protected double currentSpeed; // The current speed of the car
    private boolean canDrive;
    protected Vehicle(int nrDoors, double enginePower, Color color, String modelName) {
        this.nrDoors = nrDoors;
        this.enginePower = enginePower;
        this.color = color;
        this.modelName = modelName;
        this.position = new Point2D.Double();
        this.direction = Direction.EAST;
        canDrive = true;

        stopEngine();
    }

    public String getModelName() {
        return modelName;
    }

    public void setCanDrive(boolean b) {
        this.canDrive = b;
    }

    public int getNrDoors(){
        return nrDoors;
    }

    public double getEnginePower(){
        return enginePower;
    }

    public double getCurrentSpeed(){
        return currentSpeed;
    }

    public Color getColor(){
        return color;
    }

    public double getX() {
        return position.x;
    }

    public double getY() {
        return position.y;
    }

    public void setPosition(double x, double y) {
        position.x = x;
        position.y = y;
    }
    public Direction getDirection() {
        return direction;
    }

    public void setColor(Color clr){
        color = clr;
    }

    public void startEngine(){
        if (!canDrive) return;
        currentSpeed = 0.1;
    }

    public void stopEngine(){
        currentSpeed = 0;
    }

    protected double baseSpeedFactor() {
        return enginePower * ENGINE_POWER_RESCALE_AMOUNT;
    }

    protected abstract double speedFactor();

    protected void incrementSpeed(double amount) {
        currentSpeed = Math.min(getCurrentSpeed() + speedFactor() * amount, enginePower);
    }

    protected void decrementSpeed(double amount) {
        currentSpeed = Math.max(getCurrentSpeed() - speedFactor() * amount, 0);
    }

    protected void checkAmountArg(double amount) {
        if (amount < 0 || amount > 1) {
            throw new IllegalArgumentException("amount must be between 0 and 1.");
        }
    }
    public void gas(double amount){
        if (!canDrive) return;
        checkAmountArg(amount);
        incrementSpeed(amount);
    }
    public void brake(double amount){
        checkAmountArg(amount);
        decrementSpeed(amount);
    }

    public void move() {
        if (!canDrive) return;
        switch (direction) {
            case NORTH:
                position.y += currentSpeed;
                break;
            case EAST:
                position.x += currentSpeed;
                break;
            case SOUTH:
                position.y -= currentSpeed;
                break;
            case WEST:
                position.x -= currentSpeed;
                break;
        }
    }

    public void turnLeft() {
        direction = direction.rotateCounterClockwise();
    }

    public void turnRight() {
        direction = direction.rotateClockwise();
    }
}
