// Main.java - Integração de todos os padrões GoF
import java.util.*;

import builder.TabletopDirector;
import builder.TabletopProduct;
import composite.TabletopComponent;
import composite.TabletopComposite;
import flyweight.TabletopFlyweightConcreteCreator;
import flyweight.TabletopFlyweightProduct;
import observer.TabletopConcreteObserver;
import observer.TabletopConcreteSubject;
import observer.TabletopObserver;
import prototype.TabletopConcretePrototype;

public class Main {
    public static void main(String[] args) {
        System.out.println("Iniciando montagem de Tabuleiro...");
        System.out.println("Criando tiles via Factory...");
        TabletopConcreteCreator creator1 = new TabletopConcreteCreator("Grama");
        TabletopConcreteCreator creator2 = new TabletopConcreteCreator("Pedra");
        TabletopAbstractProduct tile1 = creator1.createProduct();
        TabletopAbstractProduct tile2 = creator2.createProduct();

        System.out.println("Usando Flyweight para gerenciar tiles compartilhados...");
        TabletopFlyweightConcreteCreator flyweightFactory = new TabletopFlyweightConcreteCreator();
        TabletopFlyweightProduct flyTile1 = flyweightFactory.getFlyweight(((TabletopConcreteProduct) tile1).getName());
        TabletopFlyweightProduct flyTile2 = flyweightFactory.getFlyweight(((TabletopConcreteProduct) tile2).getName());

        System.out.println(flyTile1.operation("x:0,y:0"));
        System.out.println(flyTile2.operation("x:0,y:1"));

        System.out.println("Criando área (Composite)...");
        TabletopComposite area = new TabletopComposite("Zona Inicial");
        area.add(flyTile1, 0, 0);
        area.add(flyTile2, 0, 1);
        area.add(flyTile1, 0, 2);
        area.add(flyTile2, 1, 0);

        System.out.println("Montando tabuleiro com Builder...");
        List<TabletopComponent> tiles = new ArrayList<>();
        tiles.add(area);
        TabletopDirector director = new TabletopDirector();
        TabletopProduct product = director.construct(10, 10, tiles);

        System.out.println("Clonando tabuleiro com Prototype...");
        TabletopConcretePrototype prototype = new TabletopConcretePrototype(product);
        TabletopConcretePrototype cloned = (TabletopConcretePrototype) prototype.clonePrototype();

        System.out.println("Aplicando Observer...");
        TabletopConcreteSubject subject = new TabletopConcreteSubject("Inicial");
        TabletopObserver observer = new TabletopConcreteObserver("Sistema de Eventos");
        subject.attach(observer);
        subject.setState("Tabuleiro clonado com sucesso.");

        System.out.println("\n🔹 Produto Original:");
        System.out.println("Área: " + product.getX() + "x" + product.getY());
        for (TabletopComponent c : product.getTiles()) {
            System.out.println(c.operation());
        }

        System.out.println("\n🔸 Produto Clonado:");
        System.out.println("Área: " + cloned.getProduct().getX() + "x" + cloned.getProduct().getY());

        System.out.println("\n Flyweights utilizados:");
        for (String f : flyweightFactory.listFlyweights()) {
            System.out.println("- " + f);
        }
    }
}
