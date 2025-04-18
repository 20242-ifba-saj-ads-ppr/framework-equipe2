package command;

import memento.BoardMemento;

public interface Command {
    // Executa a ação
    boolean execute();

    // Serializa para log
    void store();

    String serialize();

    // Cria um memento do estado antes da execução
    BoardMemento saveMemento();

    // Restaura o estado a partir de um memento
    void restore(BoardMemento memento);
}
