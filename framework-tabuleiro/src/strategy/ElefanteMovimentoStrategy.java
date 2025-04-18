package strategy;

import builder.TabletopProduct;
import observer.TabletopSubject;

public class ElefanteMovimentoStrategy implements MovimentoStrategy {

    @Override
    public boolean mover(Object peca, TabletopProduct board, int ox, int oy, int dx, int dy, TabletopSubject subject) {
        if (!movimentoSimples(ox, oy, dx, dy)) return false;

        // Regra: Elefante não pode capturar rato
        boolean destinoTemRato = verificaSeTemRato(board, dx, dy);
        if (destinoTemRato) return false;

        atualizarTabuleiro(board, ox, oy, dx, dy, peca);
        subject.notifyObservers("Elefante moveu-se para (" + dx + "," + dy + ")");
        return true;
    }

    private boolean movimentoSimples(int ox, int oy, int dx, int dy) {
        return (Math.abs(ox - dx) + Math.abs(oy - dy)) == 1;
    }

    private boolean verificaSeTemRato(TabletopProduct board, int x, int y) {
        // Simulação: em uma implementação real, você checaria o conteúdo da célula
        return false;
    }

    private void atualizarTabuleiro(TabletopProduct board, int ox, int oy, int dx, int dy, Object peca) {
        System.out.println("Elefante movido.");
    }
}
