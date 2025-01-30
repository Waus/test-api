package pl.tmobile.recruitment.steps;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pl.tmobile.recruitment.api.NBPClient;
import pl.tmobile.recruitment.model.CurrencyRate;
import pl.tmobile.recruitment.model.ExchangeRateTable;
import pl.tmobile.recruitment.service.ExchangeRateService;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class StepDefinitions {
    private static final Logger logger = LogManager.getLogger(StepDefinitions.class);

    private static ExchangeRateTable exchangeRateTable;
    private static ExchangeRateService exchangeRateService;

    @When("Pobieram kursy walut")
    public void fetchExchangeRates() throws IOException {
        NBPClient client = new NBPClient();
        exchangeRateTable = client.fetchExchangeRates();
        assertNotNull(exchangeRateTable, "Nie udało się pobrać danych z API NBP");
        exchangeRateService = new ExchangeRateService(exchangeRateTable);
    }

    @Then("Wyświetlam kurs dla waluty o kodzie {string}")
    public void displayExchangeRateForCurrencyCode(String code) {
        assertNotNull(exchangeRateService, "Serwis kursów walut nie został zainicjalizowany");
        assertNotNull(exchangeRateTable, "Tabela kursów nie została załadowana");

        CurrencyRate rate = exchangeRateService.getRateByCode(code);
        assertNotNull(rate, "Nie znaleziono kursu dla waluty: " + code);

        logger.info("Kurs dla waluty o kodzie {}: {}", code, rate.getMid());
    }

    @Then("Wyświetlam kurs dla waluty o nazwie {string}")
    public void displayExchangeRateForCurrencyName(String currencyName) {
        assertNotNull(exchangeRateService, "Serwis kursów walut nie został zainicjalizowany");
        assertNotNull(exchangeRateTable, "Tabela kursów nie została załadowana");

        CurrencyRate rate = exchangeRateService.getRateByName(currencyName);
        assertNotNull(rate, "Nie znaleziono kursu dla waluty: " + currencyName);

        logger.info("Kurs dla waluty {} ({}) wynosi: {}", currencyName, rate.getCode(), rate.getMid());
    }

    @Then("Wyświetlam waluty o kursie powyżej {double}")
    public void displayCurrenciesWithRateAbove(double value) {
        assertNotNull(exchangeRateService, "Serwis kursów walut nie został zainicjalizowany");
        assertNotNull(exchangeRateTable, "Tabela kursów nie została załadowana");

        List<CurrencyRate> ratesAbove = exchangeRateService.getRatesAbove(value);

        if (ratesAbove.isEmpty()) {
            logger.warn("Brak walut o kursie powyżej {}", value);
        } else {
            logger.info("Waluty o kursie powyżej {}:", value);
            ratesAbove.forEach(rate -> logger.info("{} ({}) - {}", rate.getCode(), rate.getCurrency(), rate.getMid()));
        }
    }

    @Then("Wyświetlam waluty o kursie poniżej {double}")
    public void displayCurrenciesWithRateBelow(double value) {
        assertNotNull(exchangeRateService, "Serwis kursów walut nie został zainicjalizowany");
        assertNotNull(exchangeRateTable, "Tabela kursów nie została załadowana");

        List<CurrencyRate> ratesBelow = exchangeRateService.getRatesBelow(value);

        if (ratesBelow.isEmpty()) {
            logger.warn("Brak walut o kursie poniżej {}", value);
        } else {
            logger.info("Waluty o kursie poniżej {}:", value);
            ratesBelow.forEach(rate -> logger.info("{} ({}) - {}", rate.getCode(), rate.getCurrency(), rate.getMid()));
        }
    }
}
