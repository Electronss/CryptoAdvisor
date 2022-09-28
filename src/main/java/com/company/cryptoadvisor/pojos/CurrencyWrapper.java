package com.company.cryptoadvisor.pojos;

import com.company.cryptoadvisor.model.Currency;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Timestamp;


@Getter
@Setter
public class CurrencyWrapper extends Currency {

    private BigDecimal normalizedRange;

    public CurrencyWrapper(Timestamp timeStamp, String symbol, BigDecimal price) {
        super(timeStamp, symbol, price);
    }

    public CurrencyWrapper(Timestamp timeStamp, String symbol, BigDecimal price, BigDecimal normalizedRange) {
        super(timeStamp, symbol, price);
        this.normalizedRange = normalizedRange;
    }


}
