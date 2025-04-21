package responsability;

import builder.TabletopProduct;
import context.Peca;

public class BoundsValidator extends MoveValidator {
    public BoundsValidator(MoveValidator next) { super(next); }
    @Override protected boolean check(Peca peca, TabletopProduct b, int ox, int oy, int dx, int dy) {
        return b.isWithinBounds(dx, dy);
    }
}