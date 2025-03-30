package builder;
// Builder Pattern - GOF
// Diretor que controla o processo de construção
import java.util.List;
import composite.TabletopComponent; 

public class TabletopDirector {
    public TabletopProduct construct(int x, int y, List<TabletopComponent> tiles) {
        TabletopBuilder builder = new TabletopConcreteBuilder();
        return builder.setArea(x, y).setTiles(tiles).getResult();
    }
}
