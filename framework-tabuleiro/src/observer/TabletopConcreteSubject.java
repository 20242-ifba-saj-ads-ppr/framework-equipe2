package observer;

public class TabletopConcreteSubject extends TabletopSubject {
    private String state;

    public TabletopConcreteSubject(String initialState) {
        this.state = initialState;
    }
    
    @Override
    public String getState() {
        return state;
    }

    public void setState(String newState) {
        this.state = newState;
        notifyObservers(this.state);
    }
}
