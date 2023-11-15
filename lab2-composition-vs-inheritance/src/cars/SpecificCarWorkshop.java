package cars;

public class SpecificCarWorkshop<T extends Car> extends CarWorkshop<T> {
    public SpecificCarWorkshop(int carCapacity) {
        super(carCapacity);
    }
}
