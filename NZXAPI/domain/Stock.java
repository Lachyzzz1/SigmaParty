package NZXAPI.domain;
import java.math.BigDecimal;
import NZXAPI.tools.WebScraper;

public class Stock extends Instrument {

    private BigDecimal currentPrice;
    private BigDecimal openPrice;
    private Integer volumeTraded;
    private BigDecimal priceToEquity;
    private BigDecimal netTangibleAssets;
    private Integer marketCap;

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

    
    
}
