// Factory Pattern - GOF
// Produto concreto
public class TabletopConcreteProduct extends TabletopAbstractProduct {
    private String name;

    public TabletopConcreteProduct(String name) {
        this.name = name;
    }

    @Override
    public String describe() {
        return "ConcreteProduct: " + name;
    }

    public String getName() {
        return name;
    }
}
