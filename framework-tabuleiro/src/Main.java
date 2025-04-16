
import facade.GameFacade;

public class Main {
    public static void main(String[] args) {
        GameFacade game = new GameFacade();
        
        // 1) Configura o tabuleiro
        game.setupGame(8, 8);
        
        // 2) Divide em lado branco e preto
        game.divideBoard();
        
        // 3) Executa algum movimento
        game.executeMove("Leão", 0, 0, 1, 0);
    }
}
