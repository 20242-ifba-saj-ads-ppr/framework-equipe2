package builder;

import TabletopComposite;
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
        TabletopComposite zonaPadrao = montarZonaPadrao(flyFactory);
        this.tiles.add(zonaPadrao);
        System.out.println("Tiles padrão adicionados à área.");
    }

   
    @Override
    public void buildExtras(TabletopFlyweightConcreteCreator flyFactory) {
        
        TabletopComposite zonaExtras = new TabletopComposite("Zona Extra - Obstáculos");
        zonaExtras.add(flyFactory.getFlyweight("Obstáculo"), 2, 2);
        zonaExtras.add(flyFactory.getFlyweight("Obstáculo"), 2, 3);
        
        this.tiles.add(zonaExtras);
        System.out.println("Extras adicionados à área.");
    }

    @Override
    public TabletopProduct getResult() {
        return new TabletopProduct(x, y, tiles);
    }
}
