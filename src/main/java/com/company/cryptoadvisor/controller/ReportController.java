package com.company.cryptoadvisor.controller;

import com.company.cryptoadvisor.model.Currency;
import com.company.cryptoadvisor.pojos.CurrencyConvector;
import com.company.cryptoadvisor.pojos.CurrencyWrapper;
import com.company.cryptoadvisor.service.ReportService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

@Slf4j
@RestController
@ApiOperation("File Upload")
@RequestMapping(value = "api/report/currency")
public class ReportController extends AbstractController {

    private ReportService service;
    private CurrencyConvector convector;

    public ReportController(ReportService service, CurrencyConvector convector) {
        this.service = service;
        this.convector = convector;
    }

    /**
     * TODO
     * @param symbol
     * @return
     */
    @GetMapping(value = "/max")
    public ResponseEntity<ResponseMessage> getCurrencyWithMaxPrice(@RequestParam String symbol) {
        Currency currency = service.getCurrencyWithMaxPrice(symbol);
        CurrencyWrapper responce = convector.convert(currency);
        return getResponseMessageResponseEntity(responce, symbol);
    }

    /**
     * TODO
     * @param symbol
     * @return
     */
    @GetMapping(value = "/min")
    public ResponseEntity<ResponseMessage> getCurrencyWithMinPrice(@RequestParam String symbol) {
        Currency currency = service.getCurrencyWithMinPrice(symbol);
        CurrencyWrapper responce = convector.convert(currency);
        return getResponseMessageResponseEntity(responce, symbol);
    }

    @GetMapping(value = "/oldest")
    public ResponseEntity<ResponseMessage> getOldestCurrency(@RequestParam String symbol) {
        Currency currency = service.getOldestCurrency(symbol);
        CurrencyWrapper responce = convector.convert(currency);
        return getResponseMessageResponseEntity(responce, symbol);
    }

    @GetMapping(value = "/newest")
    public ResponseEntity<ResponseMessage> getNewestCurrency(@RequestParam String symbol) {
        Currency currency = service.getNewestCurrency(symbol);
        CurrencyWrapper responce = convector.convert(currency);
        return getResponseMessageResponseEntity(responce, symbol);
    }


    @GetMapping(value = "/normalized-range")
    public List<CurrencyWrapper> calcNormalizedRangeOfAllCurrency() {
        return service.calcNormalizedRangeOfAllCurrency();
    }

    @GetMapping(value = "/normalized-range-by-date")
    public List<CurrencyWrapper> calcNormalizedRangeOfAllCurrency(@RequestParam String date) {
        return service.calcHighestNormalizedRangeForSpecificDay(date);
    }
}
