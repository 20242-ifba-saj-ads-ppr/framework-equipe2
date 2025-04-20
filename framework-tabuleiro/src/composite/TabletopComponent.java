

public interface TabletopComponent {
    String operation();
    
    
    default void add(TabletopComponent component) {
        throw new UnsupportedOperationException("Operação add não suportada.");
    }
    
    default void remove(TabletopComponent component) {
        throw new UnsupportedOperationException("Operação remove não suportada.");
    }
    
    default TabletopComponent getChild(int index) {
        throw new UnsupportedOperationException("Operação getChild não suportada.");
    }
}
