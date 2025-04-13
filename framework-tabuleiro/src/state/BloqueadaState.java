package state;

import builder.TabletopProduct;
import observer.TabletopSubject;
import context.Peca;

public class BloqueadaState implements PecaState {

    @Override
    public boolean mover(Peca peca, TabletopProduct board, 
                         int origemX, int origemY, 
                         int destinoX, int destinoY, 
                         TabletopSubject subject) {
        System.out.println("A peça " + peca.getNome() + " está bloqueada e não pode se mover.");
        return false;
    }
}
