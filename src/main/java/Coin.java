import java.sql.Timestamp;

public class Coin {
    private final String id;
    private final String name;
    private final String symbol;
    private final double priceUsd;
    private final double changePercent24Hr;
    private final double volumeUsd24Hr;
    private final Timestamp timestamp;

    public Coin(String id, String name, String symbol, double priceUsd, double changePercent24Hr, double volumeUsd24Hr, Timestamp timestamp) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
        this.priceUsd = priceUsd;
        this.changePercent24Hr = changePercent24Hr;
        this.volumeUsd24Hr = volumeUsd24Hr;
        this.timestamp = timestamp;
    }

    public double getPriceUsd() {
        return priceUsd;
    }

    public  double getChangePercent24Hr() {
        return changePercent24Hr;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Coin{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", symbol='" + symbol + '\'' +
                ", priceUsd=" + priceUsd +
                ", changePercent24Hr=" + changePercent24Hr +
                ", volumeUsd24Hr=" + volumeUsd24Hr +
                ", timestamp=" + timestamp +
                '}';
    }
}
