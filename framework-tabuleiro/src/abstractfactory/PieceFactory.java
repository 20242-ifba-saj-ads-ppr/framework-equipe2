// src/main/java/abstractfactory/PieceFactory.java
package abstractfactory;

import context.Peca;
import context.PlayerSide;


public interface PieceFactory {
    /**
     * Cria uma peça pelo tipo (nome) e lado.
     * @param type  palavra-chave identificando a peça (e.g. "Leão", "Elefante")
     * @param side  lado do jogador (WHITE ou BLACK)
     */
    Peca create(String type, PlayerSide side);
}
