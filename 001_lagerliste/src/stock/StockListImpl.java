package stock;

public abstract class StockListImpl implements StockList {
    protected StockMovementList ingoings;
    protected StockMovementList outgoings;

    public StockListImpl() {
        ingoings = new StockMovementList();
        outgoings = new StockMovementList();
    }

    @Override
    public String getStockIngoings() {
        return ingoings.toString();
    }

    @Override
    public String getStockOutgoings() {
        return outgoings.toString();
    }
}
