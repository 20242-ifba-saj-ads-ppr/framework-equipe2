package strategy;

import builder.TabletopProduct;
import observer.TabletopSubject;

/**
 * Implementação concreta do MovementStrategy para a peça Leão.
 */
public class LeaoMovimentoStrategy implements MovimentoStrategy {

    @Override
    public boolean mover(Object peca, TabletopProduct board,
                         int origemX, int origemY, 
                         int destinoX, int destinoY,
                         TabletopSubject subject) {
        
        // 1. Verifica se o movimento básico (1 célula) é válido.
        boolean movimentoValido = verificaMovimentoBasico(origemX, origemY, destinoX, destinoY);
        
        // 2. Se o movimento for maior que 1 célula, interprete como um salto sobre água.
        if (estaSaltandoSobreAgua(origemX, origemY, destinoX, destinoY)) {
            // Lógica de salto: pode incluir validação de obstáculos (como a presença de Rato)
            movimentoValido = true; // Esta lógica deve ser expandida conforme as regras do jogo.
        }
        
        // 3. Se o movimento for válido, interage com outros padrões:
        if (movimentoValido) {
            // 3.1. Pode atualizar o tabuleiro (Composite): 
            // Aqui você poderia, por exemplo, remover a peça da posição de origem
            // e adicioná-la na posição de destino. Como o TabletopProduct contém
            // uma lista de TabletopComponent, você pode iterar e encontrar o componente
            // que representa a posição a ser atualizada.
            atualizarTabuleiro(board, origemX, origemY, destinoX, destinoY, peca);
            
            // 3.2. Notifica os observadores (Observer) sobre o movimento.
            subject.notifyObservers("Leão moveu-se de (" + origemX + "," + origemY + 
                                    ") para (" + destinoX + "," + destinoY + ")");
        }
        
        return movimentoValido;
    }
    
    /**
     * Verifica se o movimento é básico (1 casa na horizontal ou vertical).
     */
    private boolean verificaMovimentoBasico(int ox, int oy, int dx, int dy) {
        int difX = Math.abs(dx - ox);
        int difY = Math.abs(dy - oy);
        return ((difX == 1 && difY == 0) || (difX == 0 && difY == 1));
    }
    
    /**
     * Verifica se o movimento corresponde a um salto sobre água 
     * (movimento com distância maior que 1).
     */
    private boolean estaSaltandoSobreAgua(int ox, int oy, int dx, int dy) {
        int distancia = Math.abs(dx - ox) + Math.abs(dy - oy);
        return (distancia > 1);
    }
    
    /**
     * Exemplo de integração com o tabuleiro (Composite).
     * Aqui você pode atualizar a lista de componentes que representam as células.
     * 
     * @param board     tabuleiro do jogo (TabletopProduct)
     * @param origemX   posição X de origem
     * @param origemY   posição Y de origem
     * @param destinoX  posição X de destino
     * @param destinoY  posição Y de destino
     * @param peca      a peça que está se movendo
     */
    private void atualizarTabuleiro(TabletopProduct board,
                                    int origemX, int origemY,
                                    int destinoX, int destinoY, Object peca) {
        // Exemplo de placeholder: em uma implementação real, você procuraria o componente
        // que representa a posição "origem" dentro de board.getTiles(), remove-lo ou marcá-lo
        // como vazio, e inserir o componente referente à peça "peca" na posição "destino".
        System.out.println("Atualizando tabuleiro: movendo peça de (" 
                            + origemX + "," + origemY + ") para (" 
                            + destinoX + "," + destinoY + ").");
    }

}
