package factorymethod;

import context.Position;

public class TrapWhiteCellCreator extends CellCreator {
    @Override
    protected CellAbstractProduct factoryMethod(Position pos) {
        return new TrapCell(pos, true);
    }
}