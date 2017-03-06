package mvc;

/**
 * Created by Dmitry on 19.02.2017.
 */
public class TableUpdateEvent {
    private double oldX;
    private double newX;

    public TableUpdateEvent(double oldX, double newX) {
        this.oldX = oldX;
        this.newX = newX;
    }

    public double getOldX() {
        return oldX;
    }

    public double getNewX() {
        return newX;
    }
}
