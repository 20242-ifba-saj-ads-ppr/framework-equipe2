package strategy;

import builder.TabletopProduct;
import context.Peca;
import observer.TabletopSubject;

public class LeaoMovimentoStrategy implements MovimentoStrategy {
    @Override
    public boolean mover(Peca peca,
                         TabletopProduct board,
                         int ox, int oy, int dx, int dy,
                         TabletopSubject subject) {
        if (ox == dx && isRiverJump(board, ox, oy, dx, dy)) {
            return jump(board, peca, ox, oy, dx, dy);
        }
        if (oy == dy && isRiverJump(board, ox, oy, dx, dy)) {
            return jump(board, peca, ox, oy, dx, dy);
        }
        return new ElefanteMovimentoStrategy().mover(peca, board, ox, oy, dx, dy, subject);
    }

    private boolean isRiverJump(TabletopProduct board, int ox, int oy, int dx, int dy) {
        return Math.abs(ox - dx + oy - dy) > 1; 
    }

    private boolean jump(TabletopProduct board, Peca peca,
                         int ox, int oy, int dx, int dy) {
        Peca alvo = board.getPieceAt(dx, dy);
        if (alvo != null && alvo.getSide() == peca.getSide()) return false;
        board.movePiece(peca, ox, oy, dx, dy);
        if (alvo != null) board.removePiece(alvo);
        return true;
    }
}