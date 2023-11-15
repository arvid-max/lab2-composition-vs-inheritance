import java.util.ArrayList;

import cars.Car;
import cars.Direction;
import cars.Saab95;
import cars.Volvo240;

public class Driving {
    public static void main(String[] args) {

        Saab95 saab = new Saab95();
        Volvo240 volvo = new Volvo240();

        saab.setTurboOn();

        ArrayList<Car> cars = new ArrayList<>(2);

        cars.add(saab);
        cars.add(volvo);
        
        // for (Car car : cars) {
        //     car.startEngine();
        //     System.out.printf("car: %s\n", car.toString());
        //     System.out.printf("dir: %s\n", car.getDirection());
        //     System.out.printf("speed: %.3f\n", car.getCurrentSpeed());
        //     System.out.printf("pos: (%.3f, %.3f)\n", car.getX(), car.getY());
        //     car.move();
        //     System.out.printf("pos: (%.3f, %.3f)\n", car.getX(), car.getY());
        //     car.turnRight();
        //     car.gas(1);
        //     car.move();
        //     System.out.printf("pos: (%.3f, %.3f)\n", car.getX(), car.getY());
        //     saab.gas(2);
        // }

        System.out.println(saab.getCurrentSpeed());

        System.out.printf("saab enginepower: %.3f\n", saab.getEnginePower());
        for (int i = 0; i < 100; i++) {
            saab.gas(1);
            System.out.printf("saab speed: %.2f\n", saab.getCurrentSpeed());

        }

        for (int i = 0; i < 200; i++) {
            saab.brake(1);
            System.out.printf("saab speed: %.2f\n", saab.getCurrentSpeed());
        }

    }

}
