package responsability;



import builder.TabletopProduct;
import context.Peca;

public class CaptureValidator extends MoveValidator {
    public CaptureValidator(MoveValidator next) { super(next); }
    @Override protected boolean check(Peca peca, TabletopProduct b, int ox, int oy, int dx, int dy) {
        Peca target = b.getPieceAt(dx, dy);
        if (target == null) return true;
        return target.getSide() != peca.getSide();
    }
}