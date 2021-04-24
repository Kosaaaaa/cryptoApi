import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Timestamp;
import java.util.Scanner;

public class ApiConnector {
    private final String baseUrl;

    public ApiConnector(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public Coin getCoinInfo(String coin_id) throws IOException, ParseException {
        String endpoint = String.format(this.baseUrl + "/assets/%s", coin_id);

        URL url = new URL(endpoint);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.connect();

        int responseCode = connection.getResponseCode();

        if (responseCode >= 400) {
            throw new RuntimeException(("Coin: " + coin_id + " Code: " + responseCode + " Response: " + connection.getResponseMessage()));
        } else {
            StringBuilder inline = new StringBuilder();
            Scanner scanner = new Scanner(url.openStream());
            while (scanner.hasNext()) {
                inline.append(scanner.nextLine());
            }
            scanner.close();

            JSONParser parser = new JSONParser();
            JSONObject json = (JSONObject) parser.parse(inline.toString());
            JSONObject data = (JSONObject) json.get("data");

            String id = (String) data.get("id");
            String name = (String) data.get("name");
            String symbol = (String) data.get("symbol");
            double priceUsd = Double.parseDouble((String) data.get("priceUsd"));
            double changePercent24Hr = Double.parseDouble((String) data.get("changePercent24Hr"));
            double volumeUsd24Hr = Double.parseDouble((String) data.get("volumeUsd24Hr"));
            Timestamp timestamp = new Timestamp((long) json.get("timestamp"));

            return new Coin(id, name, symbol, priceUsd, changePercent24Hr, volumeUsd24Hr, timestamp);
        }
    }
}
