package factorymethod;


// Creator abstrato

import context.Position;

public abstract class CellCreator {
    /** Método-Template: chama o Factory Method */
    public final CellAbstractProduct createCell(Position pos) {
        CellAbstractProduct cell = factoryMethod(pos);
        // hook: poderia haver inicializações comuns aqui
        return cell;
    }

    /** Factory Method que as subclasses concretas implementam */
    protected abstract CellAbstractProduct factoryMethod(Position pos);
}
