import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String[] portfolioIds = {"bitcoin", "ethereum", "xrp", "chainlink", "polkadot", "ravencoin", "iostoken", "cosmos"};
        ApiConnector client = new ApiConnector("https://api.coincap.io/v2");

        System.out.println("Select: ");
        System.out.println("1. Get Portfolio Data");
        System.out.println("2. Get specific Coin Data");
        System.out.println("3. Get Average Price from Portfolio");
        System.out.println("4. Get Highest 24H percent change");

        Scanner scanner = new Scanner(System.in);
        int selected_option = scanner.nextInt();


        try {
            switch (selected_option) {
                case 1:
                    option1(client, portfolioIds);
                    break;
                case 2:
                    option2(client);
                    break;
                case 3:
                    option3(client, portfolioIds);
                    break;
                case 4:
                    option4(client, portfolioIds);
                    break;
            }
        } catch (IOException | ParseException e) {
            e.printStackTrace();
        }

    }

    private static void option1(ApiConnector client, String[] portfolioIds) throws IOException, ParseException {
        CoinRepository portfolioRepository = new CoinRepository();
        for (String coin_id : portfolioIds) {
            portfolioRepository.addCoin(client.getCoinInfo(coin_id));
        }
        for (Coin coin : portfolioRepository.getCoinList()) {
            System.out.println(coin);
        }
    }

    private static void option2(ApiConnector client) throws IOException, ParseException {
        System.out.println("Type Coin ID: ");
        Scanner scanner2 = new Scanner(System.in);
        String coin_id = scanner2.nextLine();
        Coin coin = client.getCoinInfo(coin_id);
        System.out.println(coin);
    }

    private static void option3(ApiConnector client, String[] portfolioIds) throws IOException, ParseException {
        CoinRepository portfolioRepository = new CoinRepository();
        for (String coin_id : portfolioIds) {
            portfolioRepository.addCoin(client.getCoinInfo(coin_id));
        }
        System.out.println("$ " + portfolioRepository.getAvgPrice());
    }

    private static void option4(ApiConnector client, String[] portfolioIds) throws IOException, ParseException {
        CoinRepository portfolioRepository = new CoinRepository();
        for (String coin_id : portfolioIds) {
            portfolioRepository.addCoin(client.getCoinInfo(coin_id));
        }
        System.out.println(portfolioRepository.getHighestChangePercent() + " %");
    }
}
