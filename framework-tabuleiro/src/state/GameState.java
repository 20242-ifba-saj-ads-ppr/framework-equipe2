package state;

import controller.GameController;

public interface GameState {
    void start(GameController game);
    void play(GameController game);
    void end(GameController game);
}