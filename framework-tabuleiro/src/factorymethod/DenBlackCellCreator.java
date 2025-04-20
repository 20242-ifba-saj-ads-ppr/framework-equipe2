package factorymethod;

import context.Position;

public class DenBlackCellCreator extends CellCreator {
    @Override
    protected CellAbstractProduct factoryMethod(Position pos) {
        return new DenCell(pos, false);
    }
}