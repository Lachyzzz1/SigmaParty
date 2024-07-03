package com.example.nzxmarkets.Service;
import org.springframework.stereotype.Service;
import com.example.nzxmarkets.Model.Stock;

import java.math.BigDecimal;
import java.math.BigInteger;
@Service
public class StockService {
    public Stock getStockData(String ticker){

        return new Stock(ticker);
    }

}

