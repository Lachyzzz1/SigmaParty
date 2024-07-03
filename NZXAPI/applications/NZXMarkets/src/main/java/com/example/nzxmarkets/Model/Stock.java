package com.example.nzxmarkets.Model;
import com.example.nzxmarkets.Service.WebScraper;
import com.example.nzxmarkets.Service.Instrument;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.math.BigDecimal;
import java.math.BigInteger;

public class Stock extends Instrument {
    private BigDecimal currentPrice;
    private BigDecimal openPrice;

    public BigDecimal getOpenPrice() {
        return openPrice;
    }

    public Integer getVolumeTraded() {
        return volumeTraded;
    }

    public BigDecimal getPriceToEquity() {
        return priceToEquity;
    }

    public BigDecimal getNetTangibleAssets() {
        return netTangibleAssets;
    }

    private Integer volumeTraded;
    private BigDecimal priceToEquity;
    private BigDecimal netTangibleAssets;
    private BigInteger marketCap;
    private BigDecimal priceChange;

    public Stock(String ticker) {
        super(ticker);
        refreshData();
    }

    public void refreshData() {
        refresh();
        this.currentPrice = getScraper().getStockPrice();
        this.openPrice = getScraper().getOpenPrice();
        this.volumeTraded = getScraper().getVolumeTraded();
        this.priceToEquity = getScraper().getPriceToEquity();
        this.netTangibleAssets = getScraper().getNetTangibleAssets();
        this.marketCap = getScraper().getMarketCap();
        this.priceChange = getScraper().getPriceChange();
    }

    public String toString() {
        return "Stock: " + getName() + "\n" +
                "Ticker: " + getTicker() + "\n" +
                "ISIN: " + getISIN() + "\n" +
                "Type: " + getType() + "\n" +
                "Current Price: " + currentPrice + "\n" +
                "Open Price: " + openPrice + "\n" +
                "Volume Traded: " + volumeTraded + "\n" +
                "Price to Equity: " + priceToEquity + "\n" +
                "Net Tangible Assets: " + netTangibleAssets + "\n" +
                "Market Cap: " + marketCap + "\n";
    }

    public BigDecimal getCurrentPrice(){
        return currentPrice;
    }
    public BigInteger getMarketCap() {
        return marketCap;
    }

    public BigDecimal getPriceChange(){
        return priceChange;
    }

    public String toJson(){
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(this);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

}
