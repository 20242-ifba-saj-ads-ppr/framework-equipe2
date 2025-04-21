package factorymethod;

import context.Position;
import enums.CellType;

public class DenCell extends CellAbstractProduct {
    public DenCell(Position p, boolean whiteSide) {
        super(p, whiteSide? CellType.DEN_WHITE : CellType.DEN_BLACK);
    }
    @Override
    public void render(flyweight.TabletopFlyweightFactory fw) {
        String key = (type == CellType.DEN_WHITE? "TocaBranca" : "TocaPreta");
        System.out.println(fw.getFlyweight(key).operation("x:"+pos.row+",y:"+pos.col));
    }

    @Override
    public DenCell clone() {
        return new DenCell(new Position(pos.row, pos.col), type == CellType.DEN_WHITE);
    }
}

    
