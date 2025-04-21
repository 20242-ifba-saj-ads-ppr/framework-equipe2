package factorymethod;


// Creator abstrato

import context.Position;

public abstract class CellCreator {
    public final CellAbstractProduct createCell(Position pos) {
        CellAbstractProduct cell = factoryMethod(pos);
        return cell;
    }

    protected abstract CellAbstractProduct factoryMethod(Position pos);
}
