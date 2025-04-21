package factorymethod;

import context.Position;
import enums.CellType;

public class WaterCell extends CellAbstractProduct {
    public WaterCell(Position p) { super(p, CellType.WATER); }
    @Override
    public void render(flyweight.TabletopFlyweightFactory fw) {
        System.out.println(fw.getFlyweight("Água").operation("x:"+pos.row+",y:"+pos.col));
    }
    @Override
    public WaterCell clone() {
        return new WaterCell(new Position(pos.row, pos.col));

    }
}