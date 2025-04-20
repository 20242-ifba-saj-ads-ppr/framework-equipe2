package strategy;

import builder.TabletopProduct;
import context.Peca;
import observer.TabletopSubject;

/**
 * Interface que define a estratégia de movimentação de uma peça.
 */
public interface MovimentoStrategy {
    boolean mover(Peca peca,
                  TabletopProduct board,
                  int origemX, int origemY,
                  int destinoX, int destinoY,
                  TabletopSubject subject);
}
