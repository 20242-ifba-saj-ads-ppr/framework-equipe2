package builder;
// Builder Pattern - GOF
// Implementação concreta do construtor
import java.util.List;
import composite.TabletopComponent; 



public class TabletopConcreteBuilder implements TabletopBuilder {
    private int x, y;
    private List<TabletopComponent> tiles;

    @Override
    public TabletopBuilder setArea(int x, int y) {
        this.x = x;
        this.y = y;
        return this;
    }

    @Override
    public TabletopBuilder setTiles(List<TabletopComponent> tiles) {
        this.tiles = tiles;
        return this;
    }

    @Override
    public TabletopProduct getResult() {
        return new TabletopProduct(x, y, tiles);
    }
}
