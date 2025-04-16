package observer;

import java.util.ArrayList;
import java.util.List;

public abstract class TabletopSubject {
    protected List<TabletopObserver> observers = new ArrayList<>();

    public void attach(TabletopObserver observer) {
        if(observer != null && !observers.contains(observer)) {
            observers.add(observer);
        }
    }

    public void detach(TabletopObserver observer) {
        observers.remove(observer);
    }
    
    public void notifyObservers(String data) {
        List<TabletopObserver> observersCopy = new ArrayList<>(observers);
        for (TabletopObserver observer : observersCopy) {
            observer.update(data);
        }
    }
    
    public abstract String getState();
}
