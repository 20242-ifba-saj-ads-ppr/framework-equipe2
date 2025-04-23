package abstractfactory;

import context.Peca;
import context.PlayerSide;
import context.Position;
import strategy.MovimentoStrategy;

public class Rato extends Peca {
    public Rato(PlayerSide side, Position start, MovimentoStrategy strat) {
        super("Rato", side, strat, start);
    }
}