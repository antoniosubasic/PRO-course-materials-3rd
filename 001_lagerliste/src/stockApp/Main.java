package stockApp;

import date.Date;
import stock.HiFoStockList;
import stock.StockList;
import stock.StockMovement;
import stock.ValuedStockMovement;

public class Main {
    public static void main(String[] args) {
        StockList stockList = new HiFoStockList();

        // Einlagern, Auslagern, Analysieren
        stockList.store(new ValuedStockMovement(new Date(2024, 1, 7), 100, 12));
        stockList.store(new ValuedStockMovement(new Date(2024, 1, 8), 100, 11));
        
        stockList.remove(new StockMovement(new Date(2024, 1, 9), 30));

        System.out.println("Lagerbestand:");
        System.out.println(stockList.getStockStatus());

        stockList.store(new ValuedStockMovement(new Date(2024, 1, 10), 100, 13));
        
        stockList.remove(new StockMovement(new Date(2024, 1, 9), 30));

        System.out.println("Lagerbestand:");
        System.out.println(stockList.getStockStatus());

        System.out.println("\nWareneingänge:");
        System.out.println(stockList.getStockIngoings());

        System.out.println("\nWarenausgänge:");
        System.out.println(stockList.getStockOutgoings());
    }
}
