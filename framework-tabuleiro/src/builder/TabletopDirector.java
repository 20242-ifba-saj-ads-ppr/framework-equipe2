package builder;

import flyweight.TabletopFlyweightConcreteCreator;

public class TabletopDirector {
    private AbstractTabletopBuilder builder;

    public TabletopDirector(AbstractTabletopBuilder builder) {
        this.builder = builder;
    }

    public TabletopProduct construct(int width, int height, TabletopFlyweightConcreteCreator tileFactory) {
        builder.buildArea(width, height);
        builder.buildTiles(tileFactory);
        builder.buildExtras();
        return builder.getResult();
    }
}