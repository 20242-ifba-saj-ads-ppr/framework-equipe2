package strategy;

import builder.TabletopProduct;
import context.Peca;
import enums.CellType;
import observer.TabletopSubject;

public class RatoMovimentoStrategy implements MovimentoStrategy {
    @Override
    public boolean mover(Peca peca,
                         TabletopProduct board,
                         int ox, int oy, int dx, int dy,
                         TabletopSubject subject) {
        if (!board.isWithinBounds(dx, dy) || Math.abs(ox - dx) + Math.abs(oy - dy) != 1)
            return false;

        CellType origemType = board.getCellType(ox, oy);
        CellType destinoType = board.getCellType(dx, dy);

        Peca alvo = board.getPieceAt(dx, dy);
        if (alvo != null && alvo.getSide() == peca.getSide()) return false;

        
        if (origemType == CellType.WATER && destinoType == CellType.LAND &&
            alvo != null && alvo.getNome().equals("Elefante")) return false;

        board.movePiece(peca, ox, oy, dx, dy);
        if (alvo != null) board.removePiece(alvo);
        return true;
    }
}
