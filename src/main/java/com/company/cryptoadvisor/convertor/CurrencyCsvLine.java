package com.company.cryptoadvisor.convertor;

public class CurrencyCsvLine extends CsvLine {

    @Override
    public String toString() {
        return "CurrencyCsvLine{" +
                "timeStamp=" + timeStamp +
                ", symbol='" + symbol + '\'' +
                ", price=" + price +
                '}';
    }
}
