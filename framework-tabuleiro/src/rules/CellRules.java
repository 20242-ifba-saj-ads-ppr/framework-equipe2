package rules;

import factorymethod.CellAbstractProduct;

import context.Peca;
import context.PlayerSide;
import enums.CellType;

public class CellRules {

    /**
     * Verifica se a peça pode entrar na célula especificada.
     * - Uma peça não pode entrar na sua própria toca (Den).
     */
    public static boolean podeEntrar(Peca peca, CellAbstractProduct cell) {
        CellType type = cell.getType();
        PlayerSide side = peca.getSide();

        if (type == CellType.DEN_WHITE && side == PlayerSide.WHITE) return false;
        if (type == CellType.DEN_BLACK && side == PlayerSide.BLACK) return false;

        return true;
    }

    /**
     * Verifica se a célula é uma armadilha do inimigo.
     * Isso torna a peça vulnerável a qualquer inimigo.
     */
    public static boolean estaEmArmadilhaInimiga(Peca peca, CellAbstractProduct cell) {
        CellType type = cell.getType();
        PlayerSide side = peca.getSide();

        return (type == CellType.TRAP_WHITE && side == PlayerSide.BLACK) ||
               (type == CellType.TRAP_BLACK && side == PlayerSide.WHITE);
    }

    /**
     * Verifica se a célula é água.
     */
    public static boolean isAgua(CellAbstractProduct cell) {
        return cell.getType() == CellType.WATER;
    }

    /**
     * Verifica se a célula é uma toca.
     */
    public static boolean isToca(CellAbstractProduct cell) {
        return cell.getType() == CellType.DEN_WHITE || cell.getType() == CellType.DEN_BLACK;
    }

    /**
     * Verifica se a célula é uma armadilha.
     */
    public static boolean isArmadilha(CellAbstractProduct cell) {
        return cell.getType() == CellType.TRAP_WHITE || cell.getType() == CellType.TRAP_BLACK;
    }
}
