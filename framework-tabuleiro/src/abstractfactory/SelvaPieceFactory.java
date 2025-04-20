package abstractfactory;

import context.Peca;
import context.PlayerSide;
import context.Position;
import strategy.MovimentoStrategy;

public abstract class SelvaPieceFactory {
    protected Peca createPiece(String nome, PlayerSide side, MovimentoStrategy strat, Position start) {
        return new Peca(nome, side, strat, start);
    }

    public Peca create(String nome, PlayerSide side) {
    return switch (nome.toLowerCase()) {
        case "elefante" -> createElefante(side);
        case "leão", "leao" -> createLeao(side);
        case "tigre" -> createTigre(side);
        case "leopardo" -> createLeopardo(side);
        case "cão", "cao" -> createCao(side);
        case "lobo" -> createLobo(side);
        case "gato" -> createGato(side);
        case "rato" -> createRato(side);
        default -> throw new IllegalArgumentException("Peça desconhecida: " + nome);
    };
}

    public abstract Peca createElefante(PlayerSide side);
    public abstract Peca createLeao(PlayerSide side);
    public abstract Peca createTigre(PlayerSide side);
    public abstract Peca createLeopardo(PlayerSide side);
    public abstract Peca createCao(PlayerSide side);
    public abstract Peca createLobo(PlayerSide side);
    public abstract Peca createGato(PlayerSide side);
    public abstract Peca createRato(PlayerSide side);
}