package builder;

import java.util.List;
import composite.TabletopComponent;

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
}
