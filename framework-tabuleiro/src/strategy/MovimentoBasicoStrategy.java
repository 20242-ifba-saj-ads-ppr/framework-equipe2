package strategy;

import builder.TabletopProduct;
import observer.TabletopSubject;

public class MovimentoBasicoStrategy implements MovimentoStrategy {

    @Override
    public boolean mover(Object peca, TabletopProduct board, int ox, int oy, int dx, int dy, TabletopSubject subject) {
        if ((Math.abs(dx - ox) + Math.abs(dy - oy)) != 1) return false;

        atualizarTabuleiro(board, ox, oy, dx, dy, peca);
        subject.notifyObservers("Peça moveu-se de (" + ox + "," + oy + ") para (" + dx + "," + dy + ")");
        return true;
    }

    private void atualizarTabuleiro(TabletopProduct board, int ox, int oy, int dx, int dy, Object peca) {
        System.out.println("Peça comum movida.");
    }
}
