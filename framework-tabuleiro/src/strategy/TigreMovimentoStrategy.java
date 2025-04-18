package strategy;

import builder.TabletopProduct;
import observer.TabletopSubject;

public class TigreMovimentoStrategy implements MovimentoStrategy {

    @Override
    public boolean mover(Object peca, TabletopProduct board, int ox, int oy, int dx, int dy, TabletopSubject subject) {
        boolean movimentoValido = verificaMovimentoBasico(ox, oy, dx, dy);

        if (estaSaltandoSobreAgua(ox, oy, dx, dy)) {
            boolean ratoBloqueando = verificaSeHaRatoNoCaminho(board, ox, oy, dx, dy);
            if (ratoBloqueando) return false;
            movimentoValido = true;
        }

        if (movimentoValido) {
            atualizarTabuleiro(board, ox, oy, dx, dy, peca);
            subject.notifyObservers("Tigre moveu-se para (" + dx + "," + dy + ")");
        }

        return movimentoValido;
    }

    private boolean verificaMovimentoBasico(int ox, int oy, int dx, int dy) {
        int difX = Math.abs(dx - ox);
        int difY = Math.abs(dy - oy);
        return (difX == 1 && difY == 0) || (difX == 0 && difY == 1);
    }

    private boolean estaSaltandoSobreAgua(int ox, int oy, int dx, int dy) {
        return (Math.abs(dx - ox) > 1 || Math.abs(dy - oy) > 1);
    }

    private boolean verificaSeHaRatoNoCaminho(TabletopProduct board, int ox, int oy, int dx, int dy) {
        // Simulação: lógica real verificaria caminho linha/coluna se contém rato
        return false;
    }

    private void atualizarTabuleiro(TabletopProduct board, int ox, int oy, int dx, int dy, Object peca) {
        System.out.println("Tigre movido.");
    }
}
