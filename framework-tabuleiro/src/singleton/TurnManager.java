package singleton;

import context.PlayerSide;

public class TurnManager {

    private static TurnManager instance;

    private PlayerSide currentSide;

    private TurnManager() {
        this.currentSide = PlayerSide.WHITE; 
    }


    public static TurnManager getInstance() {
        if (instance == null) {
            instance = new TurnManager();
        }
        return instance;
    }

    
    public PlayerSide getCurrentSide() {
        return currentSide;
    }


    public void switchTurn() {
        currentSide = (currentSide == PlayerSide.WHITE) ? PlayerSide.BLACK : PlayerSide.WHITE;
        System.out.println("✔️ Agora é a vez do jogador: " + currentSide);
    }

   
    public boolean isCurrentPlayer(PlayerSide side) {
        return this.currentSide == side;
    }

   
    public void reset() {
        this.currentSide = PlayerSide.WHITE;
        System.out.println("Turno reiniciado: começa o jogador branco.");
    }
}
