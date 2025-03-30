package flyweight;
// Flyweight Pattern - GOF
// Implementação concreta do objeto compartilhado
public class TabletopFlyweightConcreteProduct extends TabletopFlyweightProduct {

    public TabletopFlyweightConcreteProduct(String name) {
        super(name);
    }

    @Override
    public String operation(String position) {
        return "Tile: " + name + " em " + position;
    }
}
