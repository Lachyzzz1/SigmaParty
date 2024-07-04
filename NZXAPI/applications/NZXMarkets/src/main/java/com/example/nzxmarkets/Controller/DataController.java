package com.example.nzxmarkets.Controller;


import com.example.nzxmarkets.Model.Stock;
import com.example.nzxmarkets.Service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataController {

    @Autowired
    private StockService stockService;

    @GetMapping("/stock")
    public Stock getStock(@RequestParam String ticker) {
    return stockService.getStockData(ticker);
}


}
