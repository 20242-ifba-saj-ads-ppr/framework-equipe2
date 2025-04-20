package abstractfactory;

import context.Peca;
import context.PlayerSide;
import context.Position;
import builder.TabletopProduct;
import observer.TabletopSubject;
import strategy.MovimentoStrategy;

public class NullPiece extends Peca {
    public NullPiece(Position pos) {
        super("Peça Inválida", PlayerSide.WHITE, new MovimentoStrategy() {
            @Override
            public boolean mover(Peca peca, TabletopProduct board,
                                 int ox, int oy, int dx, int dy,
                                 TabletopSubject subject) {
                return false;
            }
        }, pos);
    }

    @Override
    public String getNome() {
        return "Peça Inválida";
    }
}
