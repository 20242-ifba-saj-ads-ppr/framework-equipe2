package strategy;

import observer.TabletopSubject;


import builder.TabletopProduct;

/**
 * Implementação concreta do MovementStrategy para a peça Tigre.
 */
public class TigreMovimentoStrategy implements MovimentoStrategy {

    @Override
    public boolean mover(Object peca, TabletopProduct board,
                         int origemX, int origemY, 
                         int destinoX, int destinoY,
                         TabletopSubject subject) {
        
        // Validação básica do movimento
        boolean movimentoValido = verificaMovimentoBasico(origemX, origemY, destinoX, destinoY);
        
        // Se o movimento for maior que 1 célula, trata como salto, com regras específicas do Tigre.
        if (estaSaltandoSobreAgua(origemX, origemY, destinoX, destinoY)) {
            // Adicione lógica específica para o Tigre, se necessário.
            movimentoValido = true;
        }
        
        // Se o movimento é válido, interage com o tabuleiro e notifica os observadores.
        if (movimentoValido) {
            atualizarTabuleiro(board, origemX, origemY, destinoX, destinoY, peca);
            subject.notifyObservers("Tigre moveu-se de (" + origemX + "," + origemY + 
                                    ") para (" + destinoX + "," + destinoY + ")");
        }
        
        return movimentoValido;
    }
    
    private boolean verificaMovimentoBasico(int ox, int oy, int dx, int dy) {
        int difX = Math.abs(dx - ox);
        int difY = Math.abs(dy - oy);
        return ((difX == 1 && difY == 0) || (difX == 0 && difY == 1));
    }
    
    private boolean estaSaltandoSobreAgua(int ox, int oy, int dx, int dy) {
        int distancia = Math.abs(dx - ox) + Math.abs(dy - oy);
        return (distancia > 1);
    }
    
    private void atualizarTabuleiro(TabletopProduct board,
                                    int origemX, int origemY,
                                    int destinoX, int destinoY, Object peca) {
        // Placeholder para a atualização do tabuleiro usando o Composite Pattern.
        System.out.println("Atualizando tabuleiro: movendo peça de (" 
                            + origemX + "," + origemY + ") para (" 
                            + destinoX + "," + destinoY + ").");
    }
}
