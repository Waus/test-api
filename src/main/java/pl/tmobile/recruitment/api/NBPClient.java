package pl.tmobile.recruitment.api;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.tmobile.recruitment.model.ExchangeRateTable;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class NBPClient {
    private static final Logger logger = LogManager.getLogger(NBPClient.class);
    private static final String API_URL = "http://api.nbp.pl/api/exchangerates/tables/A/?format=json";

    public ExchangeRateTable fetchExchangeRates() throws IOException {
        URL url = new URL(API_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        int responseCode = conn.getResponseCode();
        logger.info("Kod odpowiedzi: {}", responseCode);

        if (responseCode != 200) {
            logger.error("Nie udało się pobrać danych NBP API! Status: {}", responseCode);
            throw new RuntimeException("Nie udało się pobrać danych NBP API");
        }

        Scanner scanner = new Scanner(url.openStream());
        String response = scanner.useDelimiter("\\A").next();
        scanner.close();

        ObjectMapper mapper = new ObjectMapper();
        ExchangeRateTable[] tables = mapper.readValue(response, ExchangeRateTable[].class);

        return tables.length > 0 ? tables[0] : null;
    }
}
