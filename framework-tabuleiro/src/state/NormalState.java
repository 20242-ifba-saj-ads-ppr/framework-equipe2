package state;

import builder.TabletopProduct;
import observer.TabletopSubject;
import context.Peca;

public class NormalState implements PecaState {

    @Override
    public boolean mover(Peca peca, TabletopProduct board, 
                         int origemX, int origemY, 
                         int destinoX, int destinoY, 
                         TabletopSubject subject) {
        if (peca.getMovimentoStrategy() == null) {
            System.out.println("Nenhuma estratégia definida para " + peca.getNome());
            return false;
        }
        System.out.println("Estado Normal: Movimento solicitado para a peça " + peca.getNome());
        return peca.getMovimentoStrategy().mover(peca, board, 
                                                 origemX, origemY, 
                                                 destinoX, destinoY, 
                                                 subject);
    }
}
