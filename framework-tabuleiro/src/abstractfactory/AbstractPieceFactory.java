package abstractfactory;

import context.Peca;
import context.PlayerSide;
import context.Position;
import strategy.MovimentoStrategy;

public abstract class AbstractPieceFactory implements PieceFactory {

    protected Peca createPiece(String nome,
                               PlayerSide side,
                               MovimentoStrategy strat,
                               Position pos) {
        return new Peca(nome, side, strat, pos);
    }
}
