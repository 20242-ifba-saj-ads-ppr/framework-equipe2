package factorymethod;

import context.Position;
import enums.CellType;

public abstract class CellAbstractProduct {
    protected Position pos;
    protected CellType type;

    public CellAbstractProduct(Position pos, CellType type) {
        this.pos  = pos;
        this.type = type;
    }

    public Position getPosition() { return pos; }
    public CellType getType()       { return type; }

    /** Exibe a célula usando Flyweight para renderização */
    public abstract void render(flyweight.TabletopFlyweightFactory flyFactory);
    public abstract CellAbstractProduct clone();

}