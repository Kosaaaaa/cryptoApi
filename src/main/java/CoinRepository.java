import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.OptionalDouble;

public class CoinRepository {
    private final List<Coin> coinList = new ArrayList<>();

    public CoinRepository() {
    }

    public void addCoin(Coin coin) {
        coinList.add(coin);
    }

    public double getAvgPrice() {
        double avg = 0;
        OptionalDouble avgOptional = coinList.stream().mapToDouble(Coin::getPriceUsd).average();
        if (avgOptional.isPresent()) {
            avg = avgOptional.getAsDouble();
        }
        return avg;
    }


    public String getHighestChangePercent() {
        Coin coin = coinList.stream().max(Comparator.comparing(Coin::getChangePercent24Hr)).get();
        return String.format("%s: %s", coin.getName(), coin.getChangePercent24Hr());
    }

    public List<Coin> getCoinList() {
        return coinList;
    }

    @Override
    public String toString() {
        return "CoinRepository{" +
                "coinList=" + coinList +
                '}';
    }
}
