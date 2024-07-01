package NZXAPI.tools;

import NZXAPI.domain.Instrument;
import NZXAPI.domain.Stock;

public class Test {

    public static void main(String[] args) {
        String[] tickers = {
            "2CC", "AFC", "AFI", "AFT", "AGG", "AGL", "AIA", "AIR", "ALD", "ALF", "ANZ",
            "AOF", "APA", "APL", "ARB", "ARG", "ARV", "ASD", "ASF", "ASP", "ASR", "ATM",
            "AUE", "AUS", "BAI", "BFG", "BGP", "BIF", "BIT", "BLT", "BOT", "BPG", "BRM",
            "BRMWH", "BRW", "CBD", "CCC", "CDI", "CEN", "CHI", "CMO", "CNU", "CO2", "CRP",
            "CVT", "DGL", "DIV", "DOW", "EBO", "EMF", "EMG", "ENS", "ERD", "ESG", "EUF",
            "EUG", "FBU", "FCT", "FNZ", "FPH", "FRW", "FSF", "FWL", "GBF", "GEN", "GFI",
            "GFL", "GGB", "GMT", "GNE", "GPR", "GSH", "GTK", "GXH", "HFL", "HGH", "HLG",
            "IFT", "IKE", "INF", "IPL", "IPR", "JPN", "KFL", "KFLWH", "KMD", "KPG", "LIC",
            "LIV", "MCK", "MCKPA", "MCY", "MDZ", "MEE", "MEL", "MFB", "MFT", "MHJ", "MLN",
            "MLNWG", "MMH", "MNW", "MOV", "MPG", "MWE", "MZY", "NGB", "NPF", "NPH", "NTL",
            "NWF", "NZB", "NZC", "NZG", "NZK", "NZL", "NZLWA", "NZM", "NZO", "NZX", "OCA",
            "OZY", "PCT", "PEB", "PFI", "PGW", "PHL", "PLP", "POT", "PYS", "RAD", "RAK",
            "RBD", "RTO", "RUA", "RYM", "SAN", "SCL", "SCT", "SDL", "SEK", "SKC", "SKL",
            "SKO", "SKT", "SML", "SPG", "SPK", "SPN", "SPY", "STU", "SUM", "SVR", "TAH",
            "TEM", "TGG", "THL", "TNZ", "TRA", "TRU", "TWF", "TWH", "TWL", "TWR", "USA",
            "USF", "USG", "USH", "USM", "USS", "USV", "VCT", "VGL", "VHP", "VNT", "VSL",
            "VTL", "WBC", "WCO", "WHS", "WIN"
        };
        for (String ticker : tickers) {
            Stock stock = new Stock(ticker);
            System.out.println(stock);
        }
        Stock stockOne = new Stock("AIA");
        System.out.println(stockOne);
        
            
        
            
        //WebScraper scraper = new WebScraper("https://www.nzx.com/instruments/ARV");
        //System.out.println(scraper.getStockPrice());
       // System.out.println(scraper.getOpenPrice());
        //System.out.println(scraper.getMarketCap());
    }
    
}
