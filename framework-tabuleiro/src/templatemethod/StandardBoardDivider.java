package templatemethod;

import builder.TabletopProduct;
import composite.TabletopComposite;
import flyweight.TabletopFlyweightConcreteCreator;

public class StandardBoardDivider extends AbstractBoardDivider {

    @Override
    protected void addWhiteSide(TabletopProduct board, TabletopFlyweightConcreteCreator flyFactory) {
        // Cria o composite para o lado branco e adiciona tiles específicos (utilizando flyweights)
        TabletopComposite whiteSide = new TabletopComposite("Lado Branco");
        whiteSide.add(flyFactory.getFlyweight("Branco1"), 0, 0);
        whiteSide.add(flyFactory.getFlyweight("Branco2"), 0, 1);
        // Adiciona o lado branco à lista de componentes do tabuleiro
        board.getTiles().add(whiteSide);
        System.out.println("Adicionado o lado branco ao tabuleiro.");
    }

    @Override
    protected void addBlackSide(TabletopProduct board, TabletopFlyweightConcreteCreator flyFactory) {
        // Cria o composite para o lado preto e adiciona tiles específicos
        TabletopComposite blackSide = new TabletopComposite("Lado Preto");
        blackSide.add(flyFactory.getFlyweight("Preto1"), 1, 0);
        blackSide.add(flyFactory.getFlyweight("Preto2"), 1, 1);
        board.getTiles().add(blackSide);
        System.out.println("Adicionado o lado preto ao tabuleiro.");
    }
}
