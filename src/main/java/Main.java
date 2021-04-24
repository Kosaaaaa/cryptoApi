import org.json.simple.parser.ParseException;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        ApiConnector client = new ApiConnector("https://api.coincap.io/v2");
        try {
            String[] portfolioIds = {"bitcoin", "ethereum", "xrp", "chainlink", "polkadot", "ravencoin", "iostoken", "cosmos"};
            CoinRepository portfolioRepository = new CoinRepository();

            for (String coin_id : portfolioIds) {
                portfolioRepository.addCoin(client.getCoinInfo(coin_id));
            }
            System.out.println(portfolioRepository);
            System.out.println(portfolioRepository.getAvgPrice());
            System.out.println(portfolioRepository.getHighestChangePercent());
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

    }
}
