package NZXAPI.domain;

import java.math.BigDecimal;

import NZXAPI.tools.WebScraper;

public class Instrument {
    
    private String ticker;
    private String name;
    private String ISIN;
    private String type;
    private WebScraper scraper;
    
    
    public Instrument(String ticker) {
        this.ticker = ticker;
        scraper = new WebScraper("https://www.nzx.com/instruments/" + this.ticker);
        this.name = scraper.getName();
        this.ISIN = scraper.getISIN();
        this.type = scraper.getType();
    }

    public String getTicker(){
        return this.ticker;
    }
    
    public String getName(){
        return this.name;
    }

    public String getISIN(){
        return this.ISIN;
    }
    
    public String getType(){
        return this.type;
    }

    public void refresh(){
        this.scraper = new WebScraper("https://www.nzx.com/instruments/" + this.ticker);
    }

    public WebScraper getScraper(){
        return this.scraper;
    }

}
