package factorymethod;

import context.Position;
import enums.CellType;

public class LandCell extends CellAbstractProduct
 {
    public LandCell(Position p) { super(p, CellType.LAND); }
    @Override
    public void render(flyweight.TabletopFlyweightFactory fw) {
        System.out.println(fw.getFlyweight("Grama").operation("x:"+pos.row+",y:"+pos.col));
    }

    @Override
    public LandCell clone() {
        return new LandCell(new Position(pos.row, pos.col));
    }

}