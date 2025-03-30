package flyweight;
// Flyweight Pattern - GOF
// Compartilha objetos similares para economizar memória
public abstract class TabletopFlyweightProduct {
    protected String name;

    public TabletopFlyweightProduct(String name) {
        this.name = name;
    }

    public abstract String operation(String position);
}
