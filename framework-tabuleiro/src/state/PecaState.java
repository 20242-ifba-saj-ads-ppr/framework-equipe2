package state;

import builder.TabletopProduct;
import observer.TabletopSubject;
import context.Peca;

public interface PecaState {
    boolean mover(Peca peca, TabletopProduct board, 
                  int origemX, int origemY, 
                  int destinoX, int destinoY, 
                  TabletopSubject subject);
}
