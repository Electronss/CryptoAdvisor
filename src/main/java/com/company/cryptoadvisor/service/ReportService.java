package com.company.cryptoadvisor.service;

import com.company.cryptoadvisor.model.Currency;
import com.company.cryptoadvisor.pojos.CurrencyWrapper;
import com.company.cryptoadvisor.repository.CurrencyRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

@Slf4j
@Service
public class ReportService {

    private final CurrencyRepository repository;
    private HashMap<String, BigDecimal> normalizedRange;

    public ReportService(CurrencyRepository repository) {
        this.repository = repository;
    }

    public Currency getOldestCurrency(String symbol) {
        return repository.findFirstBySymbolOrderByTimeStampDesc(symbol);
    }

    public Currency getNewestCurrency(String symbol) {
        return repository.findFirstBySymbolOrderByTimeStampAsc(symbol);
    }

    public Currency getCurrencyWithMinPrice(String symbol) {
        return repository.findFirstBySymbolOrderByPriceAsc(symbol);
    }

    public Currency getCurrencyWithMaxPrice(String symbol) {
        return repository.findFirstBySymbolOrderByPriceDesc(symbol);
    }

    //TODO gets data from DB by portion. No All at one time.
    public List<CurrencyWrapper> calcNormalizedRangeOfAllCurrency() {
        List<CurrencyWrapper> currencyWrappers = new ArrayList<>();

        normalizedRange = calcNormalizedRangeOfSymbols();
        List<Currency> currencyList = geSortedCurrency();

        currencyList.forEach(c -> currencyWrappers.add(
                new CurrencyWrapper(
                        c.getTimeStamp(),
                        c.getSymbol(),
                        c.getPrice(),
                        getNormalizedRange(c.getSymbol())
                ))
        );
        return currencyWrappers;
    }

    public List<CurrencyWrapper> calcHighestNormalizedRangeForSpecificDay(String date) {
        List<CurrencyWrapper> currencyWrappers = new ArrayList<>();

        //TODO
        return currencyWrappers;
    }

    private BigDecimal getNormalizedRange(String symbol) {
        return normalizedRange.get(symbol);
    }

    private HashMap<String, BigDecimal> calcNormalizedRangeOfSymbols() {
        HashMap<String, BigDecimal> hashMap = new HashMap<>();
        List<String> unicSymbol = repository.getUnicSymbolCodes();

        unicSymbol.forEach(s -> {
            BigDecimal max = getCurrencyWithMaxPrice(s).getPrice();
            BigDecimal min = getCurrencyWithMinPrice(s).getPrice();
            BigDecimal normalizedRange = (max.subtract(min)).divide(min, RoundingMode.valueOf(3));
            hashMap.put(s, normalizedRange);
        });
        return hashMap;
    }


    private List<Currency> geSortedCurrency(String dateString)  { //todo
        //TODO
        return null;
    }

    private List<Currency> geSortedCurrency() {
        List<Currency> currencyList = repository.findAllBySymbolIsNotNull();
        sortListDesc(currencyList);
        return currencyList;
    }

    private void sortListDesc(List<Currency> currencyList) {
        currencyList.sort(Comparator.comparing(Currency::getPrice).reversed());
        currencyList.sort(Comparator.comparing(Currency::getSymbol));
    }

}
