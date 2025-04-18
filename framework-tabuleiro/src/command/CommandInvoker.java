package command;

import memento.BoardMemento;
import java.util.Stack;

public class CommandInvoker {
    private Stack<Command> history = new Stack<>();
    private Stack<BoardMemento> mementos = new Stack<>();

    /**
     * Executa o comando salvando primeiro o memento.
     */
    public boolean executeCommand(Command cmd) {
        BoardMemento m = cmd.saveMemento();
        boolean result = cmd.execute();
        if (result) {
            history.push(cmd);
            mementos.push(m);
        }
        return result;
    }

    
    public void undo() {
        if (!history.isEmpty()) {
            Command cmd = history.pop();
            BoardMemento m = mementos.pop();
            cmd.restore(m);
        } else {
            System.out.println("Nada para desfazer.");
        }
    }

    public Command getLastCommand() {
        return history.isEmpty() ? null : history.peek();
    }
}
