package cars;

import java.util.ArrayList;
import java.util.Random;

public class VehicleFactory {
    public Vehicle createVolvo(int x, int y) {
        Volvo240 volvo240 = new Volvo240();
        volvo240.setPosition(x,y);
        return volvo240;
    }
    public Vehicle createSaab(int x, int y) {
        Saab95 saab95 = new Saab95();
        saab95.setPosition(x,y);
        return saab95;
    }
    public Vehicle createScania(int x, int y) {
        Scania scania = new Scania();
        scania.setPosition(x,y);
        return scania;
    }
    public Vehicle createRandomVehicle() {
        Random random = new Random();
        int randomNumber = random.nextInt(3);
        int randomX = random.nextInt(700);
        int randomY = random.nextInt(560);
        return switch (randomNumber) {
            case 0 -> createVolvo(randomX, randomY);
            case 1 -> createSaab(randomX, randomY);
            case 2 -> createScania(randomX, randomY);
            default -> null;
        };
    }
    public ArrayList<String> getPossibleVehiclesNames(){
        ArrayList<String> possibleVehicleNames= new ArrayList<>();
        possibleVehicleNames.add("Saab95");
        possibleVehicleNames.add("Volvo240");
        possibleVehicleNames.add("Scania");
        return possibleVehicleNames;
    }
}
