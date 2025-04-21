package factorymethod;

import context.Position;
import enums.CellType;
import factorymethod.CellAbstractProduct;
import factorymethod.CellCreator;
import factorymethod.LandCell;
import factorymethod.WaterCell;
import factorymethod.TrapCell;
import factorymethod.DenCell;

/**
 * ConcreteCreator para células do jogo Selva.
 * Implementa o Factory Method factoryMethod().
 */
public class SelvaCellCreator extends CellCreator {

    @Override
    protected CellAbstractProduct factoryMethod(Position pos) {
        CellType type = determineCellType(pos);
        switch (type) {
            case LAND:
                return new LandCell(pos);
            case WATER:
                return new WaterCell(pos);
            case TRAP_WHITE:
                return new TrapCell(pos, true);
            case TRAP_BLACK:
                return new TrapCell(pos, false);
            case DEN_WHITE:
                return new DenCell(pos, true);
            case DEN_BLACK:
                return new DenCell(pos, false);
            default:
                throw new IllegalArgumentException("Tipo inválido: " + type);
        }
    }

    /**
     * Determina o CellType para a posição dada, de acordo com as regras fixas do tabuleiro Selva.
     */
    private CellType determineCellType(Position pos) {
        int x = pos.row, y = pos.col;
        // Coordenadas fixas de água no tabuleiro 7x9
        int[][] waters = {
            {3,1},{3,2},{4,1},{4,2},{5,1},{5,2},
            {3,4},{3,5},{4,4},{4,5},{5,4},{5,5}
        };
        for (int[] w : waters) {
            if (y == w[0] && x == w[1]) {
                return CellType.WATER;
            }
        }
        // Tocas (Dens)
        if (x == 3 && y == 0) return CellType.DEN_WHITE;
        if (x == 3 && y == 8) return CellType.DEN_BLACK;
        // Armadilhas
        int[][] trapsWhite = {{2,0},{3,1},{4,0}};
        for (int[] t : trapsWhite) {
            if (y == t[0] && x == t[1]) {
                return CellType.TRAP_WHITE;
            }
        }
        int[][] trapsBlack = {{2,8},{3,7},{4,8}};
        for (int[] t : trapsBlack) {
            if (y == t[0] && x == t[1]) {
                return CellType.TRAP_BLACK;
            }
        }
        // Demais células são terreno
        return CellType.LAND;
    }
}
