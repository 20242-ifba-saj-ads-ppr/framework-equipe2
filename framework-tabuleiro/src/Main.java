
import facade.GameFacade;

public class Main {
    public static void main(String[] args) {
        GameFacade game = new GameFacade();
        
        game.setupGame(8, 8);
        
        game.divideBoard();
        
    }
}
