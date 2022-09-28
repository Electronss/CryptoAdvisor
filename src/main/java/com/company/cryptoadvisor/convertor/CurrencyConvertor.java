package com.company.cryptoadvisor.convertor;

import com.company.cryptoadvisor.model.Currency;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Component
public class CurrencyConvertor {
    public List<Currency> convert(List<? extends CsvLine> csvBeanList) {
        List<Currency> currencyList = new ArrayList<>();
        csvBeanList.forEach(c -> currencyList.add(
                new Currency(new Timestamp(c.getTimeStamp()),
                        c.getSymbol(),
                        c.getPrice())));
    return currencyList;
    }

}
