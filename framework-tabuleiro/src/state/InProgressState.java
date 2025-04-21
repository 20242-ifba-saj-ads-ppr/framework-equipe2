package state;

import controller.GameController;

public class InProgressState implements GameState {
    @Override public void start(GameController game) { /* já iniciado */ }
    @Override public void play(GameController game) { game.doPlayLoop(); }
    @Override public void end(GameController game) { game.doEnd(); game.setState(new EndedState()); }
}