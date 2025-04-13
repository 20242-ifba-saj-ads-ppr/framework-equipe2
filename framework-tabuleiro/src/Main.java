import builder.*;
import command.Command;
import command.CommandInvoker;
import command.MoverPecaCommand;
import composite.TabletopComponent;
import context.Peca;
import flyweight.TabletopFlyweightConcreteCreator;
import flyweight.TabletopFlyweightProduct;
import observer.TabletopConcreteObserver;
import observer.TabletopConcreteSubject;
import observer.TabletopObserver;
import prototype.TabletopConcretePrototype;
import strategy.LeaoMovimentoStrategy;
import strategy.TigreMovimentoStrategy;
import state.BloqueadaState;

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

        System.out.println("Montando tabuleiro com Builder...");
        TabletopConcreteBuilder builder = new TabletopConcreteBuilder();
        TabletopDirector director = new TabletopDirector(builder);
        TabletopProduct product = director.construct(10, 10, flyweightFactory);

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

        System.out.println("\nFlyweights utilizados:");
        for (String f : flyweightFactory.listFlyweights()) {
            System.out.println("- " + f);
        }

        // ⬇️ Integração com Command Pattern e persistência (log) ⬇️
        System.out.println("\nExecutando comando de movimento com Command...");

        // Cria uma peça com a estratégia do Leão
        Peca leao = new Peca("Leão", new LeaoMovimentoStrategy());

        // Cria o comando para mover a peça (de (0,0) para (1,0))
        Command moverLeaoCommand = new MoverPecaCommand(leao, product, 0, 0, 1, 0, subject);

        // Instancia o invoker para executar o comando
        CommandInvoker invoker = new CommandInvoker();
        boolean sucesso = invoker.executeCommand(moverLeaoCommand);

        if (sucesso) {
            System.out.println("Movimento realizado com sucesso!");
        } else {
            System.out.println("Falha ao mover a peça.");
        }
        
  
        System.out.println("\n--- Demonstração do padrão State ---");
        
        // Cria uma peça "Tigre" com sua estratégia (aqui, utilizando a mesma para exemplificar)
        Peca tigre = new Peca("Tigre", new TigreMovimentoStrategy());
        
        System.out.println("Movendo peça no estado Normal:");
        tigre.mover(product, 2, 2, 3, 2, subject);
        
        // Altera o estado da peça para Bloqueada
        tigre.setState(new BloqueadaState());
        System.out.println("Tentando mover a peça com estado Bloqueada:");
        tigre.mover(product, 3, 2, 4, 2, subject);
    }
}
