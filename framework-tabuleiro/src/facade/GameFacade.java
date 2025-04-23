package facade;

import context.Peca;
import context.Position;
import context.PlayerSide;
import factorymethod.CellCreator;
import flyweight.TabletopFlyweightConcreteCreator;
import flyweight.TabletopFlyweightFactory;
import abstractfactory.SelvaPieceFactory;
import builder.SelvaTabletopBuilder;
import builder.TabletopDirector;
import builder.TabletopProduct;
import command.Command;
import command.CommandInvoker;
import command.MoverPecaCommand;
import composite.TabletopComponent;
import observer.TabletopConcreteObserver;
import observer.TabletopConcreteSubject;
import observer.TabletopObserver;
import templatemethod.AbstractBoardDivider;
import templatemethod.StandardBoardDivider;
import responsability.MoveValidator;
import responsability.BoundsValidator;
import responsability.CellEntryValidator;
import responsability.CaptureValidator;

import java.util.ArrayList;
import java.util.List;

public class GameFacade {
    private TabletopProduct board;
    private TabletopConcreteSubject subject;
    private CommandInvoker commandInvoker;
    private MoveValidator validatorChain;

    public GameFacade() {
        this.commandInvoker = new CommandInvoker();
        subject = new TabletopConcreteSubject("Inicial");
        TabletopObserver observer = new TabletopConcreteObserver("Sistema de Eventos");
        subject.attach(observer);
        // Monta cadeia de validação
        this.validatorChain = new BoundsValidator(
            new CellEntryValidator(
            new CaptureValidator(null)
        ));
    }

    public boolean executeMove(String pieceName,
                               PlayerSide side,
                               int ox, int oy,
                               int dx, int dy) {
        // Mova apenas se válido
        Peca piece = board.getPieceAt(ox, oy);
        if (piece == null || !piece.getNome().equals(pieceName)) {
            return false;
        }
        if (!validatorChain.validate(piece, board, ox, oy, dx, dy)) {
            return false;
        }
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

    /** Retorna posições válidas sem executar o movimento */
    public List<Position> getValidMoves(int ox, int oy) {
        List<Position> moves = new ArrayList<>();
        Peca p = board.getPieceAt(ox, oy);
        if (p == null) return moves;
        for (int dx = 0; dx < board.getWidth(); dx++) {
            for (int dy = 0; dy < board.getHeight(); dy++) {
                if (validatorChain.validate(p, board, ox, oy, dx, dy)) {
                    moves.add(new Position(dx, dy));
                }
            }
        }
        return moves;
    }

    public void undo() {
        commandInvoker.undo();
    }

    public void replay() {
        Command last = commandInvoker.getLastCommand();
        if (last != null) commandInvoker.executeCommand(last);
    }
}
