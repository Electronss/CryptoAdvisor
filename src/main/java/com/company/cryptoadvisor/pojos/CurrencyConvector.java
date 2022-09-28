package com.company.cryptoadvisor.pojos;

import com.company.cryptoadvisor.model.Currency;
import org.springframework.stereotype.Component;

@Component
public class CurrencyConvector {

    public CurrencyWrapper convert(Currency currency){
        return new CurrencyWrapper(currency.getTimeStamp(), currency.getSymbol(), currency.getPrice());
    }
}
