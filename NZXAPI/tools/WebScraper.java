package NZXAPI.tools;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebScraper {


    private String url;
    private String htmlContents;

    public WebScraper(String url) {
        this.url = url;
        this.htmlContents = getHTMLContents(url); 
    }

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

    public String getHTMLContents(){
        return this.htmlContents;
    }

    public BigDecimal getStockPrice() {
       
         // Define the regex pattern to extract the stock price
        String regex = "<h3 class=\"InstrumentPriceAmount cMIYIY p-price\">\\$(.*?)</h3>";
        String changePattern = "<span class=\"PriceChangeAmount emeBBK p-price-change kGHgXH\">(.*?)</span>";

                // Use Pattern and Matcher to find the stock price
        Pattern pattern = Pattern.compile(regex);
        Pattern changeRegex = Pattern.compile(changePattern);

        Matcher matcher = pattern.matcher(this.htmlContents);
        Matcher changeMatcher = changeRegex.matcher(this.htmlContents);
        BigDecimal result = null;
                
                // Check if the pattern is found and extract the price
        if (matcher.find()) {
            String stockPrice = matcher.group(1).substring(0);
            result = new BigDecimal(stockPrice);
            System.out.println("Close Stock Price: $" + stockPrice);
        } else {
            System.out.println("Stock price not found.");
        }
        if (changeMatcher.find()) {
            System.out.println("Price Change: " + changeMatcher.group(1));
        } else {
            System.out.println("Price change not found");
        }

        return result;
    }
    


    public BigDecimal getPriceChange() {
       
        // Define the regex pattern to extract the stock price
       String changePattern = "<span class=\"PriceChangeAmount emeBBK p-price-change kGHgXH\">(.*?)</span>";

               // Use Pattern and Matcher to find the stock price
       Pattern changeRegex = Pattern.compile(changePattern);

       Matcher changeMatcher = changeRegex.matcher(this.htmlContents);
       BigDecimal result = null;
               
               // Check if the pattern is found and extract the price
      
       if (changeMatcher.find()) {
        String priceChange = changeMatcher.group(1).replace("$","");   
        System.out.println("Price Change: " + changeMatcher.group(1));
        result = new BigDecimal(priceChange);
       } else {
           System.out.println("Price change not found");
       }

       return result;
   }
   }


