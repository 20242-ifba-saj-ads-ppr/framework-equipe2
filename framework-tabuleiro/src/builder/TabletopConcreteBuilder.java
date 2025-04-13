package builder;

import composite.TabletopComposite;
import flyweight.TabletopFlyweightConcreteCreator;

public class TabletopConcreteBuilder extends AbstractTabletopBuilder {

    @Override
    public void buildArea(int width, int height) {
        this.x = width;
        this.y = height;
        System.out.println("Área definida: " + x + " x " + y);
    }

    private TabletopComposite montarZonaPadrao(TabletopFlyweightConcreteCreator flyFactory) {
        TabletopComposite area = new TabletopComposite("Zona Padrão");
        area.add(flyFactory.getFlyweight("Grama"), 0, 0);
        area.add(flyFactory.getFlyweight("Pedra"), 0, 1);
        area.add(flyFactory.getFlyweight("Grama"), 0, 2);
        area.add(flyFactory.getFlyweight("Pedra"), 1, 0);
        return area;
    }

    @Override
    public void buildTiles(TabletopFlyweightConcreteCreator flyFactory) {
        this.tiles.add(montarZonaPadrao(flyFactory));
        System.out.println("Tiles padrão adicionados à área.");
    }

    @Override
    public void buildExtras() {
        // Exemplo: adicionar obstáculos ou itens interativos
        System.out.println("Sem extras a serem adicionados.");
    }

    @Override
    public TabletopProduct getResult() {
        return new TabletopProduct(x, y, tiles);
    }
}
