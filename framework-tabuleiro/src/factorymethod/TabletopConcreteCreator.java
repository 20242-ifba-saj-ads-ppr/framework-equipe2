package factorymethod;


public class TabletopConcreteCreator extends TabletopCreator {
    private String name;

    public TabletopConcreteCreator(String name) {
        this.name = name;
    }

    @Override
    public TabletopAbstractProduct factoryMethod() {
        return new TabletopConcreteProduct(name);
    }
}
