package state;

import controller.GameController;

public class EndedState implements GameState {
    @Override public void start(GameController game) { System.out.println("Jogo já terminou."); }
    @Override public void play(GameController game) { System.out.println("Jogo encerrado."); }
    @Override public void end(GameController game) { /* já encerrado */ }
}