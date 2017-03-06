package observable;


import java.util.HashSet;
import java.util.Set;


/**
 * Created by Dmitry on 18.02.2017.
 */
public class CoordinatesSubject implements ISubject {
    private Set<IObserver> observers;
    private int x,y;

    public CoordinatesSubject(){
        this.observers=new HashSet<>();
    }

    @Override
    public void attach(IObserver observer) {
        this.observers.add(observer);
    }

    @Override
    public void detach(IObserver observer) {
        this.observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        observers.forEach(o->o.update(x,y));
    }

    public void setCoords(int x, int y){
        this.x=x;
        this.y=y;
        notifyObservers();
    }
}
