package com.company.cryptoadvisor.pojos;

import com.company.cryptoadvisor.convertor.CsvLine;

import java.util.ArrayList;
import java.util.List;

public class CsvTransfer {
    private List<CsvLine> csvList;

    public CsvTransfer() {
    }

    public void setCsvList(List<CsvLine> csvList) {
        this.csvList = csvList;
    }

    public List<CsvLine> getCsvList() {
        if (csvList != null) return csvList;
        return new ArrayList<CsvLine>();
    }
}
