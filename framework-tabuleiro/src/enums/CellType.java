package enums;

import context.Position;

public enum CellType {
    LAND, WATER, DEN_WHITE, DEN_BLACK, TRAP_WHITE, TRAP_BLACK;

    /**
     * Retorna o tipo de célula com base na posição.
     * Substitua a lógica abaixo pelas regras do jogo Selva.
     */
    public static CellType lookup(Position pos) {
        if (pos.row == 3 && pos.col == 0) return DEN_WHITE;
        if (pos.row == 3 && pos.col == 8) return DEN_BLACK;
       
        return LAND;
    }
}