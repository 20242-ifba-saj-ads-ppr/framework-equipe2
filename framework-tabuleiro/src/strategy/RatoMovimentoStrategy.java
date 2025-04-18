package strategy;

import builder.TabletopProduct;
import observer.TabletopSubject;

public class RatoMovimentoStrategy implements MovimentoStrategy {

    @Override
    public boolean mover(Object peca, TabletopProduct board, int ox, int oy, int dx, int dy, TabletopSubject subject) {
        if (!movimentoBasico(ox, oy, dx, dy)) return false;

        boolean estaNaAgua = estaNaAgua(ox, oy);
        boolean destinoElefante = verificaDestinoEhElefante(board, dx, dy);

        if (estaNaAgua && destinoElefante) {
            return false;
        }

        atualizarTabuleiro(board, ox, oy, dx, dy, peca);
        subject.notifyObservers("Rato moveu-se para (" + dx + "," + dy + ")");
        return true;
    }

    private boolean movimentoBasico(int ox, int oy, int dx, int dy) {
        return (Math.abs(dx - ox) + Math.abs(dy - oy)) == 1;
    }

    private boolean estaNaAgua(int x, int y) {
        // Verifica se a célula (x,y) é azul (água)
        return false;
    }

    private boolean verificaDestinoEhElefante(TabletopProduct board, int dx, int dy) {
        // Simular: checar se destino contém um elefante inimigo
        return false;
    }

    private void atualizarTabuleiro(TabletopProduct board, int ox, int oy, int dx, int dy, Object peca) {
        System.out.println("Rato moveu.");
    }
}
