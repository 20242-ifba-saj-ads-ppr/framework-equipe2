package abstractfactory;

import context.Peca;
import context.PlayerSide;
import context.Position;
import strategy.MovimentoStrategy;

public class Lobo extends Peca {
    public Lobo(PlayerSide side, Position start, MovimentoStrategy strat) {
        super("Lobo", side, strat, start);
    }
}