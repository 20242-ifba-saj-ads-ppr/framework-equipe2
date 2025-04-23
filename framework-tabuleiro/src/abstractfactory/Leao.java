package abstractfactory;


import context.Peca;
import context.PlayerSide;
import context.Position;
import strategy.MovimentoStrategy;

public class Leao extends Peca {
    public Leao(PlayerSide side, Position start, MovimentoStrategy strat) {
        super("Leão", side, strat, start);
    }
}