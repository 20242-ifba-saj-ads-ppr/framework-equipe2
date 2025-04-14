// Framework Tabuleiro - Main.java
import builder.TabletopConcreteBuilder;
import builder.TabletopDirector;
import builder.TabletopProduct;
import command.Command;
import command.CommandInvoker;
import command.MoverPecaCommand;
import composite.TabletopComponent;
import context.Peca;
import flyweight.TabletopFlyweightConcreteCreator;
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

        // Criação dos tiles básicos via Factory
        System.out.println("Criando tiles via Factory...");
        TabletopConcreteBuilder builder = new TabletopConcreteBuilder();

        // Flyweight Factory para reutilização de tiles
        TabletopFlyweightConcreteCreator flyweightFactory = new TabletopFlyweightConcreteCreator();

        // Director: responsável por orquestrar a construção do tabuleiro
        TabletopDirector director = new TabletopDirector(builder);
        // O método construct agora chama buildArea, buildTiles e buildExtras,
        // de modo a construir toda a estrutura composta (Composite) do tabuleiro.
        TabletopProduct product = director.construct(10, 10, flyweightFactory);

        // Demonstração do Prototype: clonando o tabuleiro construído
        System.out.println("Clonando tabuleiro com Prototype...");
        TabletopConcretePrototype prototype = new TabletopConcretePrototype(product);
        TabletopConcretePrototype cloned = (TabletopConcretePrototype) prototype.clonePrototype();

        // Configurando o Observer para notificações de eventos
        System.out.println("Aplicando Observer...");
        TabletopConcreteSubject subject = new TabletopConcreteSubject("Inicial");
        TabletopObserver observer = new TabletopConcreteObserver("Sistema de Eventos");
        subject.attach(observer);
        subject.setState("Tabuleiro clonado com sucesso.");

        // Exibindo dados do produto original
        System.out.println("\n🔹 Produto Original:");
        System.out.println("Área: " + product.getX() + "x" + product.getY());
        for (TabletopComponent c : product.getTiles()) {
            System.out.println(c.operation());
        }

        // Exibindo dados do produto clonado
        System.out.println("\n🔸 Produto Clonado:");
        System.out.println("Área: " + cloned.getProduct().getX() + "x" + cloned.getProduct().getY());

        // Listando os flyweights utilizados
        System.out.println("\nFlyweights utilizados:");
        for (String f : flyweightFactory.listFlyweights()) {
            System.out.println("- " + f);
        }

        // ⬇️ Integração com Command Pattern ⬇️
        System.out.println("\nExecutando comando de movimento com Command...");

        // Cria uma peça com a estratégia do Leão
        Peca leao = new Peca("Leão", new LeaoMovimentoStrategy());

        // Cria o comando para mover a peça (de (0,0) para (1,0))
        Command moverLeaoCommand = new MoverPecaCommand(leao, product, 0, 0, 1, 0, subject);

        // Invoker para executar o comando
        CommandInvoker invoker = new CommandInvoker();
        boolean sucesso = invoker.executeCommand(moverLeaoCommand);
        if (sucesso) {
            System.out.println("Movimento realizado com sucesso!");
        } else {
            System.out.println("Falha ao mover a peça.");
        }

        // ⬇️ Demonstração do padrão State ⬇️
        System.out.println("\n--- Demonstração do padrão State ---");
        // Cria uma peça "Tigre" com a estratégia do Tigre
        Peca tigre = new Peca("Tigre", new TigreMovimentoStrategy());
        
        System.out.println("Movendo peça no estado Normal:");
        tigre.mover(product, 2, 2, 3, 2, subject);
        
        // Altera o estado da peça para Bloqueada e tenta mover
        tigre.setState(new BloqueadaState());
        System.out.println("Tentando mover a peça com estado Bloqueada:");
        tigre.mover(product, 3, 2, 4, 2, subject);
    }
}
