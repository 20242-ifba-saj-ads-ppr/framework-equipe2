package prototype;

import builder.TabletopProduct;

public class TabletopConcretePrototype implements TabletopPrototype {
    private TabletopProduct product;

    public TabletopConcretePrototype(TabletopProduct product) {
        this.product = product;
    }

    @Override
    public TabletopPrototype clonePrototype() {
        // usa o deepClone() que acabamos de implementar
        TabletopProduct cloned = product.deepClone();
        return new TabletopConcretePrototype(cloned);
    }

    public TabletopProduct getProduct() {
        return product;
    }
}
