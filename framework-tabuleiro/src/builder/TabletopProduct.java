package builder;

import java.util.ArrayList;
import java.util.List;
import composite.TabletopComponent;

import TabletopComponent;

public class TabletopProduct {
    private int x, y;
    private List<TabletopComponent> tiles;

    public TabletopProduct(int x, int y, List<TabletopComponent> tiles) {
        this.x = x;
        this.y = y;
        this.tiles = tiles;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public List<TabletopComponent> getTiles() { return tiles; }

    // Método para criar um clone profundo (Originator cria seu Memento)
    public TabletopProduct deepClone() {
        // shallow copy of x,y; reuse same components (assume imutáveis ou flyweights)
        return new TabletopProduct(x, y, new ArrayList<>(tiles));
    }

    // Restaura estado a partir de outro produto
    public void restoreState(TabletopProduct mementoState) {
        this.x = mementoState.x;
        this.y = mementoState.y;
        this.tiles.clear();
        this.tiles.addAll(mementoState.tiles);
    }
}
