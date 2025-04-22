package builder;

import context.Peca;
import context.Position;
import enums.CellType;
import factorymethod.CellAbstractProduct;
import composite.TabletopComponent;

import java.util.ArrayList;
import java.util.List;

/**
 * Produto final do Builder. Contém a grade de células, os componentes visuais (tiles) e as peças.
 * Implementa métodos utilitários para manipulação de peças e células.
 */
public class TabletopProduct {
    private final int width, height;
    private final CellAbstractProduct[][] cells;
    private final List<TabletopComponent> tiles;
    private final List<Peca> pieces;
    private final Peca[][] pieceGrid;

    public TabletopProduct(int width,
                           int height,
                           CellAbstractProduct[][] cells,
                           List<TabletopComponent> tiles,
                           List<Peca> pieces) {
        this.width = width;
        this.height = height;
        this.cells = cells;
        this.tiles = tiles;
        this.pieces = pieces;
        this.pieceGrid = new Peca[height][width];
        // popula a grade de peças (assume que Peca tem getPosition())
        for (Peca p : pieces) {
            Position pos = p.getPosition();
            pieceGrid[pos.row][pos.col] = p;
        }
    }

    // --- Métodos utilitários para as estratégias --- //

    public boolean isWithinBounds(int x, int y) {
        return x >= 0 && x < width && y >= 0 && y < height;
    }

    public CellType getCellType(int x, int y) {
        return cells[y][x].getType();
    }

    public Peca getPieceAt(int x, int y) {
        if (!isWithinBounds(x, y)) return null;
        return pieceGrid[y][x];
    }

    public boolean movePiece(Peca peca, int ox, int oy, int dx, int dy) {
        if (getPieceAt(ox, oy) != peca || !isWithinBounds(dx, dy))
            return false;
        pieceGrid[oy][ox] = null;
        pieceGrid[dy][dx] = peca;
        peca.setPosition(new Position(dx, dy));
        return true;
    }

    public boolean removePiece(Peca peca) {
        Position pos = peca.getPosition();
        if (getPieceAt(pos.row, pos.col) != peca)
            return false;
        pieceGrid[pos.row][pos.col] = null;
        return pieces.remove(peca);
    }

    public void restoreState(TabletopProduct state) {
        // Restaura a grade de peças
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                this.pieceGrid[y][x] = state.getPieceAt(x, y);
            }
        }
    
        // Restaura as posições das peças
        this.pieces.clear();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                Peca p = state.getPieceAt(x, y);
                if (p != null) {
                    this.pieces.add(p);
                }
            }
        }
    
    }

    // dentro de TabletopProduct

/** Retorna um clone profundo deste objeto */
public TabletopProduct deepClone() {
    // 1) Clona as células (supondo que CellAbstractProduct implemente clone())
    CellAbstractProduct[][] clonedCells = new CellAbstractProduct[height][width];
    for (int y = 0; y < height; y++) {
        for (int x = 0; x < width; x++) {
            clonedCells[y][x] = cells[y][x].clone();
        }
    }

    // 2) Shallow copy dos tiles (eles só referenciam flyweights imutáveis)
    List<TabletopComponent> clonedTiles = new ArrayList<>(tiles);

    // 3) Clona cada peça usando um método deepClone() em Peca
    List<Peca> clonedPieces = new ArrayList<>();
    for (Peca p : pieces) {
        clonedPieces.add(p.deepClone());
    }

    // 4) Cria e retorna o novo TabletopProduct
    return new TabletopProduct(width, height, clonedCells, clonedTiles, clonedPieces);
    }

    public List<TabletopComponent> getTiles() {
        return tiles;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public factorymethod.CellAbstractProduct getCellAt(int x, int y) {
        return cells[y][x];
    }
    
    
}
