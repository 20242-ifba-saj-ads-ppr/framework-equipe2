package command;

import builder.TabletopProduct;
import context.Peca;
import memento.BoardMemento;
import observer.TabletopSubject;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class MoverPecaCommand implements Command {
    private Peca peca;
    private TabletopProduct board;
    private int origemX, origemY;
    private int destinoX, destinoY;
    private TabletopSubject subject;

    public MoverPecaCommand(Peca peca, TabletopProduct board,
                            int origemX, int origemY,
                            int destinoX, int destinoY,
                            TabletopSubject subject) {
        this.peca = peca;
        this.board = board;
        this.origemX = origemX;
        this.origemY = origemY;
        this.destinoX = destinoX;
        this.destinoY = destinoY;
        this.subject = subject;
    }

    @Override
    public boolean execute() {
        System.out.println("Executando comando para mover a peça " + peca.getNome());
        boolean resultado = peca.mover(board, origemX, origemY, destinoX, destinoY, subject);
        if (resultado) store();
        return resultado;
    }

    @Override
    public String serialize() {
    return String.join(";", 
        "MoverPecaCommand",
        peca.getNome(),
        peca.getSide().name(),
        Integer.toString(origemX),
        Integer.toString(origemY),
        Integer.toString(destinoX),
        Integer.toString(destinoY)
    );
}

    @Override
    public void store() {
        try (PrintWriter out = new PrintWriter(new FileWriter("command.log", true))) {
            out.println(serialize());
        } catch (IOException e) {
            System.err.println("Erro ao armazenar comando: " + e.getMessage());
        }
    }

    @Override
    public BoardMemento saveMemento() {
        // Originator cria seu Memento antes de executar
        return new BoardMemento(board);
    }

    @Override
    public void restore(BoardMemento memento) {
        // Restaura o estado do tabuleiro
        board.restoreState(memento.getState());
        subject.notifyObservers("Estado restaurado após undo"); 
    }
}
