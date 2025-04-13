package command;

import java.util.Stack;

public class CommandInvoker {
    private Stack<Command> commandHistory = new Stack<>();
    
    public boolean executeCommand(Command command) {
        boolean result = command.execute();
        if(result) {
            commandHistory.push(command);
        }
        return result;
    }
    
    public Command getLastCommand() {
        if (!commandHistory.isEmpty()) {
            return commandHistory.peek();
        }
        return null;
    }
}
