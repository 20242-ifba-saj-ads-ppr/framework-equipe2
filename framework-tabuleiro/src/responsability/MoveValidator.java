package responsability;

import builder.TabletopProduct;
import context.Peca;
import rules.CellRules;

public abstract class MoveValidator {
    private final MoveValidator next;
    public MoveValidator(MoveValidator next) { this.next = next; }
    public boolean validate(Peca peca, TabletopProduct board,
                            int ox, int oy, int dx, int dy) {
        if (!check(peca, board, ox, oy, dx, dy)) return false;
        return next == null || next.validate(peca, board, ox, oy, dx, dy);
    }
    protected abstract boolean check(Peca peca, TabletopProduct board,
                                     int ox, int oy, int dx, int dy);
}
