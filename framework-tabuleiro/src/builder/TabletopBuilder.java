package builder;
// Builder Pattern - GOF
// Interface do construtor
import java.util.List;

import composite.TabletopComponent;

public interface TabletopBuilder {
    TabletopBuilder setArea(int x, int y);
    TabletopBuilder setTiles(List<TabletopComponent> tiles);
    TabletopProduct getResult();
}
