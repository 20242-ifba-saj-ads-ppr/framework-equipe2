// Factory Pattern - GOF
// Criador abstrato
public abstract class TabletopCreator {
    public abstract TabletopAbstractProduct factoryMethod();

    public TabletopAbstractProduct createProduct() {
        return factoryMethod();
    }
}
