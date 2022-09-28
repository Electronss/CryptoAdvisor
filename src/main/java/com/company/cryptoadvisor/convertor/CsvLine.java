package com.company.cryptoadvisor.convertor;

import com.opencsv.bean.CsvBindByName;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CsvLine {
    @CsvBindByName(column = "timestamp")
    protected long timeStamp;

    @CsvBindByName
    protected String symbol;

    @CsvBindByName
    protected BigDecimal price;
}
