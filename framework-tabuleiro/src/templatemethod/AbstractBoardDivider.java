package templatemethod;

import builder.TabletopProduct;
import flyweight.TabletopFlyweightConcreteCreator;

public abstract class AbstractBoardDivider {

  
    public final void divideBoard(TabletopProduct board, TabletopFlyweightConcreteCreator flyFactory) {
        setInitialDivision(board);
        addWhiteSide(board, flyFactory);
        addBlackSide(board, flyFactory);
        finalizeDivision(board);
    }
    
    
    protected void setInitialDivision(TabletopProduct board) {
        System.out.println("Iniciando divisão do tabuleiro.");
    }
  
    protected abstract void addWhiteSide(TabletopProduct board, TabletopFlyweightConcreteCreator flyFactory);
    
   
    protected abstract void addBlackSide(TabletopProduct board, TabletopFlyweightConcreteCreator flyFactory);
    
   
    protected void finalizeDivision(TabletopProduct board) {
        System.out.println("Divisão do tabuleiro concluída.");
    }
}
