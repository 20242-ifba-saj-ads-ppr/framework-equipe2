package observer;
// Observer Pattern - GOF
// Sujeito concreto que mantém estado
import java.util.ArrayList;
import java.util.List;

public class TabletopConcreteSubject extends TabletopSubject {
    private List<TabletopObserver> observers = new ArrayList<>();
    private String state;

    public TabletopConcreteSubject(String initialState) {
        this.state = initialState;
    }

    public void setState(String newState) {
        this.state = newState;
        notifyObservers(this.state);
    }

    @Override
    public void attach(TabletopObserver observer) {
        observers.add(observer);
    }

    @Override
    public void detach(TabletopObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String data) {
        for (TabletopObserver observer : observers) {
            observer.update(data);
        }
    }
}
