package pl.tmobile.recruitment.service;

import pl.tmobile.recruitment.model.CurrencyRate;
import pl.tmobile.recruitment.model.ExchangeRateTable;

import java.util.List;
import java.util.stream.Collectors;

public class ExchangeRateService {
    private final ExchangeRateTable exchangeRateTable;

    public ExchangeRateService(ExchangeRateTable exchangeRateTable) {
        this.exchangeRateTable = exchangeRateTable;
    }

    public CurrencyRate getRateByCode(String code) {
        return exchangeRateTable.getRates().stream()
                .filter(rate -> rate.getCode().equalsIgnoreCase(code))
                .findFirst()
                .orElse(null);
    }

    public CurrencyRate getRateByName(String currencyName) {
        return exchangeRateTable.getRates().stream()
                .filter(rate -> rate.getCurrency().equalsIgnoreCase(currencyName))
                .findFirst()
                .orElse(null);
    }

    public List<CurrencyRate> getRatesAbove(double value) {
        return exchangeRateTable.getRates().stream()
                .filter(rate -> rate.getMid() > value)
                .collect(Collectors.toList());
    }

    public List<CurrencyRate> getRatesBelow(double value) {
        return exchangeRateTable.getRates().stream()
                .filter(rate -> rate.getMid() < value)
                .collect(Collectors.toList());
    }
}
