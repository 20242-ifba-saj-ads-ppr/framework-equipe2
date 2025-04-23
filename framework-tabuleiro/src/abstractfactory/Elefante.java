package abstractfactory;

import context.Peca;
import context.PlayerSide;
import context.Position;
import strategy.MovimentoStrategy;

public class Elefante extends Peca {
    public Elefante(PlayerSide side, Position start, MovimentoStrategy strat) {
        super("Elefante", side, strat, start);
    }
}