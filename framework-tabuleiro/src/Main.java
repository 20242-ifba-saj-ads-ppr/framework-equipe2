// Main.java
import facade.GameFacade;
import factorymethod.SelvaCellCreator;
import flyweight.TabletopFlyweightConcreteCreator;
import abstractfactory.SelvaPieceFactoryImpl;
import context.PlayerSide;
import composite.TabletopComponent;

public class Main {
    public static void main(String[] args) {
        // 1) Cria a fachada
        GameFacade game = new GameFacade();

        // 2) Configura o jogo Selva (7x9) usando as fábricas padrão
        game.setupSelva(
            7, 9,
            new SelvaCellCreator(),                    // Factory Method para as células
            new TabletopFlyweightConcreteCreator(),    // Flyweight para os tiles
            new SelvaPieceFactoryImpl()                // Abstract Factory para as peças
        );

        // 3) Divide o tabuleiro em lado branco/preto (Template Method)
        game.divideBoard();

        // 4) Exibe os tiles compostos
        System.out.println("\n Tiles composições:");
        for (TabletopComponent c : game.getBoard().getTiles()) {
            System.out.println(c.operation());
        }

        // 5) Executa um movimento de exemplo: Leão branco de (0,3) para (1,3)
        System.out.println("\n  Executando movimento de exemplo:");
        game.executeMove("Leão", PlayerSide.WHITE, 0, 3, 1, 3);

        // 6) Você pode continuar chamando outras operações via facade...
    }
}
