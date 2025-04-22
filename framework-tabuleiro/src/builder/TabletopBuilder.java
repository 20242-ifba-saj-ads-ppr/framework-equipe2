package builder;

import context.Peca;
import factorymethod.CellCreator;
import factorymethod.CellAbstractProduct;
import flyweight.TabletopFlyweightFactory;
import abstractfactory.SelvaPieceFactory;
import composite.TabletopComponent;

import java.util.ArrayList;
import java.util.List;


public abstract class TabletopBuilder {
    protected int width, height;
    protected CellAbstractProduct[][] cells;
    protected List<TabletopComponent> tiles = new ArrayList<>();
    protected List<Peca> pieces = new ArrayList<>();

    /**
     * Define dimensões e inicializa a matriz de células.
     */

    public TabletopBuilder withDimensions(int width, int height) {
        this.width = width;
        this.height = height;
        this.cells = new CellAbstractProduct[height][width];
        return this;
    }

    /** Preenche cada célula com fábrica de células. */
    
    public abstract TabletopBuilder buildCells(CellCreator cellFactory,
                                              TabletopFlyweightFactory flyFactory);

    /** Cria componentes visuais (tiles) via composite + flyweight. */
    public abstract TabletopBuilder buildTiles(TabletopFlyweightFactory flyFactory);

    /** Posiciona peças iniciais via Abstract Factory. */
    public abstract TabletopBuilder buildPieces(SelvaPieceFactory pieceFactory);

    /** Retorna o produto final (tabuleiro montado). */
    public TabletopProduct getResult() {
        return new TabletopProduct(width, height, cells, tiles, pieces);
    }
}