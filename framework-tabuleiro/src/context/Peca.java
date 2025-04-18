package context;

import builder.TabletopProduct;
import observer.TabletopSubject;
import strategy.MovimentoStrategy;
import state.PecaState;
import state.NormalState;

/**
 * Representa uma peça do jogo, agora associada a um lado (WHITE ou BLACK).
 */
public class Peca {
    private String nome;
    private PlayerSide side;                  // novo campo
    private MovimentoStrategy movimentoStrategy;
    private PecaState state;

    /**
     * Construtor atualizado: nome, lado e estratégia de movimento.
     */
    public Peca(String nome, PlayerSide side, MovimentoStrategy movimentoStrategy) {
        this.nome = nome;
        this.side = side;
        this.movimentoStrategy = movimentoStrategy;
        this.state = new NormalState();
    }

    public String getNome() {
        return nome;
    }

    public PlayerSide getSide() {
        return side;
    }

    public void setMovimentoStrategy(MovimentoStrategy movimentoStrategy) {
        this.movimentoStrategy = movimentoStrategy;
    }

    public MovimentoStrategy getMovimentoStrategy() {
        return movimentoStrategy;
    }

    public void setState(PecaState state) {
        this.state = state;
    }

    public PecaState getState() {
        return state;
    }

    public boolean mover(TabletopProduct board,
                         int origemX, int origemY,
                         int destinoX, int destinoY,
                         TabletopSubject subject) {
        return state.mover(this, board, origemX, origemY, destinoX, destinoY, subject);
    }
}
