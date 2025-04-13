package composite;

import java.util.ArrayList;
import java.util.List;
import flyweight.TabletopFlyweightProduct;

public class TabletopComposite implements TabletopComponent {
    private String name;
    private List<TabletopComponent> children;

    public TabletopComposite(String name) {
        this.name = name;
        this.children = new ArrayList<>();
    }
    
    @Override
    public void add(TabletopComponent component) {
        children.add(component);
    }
    
    public void add(TabletopFlyweightProduct flyweight, int x, int y) {
        this.add(new TabletopLeaf(flyweight, x, y));
    }
    
    @Override
    public void remove(TabletopComponent component) {
        children.remove(component);
    }
    
    @Override
    public TabletopComponent getChild(int index) {
        if(index >= 0 && index < children.size()) {
            return children.get(index);
        }
        throw new IndexOutOfBoundsException("Índice inválido: " + index);
    }

    @Override
    public String operation() {
        StringBuilder sb = new StringBuilder();
        sb.append("Composite: ").append(name).append(" [");
        for (int i = 0; i < children.size(); i++) {
            sb.append(children.get(i).operation());
            if (i < children.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
