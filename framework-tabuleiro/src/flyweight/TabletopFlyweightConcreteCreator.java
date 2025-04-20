package flyweight;

import java.util.HashMap;

public class TabletopFlyweightConcreteCreator extends TabletopFlyweightFactory {

    public TabletopFlyweightConcreteCreator() {
        flyweights = new HashMap<>();
    }

    @Override
    public TabletopFlyweightProduct getFlyweight(String key) {
        if (!flyweights.containsKey(key)) {
            flyweights.put(key, new TabletopFlyweightConcreteProduct(key));
        }
        return flyweights.get(key);
    }

    @Override
    public String[] listFlyweights() {
        return flyweights.keySet().toArray(new String[0]);
    }
}
