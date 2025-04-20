package facade;

import builder.TabletopDirector;
import builder.TabletopProduct;
import command.Command;
import command.CommandInvoker;
import command.MoverPecaCommand;
import context.Peca;
import context.PlayerSide;
import flyweight.TabletopFlyweightConcreteCreator;
import observer.TabletopConcreteObserver;
import observer.TabletopConcreteSubject;
import observer.TabletopObserver;
import strategy.LeaoMovimentoStrategy;
import templatemethod.AbstractBoardDivider;
import templatemethod.StandardBoardDivider;

/**
 * GameFacade agora expõe também o Template Method para divisão de tabuleiro.
 */
public class GameFacade {
    
    private TabletopProduct board;
    private TabletopConcreteSubject subject;
    private CommandInvoker commandInvoker;
    
    public GameFacade() {
        this.commandInvoker = new CommandInvoker();
        subject = new TabletopConcreteSubject("Inicial");
        TabletopObserver observer = new TabletopConcreteObserver("Sistema de Eventos");
        subject.attach(observer);
    }
    
    public void setupGame(int width, int height) {
        TabletopConcreteBuilder builder = new TabletopConcreteBuilder();
        TabletopFlyweightConcreteCreator flyFactory = new TabletopFlyweightConcreteCreator();
        TabletopDirector director = new TabletopDirector(builder);
        board = director.construct(width, height, flyFactory);
        subject.setState("Tabuleiro configurado.");
    }
    
    public void executeMove(String pieceName,
                        PlayerSide side,
                        int ox, int oy,
                        int dx, int dy) {
        Peca piece = new Peca(pieceName, side, new LeaoMovimentoStrategy());
        Command moveCommand = new MoverPecaCommand(piece, board, ox, oy, dx, dy, subject);
        commandInvoker.executeCommand(moveCommand);
    }

    
    /**
     * Método-fachada para dividir o tabuleiro em lado branco e preto.
     * Internamente usa o Template Method.
     */
    public void divideBoard() {
        TabletopFlyweightConcreteCreator flyFactory = new TabletopFlyweightConcreteCreator();
        AbstractBoardDivider divider = new StandardBoardDivider();
        divider.divideBoard(board, flyFactory);
        subject.setState("Tabuleiro dividido em branco/preto.");
    }
    
    public TabletopProduct getBoard() {
        return board;
    }
}
