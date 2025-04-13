package strategy;

import builder.TabletopProduct;
import observer.TabletopSubject;

/**
 * Interface que define a estratégia de movimentação de uma peça.
 * Permite que diferentes algoritmos sejam utilizados de forma intercambiável.
 */
public interface MovimentoStrategy {
    
    /**
     * Tenta executar o movimento da peça no tabuleiro e, se realizado,
     * notifica o sistema (através do Observer).
     * 
     * @param peca      objeto representando a peça (ex: instância de Peca)
     * @param board     o tabuleiro atual (como construído pelo Builder, TabletopProduct)
     * @param origemX   coordenada X de origem
     * @param origemY   coordenada Y de origem
     * @param destinoX  coordenada X de destino
     * @param destinoY  coordenada Y de destino
     * @param subject   objeto observável para notificar a movimentação
     * @return true se o movimento for realizado com sucesso, false caso contrário.
     */
    boolean mover(Object peca, TabletopProduct board,
                  int origemX, int origemY, 
                  int destinoX, int destinoY,
                  TabletopSubject subject);
}
