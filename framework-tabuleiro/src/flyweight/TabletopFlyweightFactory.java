package flyweight;
// Flyweight Pattern - GOF
// Interface da fábrica de flyweights
import java.util.Map;

public abstract class TabletopFlyweightFactory {
    protected Map<String, TabletopFlyweightProduct> flyweights;

    public abstract TabletopFlyweightProduct getFlyweight(String key);
    public abstract String[] listFlyweights();
}
