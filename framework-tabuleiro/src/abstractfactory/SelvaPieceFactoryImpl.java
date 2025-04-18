// 3) SelvaPieceFactoryImpl — Concrete Factory
package abstractfactory;

import context.Peca;
import context.PlayerSide;
import strategy.LeaoMovimentoStrategy;
import strategy.TigreMovimentoStrategy;
import strategy.ElefanteMovimentoStrategy;

/**
 * Fábrica concreta para o tema JogoSelva.
 */
public class SelvaPieceFactoryImpl implements SelvaPieceFactory {

    @Override
    public Peca createLeao(PlayerSide side) {
        return new Peca("Leão", side, new LeaoMovimentoStrategy());
    }

    @Override
    public Peca createTigre(PlayerSide side) {
        return new Peca("Tigre", side, new TigreMovimentoStrategy());
    }

    @Override
    public Peca createElefante(PlayerSide side) {
        return new Peca("Elefante", side, new ElefanteMovimentoStrategy());
    }
}
