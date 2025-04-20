

import flyweight.TabletopFlyweightProduct;

public class TabletopLeaf implements TabletopComponent {
    private TabletopFlyweightProduct flyweight;
    private int x, y;

    public TabletopLeaf(TabletopFlyweightProduct flyweight, int x, int y) {
        this.flyweight = flyweight;
        this.x = x;
        this.y = y;
    }

    @Override
    public String operation() {
        return flyweight.operation("x:" + x + ",y:" + y);
    }
}
