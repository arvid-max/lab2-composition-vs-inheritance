package cars;

public interface Observer {
    void notifyMove(int id, int x, int y);
    void notifyAdd(String carName, int x, int y);
    void notifyRemove(int id);
}
