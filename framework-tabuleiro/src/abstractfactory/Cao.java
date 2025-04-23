
package abstractfactory;

import context.Peca;
import context.PlayerSide;
import context.Position;
import strategy.MovimentoStrategy;

public class Cao extends Peca {
    public Cao(PlayerSide side, Position start, MovimentoStrategy strat) {
        super("Cão", side, strat, start);
    }
}