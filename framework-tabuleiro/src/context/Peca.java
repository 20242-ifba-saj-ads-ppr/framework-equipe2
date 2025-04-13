package context; 

import builder.TabletopProduct;
import observer.TabletopSubject;
import strategy.MovimentoStrategy;
import state.PecaState;
import state.NormalState;

public class Peca {
    
    private String nome;
    private MovimentoStrategy movimentoStrategy;
    
    // Novo atributo representando o estado atual da peça
    private PecaState state;
    
    public Peca(String nome, MovimentoStrategy movimentoStrategy) {
        this.nome = nome;
        this.movimentoStrategy = movimentoStrategy;
        // Estado inicial: Normal
        this.state = new NormalState();
    }
    
    public void setMovimentoStrategy(MovimentoStrategy movimentoStrategy) {
        this.movimentoStrategy = movimentoStrategy;
    }
    
    // Getter para a estratégia de movimento (usado pelo estado Normal)
    public MovimentoStrategy getMovimentoStrategy() {
        return movimentoStrategy;
    }
    
    // Método para alterar o estado da peça
    public void setState(PecaState state) {
        this.state = state;
    }
    
    public PecaState getState() {
        return state;
    }
    
    // Método mover agora delega para o estado atual
    public boolean mover(TabletopProduct board,
                         int origemX, int origemY,
                         int destinoX, int destinoY,
                         TabletopSubject subject) {
        return state.mover(this, board, origemX, origemY, destinoX, destinoY, subject);
    }
    
    public String getNome() {
        return nome;
    }
}
