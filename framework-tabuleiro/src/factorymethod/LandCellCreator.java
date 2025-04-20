package factorymethod;

import context.Position;

public class LandCellCreator extends CellCreator {
  

    @Override
    protected CellAbstractProduct factoryMethod(Position pos) {
        return new LandCell(pos);
    }
}