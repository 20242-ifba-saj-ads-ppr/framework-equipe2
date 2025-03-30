package observer;
// Observer Pattern - GOF
// Observador concreto
public class TabletopConcreteObserver implements TabletopObserver {
    private String name;

    public TabletopConcreteObserver(String name) {
        this.name = name;
    }

    @Override
    public void update(String data) {
        System.out.println(name + " recebeu atualização: " + data);
    }
}
