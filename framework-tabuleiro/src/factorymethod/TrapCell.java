package factorymethod;

import context.Position;
import enums.CellType;

public class TrapCell extends CellAbstractProduct {
    public TrapCell(Position p, boolean whiteSide) {
        super(p, whiteSide? CellType.TRAP_WHITE : CellType.TRAP_BLACK);
    }
    @Override
    public void render(flyweight.TabletopFlyweightFactory fw) {
        String key = (type == CellType.TRAP_WHITE? "ArmadilhaBranca" : "ArmadilhaPreta");
        System.out.println(fw.getFlyweight(key).operation("x:"+pos.row+",y:"+pos.col));
    }
}