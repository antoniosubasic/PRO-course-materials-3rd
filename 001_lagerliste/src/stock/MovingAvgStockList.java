package stock;

public class MovingAvgStockList extends StockListImpl {

    // aktueller Lagerbestand
    private ValuedStockMovement stock;

    public MovingAvgStockList() {
        stock = null;
    }

    @Override
    public void store(ValuedStockMovement valuedStockMovement) {
        if (stock == null) {
            // WICHTIG: Im Lager wird ein neues Objekt abgelegt,
            // damit es keine Wechselwirkungen mit übergeordneten
            // Progammsystemen gibt da wir vorhaben, Eigenschaften
            // des Objekts (z.B. Menge) zu verändern.
            stock = valuedStockMovement.clone();
        } else {
            // GLD - Berechung
            double totalQuantity = stock.quantity + valuedStockMovement.quantity;
            double totalValue = stock.getValue() + valuedStockMovement.getValue();
            double avgPricePerUnit = totalValue / totalQuantity;

            stock.quantity = totalQuantity;
            stock.pricePerUnit = avgPricePerUnit;
            stock.date = valuedStockMovement.date.clone();
        }

        ingoings.put(valuedStockMovement.clone());
    }

    @Override
    public void remove(StockMovement stockMovement) {
        // Wenn Lagerbestand vorhanden
        if (stock != null) {
            var outgoing = new ValuedStockMovement(stockMovement.date.clone(), 0, stock.pricePerUnit);

            if (stock.quantity <= stockMovement.quantity) {
                // Fall 1: Gesamte Lagermenge wird entnommen
                outgoing.quantity = stock.quantity;
                stock = null;
            } else {
                // Fall 2: Im Lager verbleibt eine Restmenge
                outgoing.quantity = stockMovement.quantity;
                stock.quantity -= stockMovement.quantity;
            }

            outgoings.put(outgoing);
        }
    }

    @Override
    public String getStockStatus() {
        return (stock == null ? "" : stock.toString()) + "\n";
    }
}