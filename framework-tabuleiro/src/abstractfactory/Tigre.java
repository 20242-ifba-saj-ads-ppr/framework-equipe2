package abstractfactory;

import context.Peca;
import context.PlayerSide;
import context.Position;
import strategy.MovimentoStrategy;

public class Tigre extends Peca {
    public Tigre(PlayerSide side, Position start, MovimentoStrategy strat) {
        super("Tigre", side, strat, start);
    }
}