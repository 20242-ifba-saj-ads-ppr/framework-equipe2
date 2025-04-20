package builder;

import context.Peca;
import context.Position;
import enums.CellType;
import factorymethod.CellAbstractProduct;
import composite.TabletopComponent;

import java.util.List;

/**
 * Produto final do Builder, agora com métodos de acesso e mutação
 * necessários para implementar as estratégias de movimento.
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
}
