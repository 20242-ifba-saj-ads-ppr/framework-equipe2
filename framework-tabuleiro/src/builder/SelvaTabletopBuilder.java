package builder;

import abstractfactory.SelvaPieceFactory;
import composite.TabletopComposite;
import composite.TabletopLeaf;
import context.PlayerSide;
import context.Position;
import factorymethod.CellAbstractProduct;
import factorymethod.CellCreator;
import flyweight.TabletopFlyweightFactory;

public class SelvaTabletopBuilder extends TabletopBuilder {
    @Override
    public TabletopBuilder buildCells(CellCreator cellFactory,
                                      TabletopFlyweightFactory flyFactory) {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Position pos = new Position(x, y);
                // Usa o Factory Method para criar a célula (implementação define o tipo internamente)
                CellAbstractProduct cell = cellFactory.createCell(pos);
                cells[y][x] = cell;
            }
        }
        return this;
    }

    @Override
    public TabletopBuilder buildTiles(TabletopFlyweightFactory flyFactory) {
        TabletopComposite board = new TabletopComposite("Selva Board");
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // renderiza cada célula usando Flyweight e cria leaf para composição
                cells[y][x].render(flyFactory);
                board.add(new TabletopLeaf(
                    flyFactory.getFlyweight(cells[y][x].getType().name()), x, y));
            }
        }
        tiles.add(board);
        return this;
    }

    @Override
    public TabletopBuilder buildPieces(SelvaPieceFactory pieceFactory) {
        // Exemplo de posicionamento padrão das peças (WHITE/BLACK)
        pieces.add(pieceFactory.createElefante(PlayerSide.WHITE));
        pieces.add(pieceFactory.createLeao(PlayerSide.BLACK));
        // ... demais peças conforme regras do jogo
        return this;
    }
}