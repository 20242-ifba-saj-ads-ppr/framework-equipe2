package factorymethod;

import context.Position;

public class TrapBlackCellCreator extends CellCreator {
    @Override
    protected CellAbstractProduct factoryMethod(Position pos) {
        return new TrapCell(pos, false);
    }
}