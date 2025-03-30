package composite;
// Composite Pattern - GOF
// Objeto composto que pode conter folhas e outros compostos
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

    public void add(TabletopFlyweightProduct flyweight, int x, int y) {
        this.children.add(new TabletopLeaf(flyweight, x, y));
    }

    @Override
    public String operation() {
        StringBuilder sb = new StringBuilder();
        sb.append("Composite: ").append(name).append(" [");
        for (int i = 0; i < children.size(); i++) {
            sb.append(children.get(i).operation());
            if (i < children.size() - 1) sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }
}
