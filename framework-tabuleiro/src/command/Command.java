package command;

public interface Command {
    boolean execute();
    
    void store();
    
    String serialize();
}
