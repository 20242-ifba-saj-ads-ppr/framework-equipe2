package builder;

import factorymethod.CellCreator;
import factorymethod.CellAbstractProduct;
import flyweight.TabletopFlyweightFactory;
import composite.TabletopComposite;
import composite.TabletopLeaf;
import context.Position;
import context.Peca;
import abstractfactory.SelvaPieceFactory;

/**
 * ConcreteBuilder genérico para montagem de um tabuleiro.
 * Usa Factory Method para criação de células, Flyweight para renderização de tiles,
 * e, opcionalmente, Abstract Factory para posicionamento de peças.
 */
public class TabletopConcreteBuilder extends TabletopBuilder {

    @Override
    public TabletopBuilder buildCells(CellCreator cellFactory,
                                      TabletopFlyweightFactory flyFactory) {
        // Preenche a matriz de células usando a fábrica de células
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Position pos = new Position(x, y);
                CellAbstractProduct cell = cellFactory.createCell(pos);
                cells[y][x] = cell;
            }
        }
        return this;
    }

    @Override
    public TabletopBuilder buildTiles(TabletopFlyweightFactory flyFactory) {
        // Cria o composite que representa todo o tabuleiro
        TabletopComposite boardComposite = new TabletopComposite("Board");
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                // Renderiza com Flyweight e cria leaf para composição
                cells[y][x].render(flyFactory);
                boardComposite.add(
                    new TabletopLeaf(
                        flyFactory.getFlyweight(cells[y][x].getType().name()),
                        x, y
                    )
                );
            }
        }
        tiles.add(boardComposite);
        return this;
    }

    @Override
    public TabletopBuilder buildPieces(SelvaPieceFactory pieceFactory) {
        
        return this;
    }
}
