package abstractfactory;


import context.Peca;
import context.PlayerSide;
import strategy.MovimentoStrategy;
import builder.TabletopProduct;
import observer.TabletopSubject;

/**
 * NullPiece: não faz nada e evita checagens de null.
 */
public class NullPiece extends Peca {
    public NullPiece() {
        super("Peça Inválida", PlayerSide.WHITE, new MovimentoStrategy() {
            @Override
            public boolean mover(Object peca, TabletopProduct board,
                                 int ox, int oy, int dx, int dy,
                                 TabletopSubject subject) {
                // movimento sempre falha
                return false;
            }
        });
    }

    @Override
    public String getNome() {
        return "Peça Inválida";
    }
}
