package prototype;
// Prototype Pattern - GOF
// Implementação concreta do protótipo

import builder.TabletopProduct;

public class TabletopConcretePrototype implements TabletopPrototype {
    private TabletopProduct product;

    public TabletopConcretePrototype(TabletopProduct product) {
        this.product = product;
    }

    @Override
    public TabletopPrototype clonePrototype() {
        return new TabletopConcretePrototype(product); // shallow copy
    }

    public TabletopProduct getProduct() {
        return product;
    }
}
