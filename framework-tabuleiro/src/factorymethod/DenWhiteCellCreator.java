package factorymethod;

import context.Position;

public class DenWhiteCellCreator extends CellCreator {
    @Override
    protected CellAbstractProduct factoryMethod(Position pos) {
        return new DenCell(pos, true);
    }
}