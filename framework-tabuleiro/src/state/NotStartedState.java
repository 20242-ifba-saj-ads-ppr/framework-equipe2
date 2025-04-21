package state;

import controller.GameController;

public class NotStartedState implements GameState {
    @Override
    public void start(GameController game) {
        game.doStart();
        game.setState(new InProgressState());
    }
    @Override public void play(GameController game) {
        System.out.println("Jogo não iniciado. Use o comando 'start'.");
    }
    @Override public void end(GameController game) {
        System.out.println("Jogo não iniciado.");
    }
}
