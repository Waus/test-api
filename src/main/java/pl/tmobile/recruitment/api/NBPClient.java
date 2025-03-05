package pl.tmobile.recruitment.api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.tmobile.recruitment.model.ExchangeRateTable;

public class NBPClient {
    private static final Logger logger = LogManager.getLogger(NBPClient.class);
    private static final String API_URL = "http://api.nbp.pl/api/exchangerates/tables/A/?format=json";

    public ExchangeRateTable fetchExchangeRates() {
        logger.info("Wysyłam zapytanie GET do NBP API: {}", API_URL);

        Response response = RestAssured
                .given()
                .when()
                .get(API_URL)
                .then()
                .statusCode(200)
                .extract()
                .response();

        logger.info("Kod odpowiedzi: {}", response.statusCode());

        ExchangeRateTable[] tables = response.as(ExchangeRateTable[].class);

        if (tables.length == 0) {
            logger.error("Brak danych w odpowiedzi z NBP API");
            throw new RuntimeException("Brak danych w odpowiedzi z NBP API");
        }

        return tables[0];
    }
}
