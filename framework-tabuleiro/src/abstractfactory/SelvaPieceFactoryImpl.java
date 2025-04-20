package abstractfactory;

import context.Peca;
import context.PlayerSide;
import context.Position;
import strategy.*;

public class SelvaPieceFactoryImpl extends SelvaPieceFactory {

    @Override
    public Peca createElefante(PlayerSide side) {
        Position pos = (side == PlayerSide.WHITE) ? new Position(6, 2) : new Position(0, 6);
        return createPiece("Elefante", side, new ElefanteMovimentoStrategy(), pos);
    }

    @Override
    public Peca createLeao(PlayerSide side) {
        Position pos = (side == PlayerSide.WHITE) ? new Position(7, 3) : new Position(0, 0);
        return createPiece("Leão", side, new LeaoMovimentoStrategy(), pos);
    }

    @Override
    public Peca createTigre(PlayerSide side) {
        Position pos = (side == PlayerSide.WHITE) ? new Position(6, 0) : new Position(0, 2);
        return createPiece("Tigre", side, new TigreMovimentoStrategy(), pos);
    }

    @Override
    public Peca createLeopardo(PlayerSide side) {
        Position pos = (side == PlayerSide.WHITE) ? new Position(6, 4) : new Position(2, 6);
        return createPiece("Leopardo", side, new LeopardoMovimentoStrategy(), pos);
    }

    @Override
    public Peca createCao(PlayerSide side) {
        Position pos = (side == PlayerSide.WHITE) ? new Position(5, 1) : new Position(1, 5);
        return createPiece("Cão", side, new CaoMovimentoStrategy(), pos);
    }

    @Override
    public Peca createLobo(PlayerSide side) {
        Position pos = (side == PlayerSide.WHITE) ? new Position(5, 5) : new Position(1, 1);
        return createPiece("Lobo", side, new LoboMovimentoStrategy(), pos);
    }

    @Override
    public Peca createGato(PlayerSide side) {
        Position pos = (side == PlayerSide.WHITE) ? new Position(7, 1) : new Position(1, 7);
        return createPiece("Gato", side, new GatoMovimentoStrategy(), pos);
    }

    @Override
    public Peca createRato(PlayerSide side) {
        Position pos = (side == PlayerSide.WHITE) ? new Position(6, 6) : new Position(2, 0);
        return createPiece("Rato", side, new RatoMovimentoStrategy(), pos);
    }
}
