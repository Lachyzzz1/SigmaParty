package NZXAPI.tools;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebScraper {

    public static void main(String[] args) {
        String[] stockCodes = {"FNZ", "ASD", "ASP", "BGP", "BRM", "CCC", "CEN", "CMO", "BLT", "ATM","NZG","NZO","MMH"};
        for(int x = 0; x < stockCodes.length; x++){
            System.out.println(getStockPrice(stockCodes[x]));
        }
        
    }

    public static BigDecimal getStockPrice(String stockCode) {
        String urlString = "https://www.nzx.com/instruments/" + stockCode;
        BigDecimal result = null;
        try {
            // Create a URL object from the URL string
            URL url = new URL(urlString);

            // Open a connection to the URL
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

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

                // Print the response
                //System.out.println("Response: " + response.toString());

                // Define the regex pattern to extract the stock price
                String regex = "<h3 class=\"InstrumentPriceAmount cMIYIY p-price\">\\$(.*?)</h3>";

                // Use Pattern and Matcher to find the stock price
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(response.toString());
                
                // Check if the pattern is found and extract the price
                if (matcher.find()) {
                    String stockPrice = matcher.group(1).substring(0);
                    result = new BigDecimal(stockPrice);
                    System.out.println("Close Stock Price: $" + stockPrice);
                } else {
                    System.out.println("Stock price not found.");
                }

            } else {
                System.out.println("GET request failed. Response code: " + responseCode);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    }


