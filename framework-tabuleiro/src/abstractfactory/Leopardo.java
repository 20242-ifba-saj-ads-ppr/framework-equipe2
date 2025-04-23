package abstractfactory;


import context.Peca;
import context.PlayerSide;
import context.Position;
import strategy.MovimentoStrategy;

public class Leopardo extends Peca {
    public Leopardo(PlayerSide side, Position start, MovimentoStrategy strat) {
        super("Leopardo", side, strat, start);
    }
}