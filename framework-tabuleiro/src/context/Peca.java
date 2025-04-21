package context;

import builder.TabletopProduct;
import observer.TabletopSubject;
import strategy.MovimentoStrategy;
import state.PecaState;
import state.NormalState;

/**
 * Representa uma peça do jogo Selva, com estado e estratégia de movimento.
 */
public class Peca {
    private String nome;
    private PlayerSide side;
    private MovimentoStrategy movimentoStrategy;
    private PecaState state;
    private Position position;

    /**
     * Construtor completo: define nome, lado, estratégia e posição inicial.
     */
    public Peca(String nome,
                PlayerSide side,
                MovimentoStrategy movimentoStrategy,
                Position start) {
        this.nome = nome;
        this.side = side;
        this.movimentoStrategy = movimentoStrategy;
        this.state = new NormalState();
        this.position = start;
    }

    public String getNome() {
        return nome;
    }

    public PlayerSide getSide() {
        return side;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position newPos) {
        this.position = newPos;
    }

    public MovimentoStrategy getMovimentoStrategy() {
        return movimentoStrategy;
    }

    public void setMovimentoStrategy(MovimentoStrategy movimentoStrategy) {
        this.movimentoStrategy = movimentoStrategy;
    }

    public PecaState getState() {
        return state;
    }

    public void setState(PecaState state) {
        this.state = state;
    }

    /**
     * Executa o movimento conforme o estado atual (NormalState delega à estratégia).
     */
    public boolean mover(TabletopProduct board,
                         int origemX, int origemY,
                         int destinoX, int destinoY,
                         TabletopSubject subject) {
        return state.mover(this, board, origemX, origemY, destinoX, destinoY, subject);
    }

    public Peca deepClone() {
        return new Peca(
            this.nome,
            this.side,
            this.movimentoStrategy,               // compartilha a mesma estratégia
            new Position(position.row, position.col)
        );
    }
}
