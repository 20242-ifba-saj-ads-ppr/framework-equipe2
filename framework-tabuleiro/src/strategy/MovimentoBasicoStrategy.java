package strategy;

import builder.TabletopProduct;
import context.Peca;
import enums.CellType;
import observer.TabletopSubject;

public class MovimentoBasicoStrategy implements MovimentoStrategy {
    @Override
    public boolean mover(Peca peca,
                         TabletopProduct board,
                         int ox, int oy, int dx, int dy,
                         TabletopSubject subject) {
        if (!board.isWithinBounds(dx, dy) || Math.abs(ox - dx) + Math.abs(oy - dy) != 1)
            return false;
        if (board.getCellType(dx, dy) == CellType.WATER) return false;
        Peca alvo = board.getPieceAt(dx, dy);
        if (alvo != null) {
            if (alvo.getSide() == peca.getSide()) return false;
        }
        board.movePiece(peca, ox, oy, dx, dy);
        if (alvo != null) board.removePiece(alvo);
        return true;
    }
}