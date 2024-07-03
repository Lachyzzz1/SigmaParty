package NZXAPI.tools;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebScraper {


    private String url;
    private String htmlContents;
    
    /**
     * Constructor for the WebScraper class
     * @param url URL to scrape
     */
    public WebScraper(String url) {
        this.url = url;
        this.htmlContents = getHTMLContents(url); 
    }
    /**
     * Get the HTML contents of the URL
     * @param url URL to get the HTML contents from
     * @return HTML contents
     */
    public static String getHTMLContents(String url){
        String result = null;
        try {
            // Create a URL object from the URL string
            URL urlObject = new URL(url);

            // Open a connection to the URL
            HttpURLConnection connection = (HttpURLConnection) urlObject.openConnection();

            // Set the request method to GET
            connection.setRequestMethod("GET");

            // Get the response code
            int responseCode = connection.getResponseCode();

            // If the response code is 200 (HTTP_OK), read the response
            if (responseCode == HttpURLConnection.HTTP_OK) {
                BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String inputLine;
                StringBuilder response = new StringBuilder();

                // Read each line from the response and append it to the response StringBuilder
                while ((inputLine = in.readLine()) != null) {
                    response.append(inputLine);
                }
                in.close();
                
                result = response.toString();
            } else {
                System.out.println("GET request failed. Response code: " + responseCode);
            }

        } catch (Exception e) {
            e.printStackTrace();
    }
    return result;
    }
    /**
     * Get the HTML contents of the URL for the WebScraper object
     * @return HTML contents
     */
    public String getHTMLContents(){
        return this.htmlContents;
    }
    /**
     * Get the current price of the stock
     * Using the regex pattern to extract the stock price from the HTML contents
     * @return current price
     */
    public BigDecimal getStockPrice() {
       
         // Define the regex pattern to extract the stock price
        String regex = "<h3 class=\"InstrumentPriceAmount cMIYIY p-price\">\\$(.*?)</h3>";

                // Use Pattern and Matcher to find the stock price
        Pattern pattern = Pattern.compile(regex);

        Matcher matcher = pattern.matcher(this.htmlContents);
        BigDecimal result = null;
                
                // Check if the pattern is found and extract the price
        if (matcher.find()) {
            try{
            String stockPrice = matcher.group(1).substring(0);
            result = new BigDecimal(stockPrice);
            } catch (Exception e) {
                return null;
            }
        } else {
            System.out.println("Stock price not found.");
        }
        

        return result;
    }
    

    /**
     * Get the 24hr price change of the stock
     * Using the regex pattern to extract the price change from the HTML contents
     * @return price change
     */
    public BigDecimal getPriceChange() {
       
        // Define the regex pattern to extract the stock price
       String changePattern = "<span class=\"PriceChangeAmount emeBBK p-price-change kGHgXH\">(.*?)</span>";

               // Use Pattern and Matcher to find the stock price
       Pattern changeRegex = Pattern.compile(changePattern);

       Matcher changeMatcher = changeRegex.matcher(this.htmlContents);
       BigDecimal result = null;
               
               // Check if the pattern is found and extract the price
      
       if (changeMatcher.find()) {
        try{
        String priceChange = changeMatcher.group(1).replace("$","");   
        result = new BigDecimal(priceChange);
        } catch (Exception e) {
            return null;
        }
       } else {
           System.out.println("Price change not found");
       }

       return result;
   }

   /**
    * Get the earnings per share of the stock
    * Using the regex pattern to extract the earnings per share from the HTML contents
    * @return earnings per share
    */
   public BigDecimal getEarningsPerShare(){
        String regex = "<th>EPS</th><td>(.*?)</td>";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(this.htmlContents);
        BigDecimal result = null;
        if (matcher.find()) {
            try{
            String earningsPerShare = matcher.group(1).replace("$", "");
            result = new BigDecimal(earningsPerShare);
            } catch (Exception e) {
                return null;
            }
        } else {
            System.out.println("Earnings Per Share not found.");
        }
        return result;
   }

   public BigDecimal getOpenPrice(){
        String regex = "<th>Open</th><td>(.*?)</td>";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(this.htmlContents);
        BigDecimal result = null;
        if (matcher.find()) {
            try{
            String openPrice = matcher.group(1).replace("$", "");
            result = new BigDecimal(openPrice);
            } catch (Exception e) {
                return null;
            }
        } else {
            System.out.println("Open Price not found.");
        }
        return result;
   }

   public Integer getVolumeTraded(){
        String regex = "<th>Volume</th><td>(.*?)</td>";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(this.htmlContents);
        Integer result = null;
        if (matcher.find()) {
            try{
            String volumeTraded = matcher.group(1).replace(",", "");
            result = Integer.parseInt(volumeTraded);
            } catch (Exception e) {
                return null;
            }
        } else {
            System.out.println("Volume Traded not found.");
        }
        return result;
   }

   public BigDecimal getPriceToEquity(){
        String regex = "<th>P/E</th><td>(.*?)</td>";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(this.htmlContents);
        BigDecimal result = null;
        if (matcher.find()) {
            try{
            String priceToEquity = matcher.group(1);
            result = new BigDecimal(priceToEquity);
            } catch (Exception e) {
                return null;
            }
        } else {
            System.out.println("Price to Equity not found.");
        }
        return result;
   }

   public BigDecimal getNetTangibleAssets(){
        String regex = "<th>NTA</th><td>(.*?)</td>";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(this.htmlContents);
        BigDecimal result = null;
        if (matcher.find()) {
            try{
            String netTangibleAssets = matcher.group(1).replace("$", "");
            result = new BigDecimal(netTangibleAssets);
            } catch (Exception e) {
                return null;
            }
        } else {
            System.out.println("Net Tangible Assets not found.");
        }
        return result;
   }

   public BigDecimal getDividendYield(){
        String regex = "<th>Gross Div Yield</th><td>(.*?)</td>";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(this.htmlContents);
        BigDecimal result = null;
        if (matcher.find()) {
            try{
            String dividendYield = matcher.group(1).replace("%", "");
            result = new BigDecimal(dividendYield);
            } catch (Exception e) {
                return null;
            }
        } else {
            System.out.println("Dividend Yield not found.");
        }
        return result;
   }

   public BigInteger getMarketCap(){
        String regex = "<th>Capitalisation \\(000s\\)</th><td>\\$(.*?)</td>";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(this.htmlContents);
        BigInteger result = null;
        long multi = 1000;
        BigInteger multiplier = BigInteger.valueOf(multi);
        if (matcher.find()) {
            try{
            Integer otherResult = Integer.valueOf(matcher.group(1).replace("$", "").replace(",", ""));
            result = BigInteger.valueOf(otherResult);
            return result.multiply(multiplier);
            } catch (Exception e) {
                return null;
            }
        } else {
            System.out.println("Market Cap not found.");
        }
        return null;
   }

   public String getName(){
    String regex = "<th>Instrument Name</th>\\s*<td><a class=\"Link gVvkzt kDqtJz\"[^>]*>([^<]+)</a></td>";
    Pattern pattern = Pattern.compile(regex);
    Matcher matcher = pattern.matcher(this.htmlContents);
    String result = null;
    if (matcher.find()) {
        try{
        result = matcher.group(1).trim(); 
        } catch (Exception e) {
            return null;
        }
    } else {
        System.out.println("Name not found.");
    }
    return result;
}

    public String getISIN(){
     String regex = "<th>ISIN</th><td>(.*?)</td>";
     Pattern pattern = Pattern.compile(regex);
     Matcher matcher = pattern.matcher(this.htmlContents);
     String result = null;
     if (matcher.find()) {
          result = matcher.group(1).trim(); 
     } else {
          System.out.println("ISIN not found.");
     }
     return result;
    }
    
    public String getType(){
     String regex = "<th>Type</th>\\s*<td>([^<]+)</td>";
     Pattern pattern = Pattern.compile(regex);
     Matcher matcher = pattern.matcher(this.htmlContents);
     String result = null;
     if (matcher.find()) {
          result = matcher.group(1).trim(); 
     } else {
          System.out.println("Type not found.");
     }
     return result;
    }
    
    public String getSector(){
     String regex = "<th>Sector</th><td>(.*?)</td>";
     Pattern pattern = Pattern.compile(regex);
     Matcher matcher = pattern.matcher(this.htmlContents);
     String result = null;
     if (matcher.find()) {
          result = matcher.group(1).trim(); 
     } else {
          System.out.println("Sector not found.");
     }
     return result;
    }
    
    public String getIndustry(){
     String regex = "<th>Industry</th><td>(.*?)</td>";
     Pattern pattern = Pattern.compile(regex);
     Matcher matcher = pattern.matcher(this.htmlContents);
     String result = null;
     if (matcher.find()) {
          result = matcher.group(1).trim(); 
     } else {
          System.out.println("Industry not found.");
     }
     return result;
    }
    
    public String getWebsite(){
     String regex = "<th>Website</th><td><a href=\"(.*?)\"";
     Pattern pattern = Pattern.compile(regex);
     Matcher matcher = pattern.matcher(this.htmlContents);
     String result = null;
     if (matcher.find()) {
          result = matcher.group(1).trim(); 
     } else {
          System.out.println("Website not found.");
     }
     return result;
}
}

