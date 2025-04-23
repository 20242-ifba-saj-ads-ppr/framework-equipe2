package abstractfactory;

import context.Peca;
import context.PlayerSide;
import context.Position;


import strategy.ElefanteMovimentoStrategy;
import strategy.LeaoMovimentoStrategy;
import strategy.TigreMovimentoStrategy;
import strategy.LeopardoMovimentoStrategy;
import strategy.CaoMovimentoStrategy;
import strategy.LoboMovimentoStrategy;
import strategy.GatoMovimentoStrategy;
import strategy.RatoMovimentoStrategy;

public class SelvaPieceFactoryImpl extends SelvaPieceFactory {

    @Override
    public Peca createElefante(PlayerSide side) {
        Position pos = (side == PlayerSide.WHITE)
            ? new Position(6, 2)
            : new Position(0, 6);
        return new Elefante(side, pos, new ElefanteMovimentoStrategy());
    }

    @Override
    public Peca createLeao(PlayerSide side) {
        Position pos = (side == PlayerSide.WHITE)
            ? new Position(7, 3)
            : new Position(0, 0);
        return new Leao(side, pos, new LeaoMovimentoStrategy());
    }

    @Override
    public Peca createTigre(PlayerSide side) {
        Position pos = (side == PlayerSide.WHITE)
            ? new Position(6, 0)
            : new Position(0, 2);
        return new Tigre(side, pos, new TigreMovimentoStrategy());

        
    }

    @Override
    public Peca createLeopardo(PlayerSide side) {
        Position pos = (side == PlayerSide.WHITE)
            ? new Position(6, 4)
            : new Position(2, 6);
        return new Leopardo(side, pos, new LeopardoMovimentoStrategy());
    }

    @Override
    public Peca createCao(PlayerSide side) {
        Position pos = (side == PlayerSide.WHITE)
            ? new Position(5, 1)
            : new Position(1, 5);
        return new Cao(side, pos, new CaoMovimentoStrategy());
    }

    @Override
    public Peca createLobo(PlayerSide side) {
        Position pos = (side == PlayerSide.WHITE)
            ? new Position(5, 5)
            : new Position(1, 1);
        return new Lobo(side, pos, new LoboMovimentoStrategy());
    }

    @Override
    public Peca createGato(PlayerSide side) {
        Position pos = (side == PlayerSide.WHITE)
            ? new Position(7, 1)
            : new Position(1, 7);
        return new Gato(side, pos, new GatoMovimentoStrategy());
    }

    @Override
    public Peca createRato(PlayerSide side) {
        Position pos = (side == PlayerSide.WHITE)
            ? new Position(6, 6)
            : new Position(2, 0);
        return new Rato(side, pos, new RatoMovimentoStrategy());
    }
}
