package responsability;

import builder.TabletopProduct;
import context.Peca;
import rules.CellRules;

public class CellEntryValidator extends MoveValidator {
    public CellEntryValidator(MoveValidator next) { super(next); }
    @Override protected boolean check(Peca peca, TabletopProduct b, int ox, int oy, int dx, int dy) {
        return CellRules.podeEntrar(peca, b.getCellAt(dx, dy));
    }
}
