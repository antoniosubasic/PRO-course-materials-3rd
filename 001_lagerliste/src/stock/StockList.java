package stock;

public interface StockList {
    void store(ValuedStockMovement valuedStockMovement);
    void remove(StockMovement valuedStockMovement);

    String getStockStatus();
    String getStockIngoings();
    String getStockOutgoings();
}
