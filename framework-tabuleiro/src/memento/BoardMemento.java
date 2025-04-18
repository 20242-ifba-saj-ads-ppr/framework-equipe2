package memento;

import builder.TabletopProduct;

/**
 * Memento que guarda um snapshot do TabletopProduct.
 */
public class BoardMemento {
    private final TabletopProduct state;

    public BoardMemento(TabletopProduct board) {
        this.state = board.deepClone();
    }

    public TabletopProduct getState() {
        return state;
    }
}
