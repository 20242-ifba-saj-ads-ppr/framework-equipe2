package observer;
// Observer Pattern - GOF
// Interface do sujeito observado
public abstract class TabletopSubject {
    public abstract void attach(TabletopObserver observer);
    public abstract void detach(TabletopObserver observer);
    public abstract void notifyObservers(String data);
}
