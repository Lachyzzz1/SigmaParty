package NZXAPI.applications;

import java.util.Scanner;

import NZXAPI.domain.Stock;

public class CommandLineApplication {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the ticker of the stock you want information for or press 'q' to quit:  ");
        String ticker = scanner.nextLine();
        while (!ticker.equals("q")) {

            Stock stock = new Stock(ticker);
            System.out.println(stock);
            System.out.println("Enter the ticker of the stock you want information for or press 'q' to quit:  ");
            ticker = scanner.nextLine();
        }
        scanner.close();
    }
    
}
