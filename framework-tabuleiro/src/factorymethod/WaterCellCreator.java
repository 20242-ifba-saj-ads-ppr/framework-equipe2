package factorymethod;

import context.Position;

public class WaterCellCreator extends CellCreator {
    @Override
    protected CellAbstractProduct factoryMethod(Position pos) {
        return new WaterCell(pos);
    }
}