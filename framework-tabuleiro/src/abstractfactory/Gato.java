package abstractfactory;

import context.Peca;
import context.PlayerSide;
import context.Position;
import strategy.MovimentoStrategy;

public class Gato extends Peca {
    public Gato(PlayerSide side, Position start, MovimentoStrategy strat) {
        super("Gato", side, strat, start);
    }
}