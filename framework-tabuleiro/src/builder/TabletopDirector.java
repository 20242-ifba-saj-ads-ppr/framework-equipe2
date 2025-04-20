package builder;

import factorymethod.CellCreator;
import flyweight.TabletopFlyweightFactory;
import abstractfactory.SelvaPieceFactory;

public class TabletopDirector {
    private final TabletopBuilder builder;

    public TabletopDirector(TabletopBuilder builder) {
        this.builder = builder;
    }

    public TabletopProduct construct(int width,
                                     int height,
                                     CellCreator cellFactory,
                                     TabletopFlyweightFactory flyFactory,
                                     SelvaPieceFactory pieceFactory) {
        return builder
                .withDimensions(width, height)
                .buildCells(cellFactory, flyFactory)
                .buildTiles(flyFactory)
                .buildPieces(pieceFactory)
                .getResult();
    }
}
