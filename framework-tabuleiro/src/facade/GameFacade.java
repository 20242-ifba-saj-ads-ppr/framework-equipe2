package facade;

import abstractfactory.SelvaPieceFactory;
import builder.SelvaTabletopBuilder;
import builder.TabletopDirector;
import builder.TabletopProduct;
import command.Command;
import command.CommandInvoker;
import command.MoverPecaCommand;
import context.Peca;
import context.PlayerSide;
import context.Position;
import factorymethod.CellCreator;
import flyweight.TabletopFlyweightConcreteCreator;
import flyweight.TabletopFlyweightFactory;
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
    
   
    
    public boolean executeMove(String pieceName,
                               PlayerSide side,
                               int ox, int oy,
                               int dx, int dy) {
        Peca piece = new Peca(pieceName, side, new LeaoMovimentoStrategy(), new Position(ox, oy));
        Command moveCommand = new MoverPecaCommand(piece, board, ox, oy, dx, dy, subject);
        return commandInvoker.executeCommand(moveCommand);
    }

    public void setupSelva(int width,
                           int height,
                           CellCreator cellFactory,
                           TabletopFlyweightFactory flyFactory,
                           SelvaPieceFactory pieceFactory) {
        SelvaTabletopBuilder builder = new SelvaTabletopBuilder();
        TabletopDirector director = new TabletopDirector(builder);

        board = director.construct(width, height,
                                   cellFactory,
                                   flyFactory,
                                   pieceFactory);

        subject.setState("Jogo Selva configurado");
    }
    
    
    public void divideBoard() {
        TabletopFlyweightConcreteCreator flyFactory = new TabletopFlyweightConcreteCreator();
        AbstractBoardDivider divider = new StandardBoardDivider();
        divider.divideBoard(board, flyFactory);
        subject.setState("Tabuleiro dividido em branco/preto.");
    }
    
    public TabletopProduct getBoard() {
        return board;
    }

    public void undo() {
        commandInvoker.undo();
    }
    
    public void replay() {
        Command lastCommand = commandInvoker.getLastCommand();
        if (lastCommand != null) {
            commandInvoker.executeCommand(lastCommand);
        } else {
            System.out.println("Nenhum comando anterior para repetir.");
        }
    }
}
