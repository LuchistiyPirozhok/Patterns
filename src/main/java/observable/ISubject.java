package observable;

/**
 * Created by Dmitry on 18.02.2017.
 */
public interface ISubject {
    void attach(IObserver observer);
    void detach(IObserver observer);
    void notifyObservers();
}
