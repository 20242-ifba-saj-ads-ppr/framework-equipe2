package abstractfactory;




import context.Peca;
import context.PlayerSide;

/**
 * Define a família de criação de peças do JogoSelva.
 */
public interface SelvaPieceFactory {
    Peca createLeao(PlayerSide side);
    Peca createTigre(PlayerSide side);
    Peca createElefante(PlayerSide side);
    // opcional: outros createXxx()

    /**
     * Gatilho genérico: cria pela string do tipo.
     * Retorna NullPiece se o tipo não existir.
     */
    default Peca create(String tipo, PlayerSide side) {
        switch (tipo.toLowerCase()) {
            case "leão":     return createLeao(side);
            case "tigre":    return createTigre(side);
            case "elefante": return createElefante(side);
            default:         return new NullPiece();
        }
    }
}
