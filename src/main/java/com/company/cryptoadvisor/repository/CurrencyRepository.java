package com.company.cryptoadvisor.repository;

import com.company.cryptoadvisor.model.Currency;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CurrencyRepository extends CrudRepository<Currency, Long> {

    @Query(
            value = "Select  MIN(PRICE) AS PRICE,  symbol From CURRENCY " +
                    " where month(time_stamp) = (Select Month (Select time_stamp From CURRENCY ORDER BY time_stamp  desc limit 1) month) GROUP BY SYMBOL",
            nativeQuery = true)
    List<Currency> getMinPriceForMonth();

    @Query(
            value = "Select  MAX(PRICE) AS PRICE,  symbol From CURRENCY " +
                    " where month(time_stamp) = (Select Month (Select time_stamp From CURRENCY ORDER BY time_stamp  desc limit 1) month) GROUP BY SYMBOL",
            nativeQuery = true)
    List<Currency> getMaxPriceForMonth();


    @Query(value = "Select Distinct(SYMBOL) From Currency",
            nativeQuery = true)
    List<String> getUnicSymbolCodes();


    Currency findFirstBySymbolOrderByTimeStampDesc(String symbolCode);

    Currency findFirstBySymbolOrderByTimeStampAsc(String symbolCode);

    //min
    Currency findFirstBySymbolOrderByPriceAsc(String symbolCode);
    //max
    Currency findFirstBySymbolOrderByPriceDesc(String symbolCode);

    List<Currency> findAllBySymbolIsNotNull();

}
