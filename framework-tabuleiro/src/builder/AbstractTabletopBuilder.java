package builder;

import java.util.ArrayList;
import java.util.List;

import composite.TabletopComponent;


public abstract class AbstractTabletopBuilder {
    protected int x, y;
    protected List<TabletopComponent> tiles = new ArrayList<>();

    public abstract void buildArea(int width, int height);
    public abstract void buildTiles(flyweight.TabletopFlyweightConcreteCreator flyFactory);
    public abstract void buildExtras(flyweight.TabletopFlyweightConcreteCreator flyFactory);
    public abstract TabletopProduct getResult();
}
