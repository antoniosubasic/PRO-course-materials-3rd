package stock;

import date.Date;

public class ValuedStockMovement extends StockMovement implements Cloneable {
    double pricePerUnit;

    public ValuedStockMovement(Date date, double quantity, double pricePerUnit) {
        super(date, quantity);
        this.pricePerUnit = pricePerUnit;
    }

    @Override
    public String toString() {
        return String.format(
                "%s %7.2f EH je EUR %7.2f      EUR %10.2f",
                date,
                quantity,
                pricePerUnit,
                quantity * pricePerUnit
        );
    }

    @Override
    public ValuedStockMovement clone() {
        return new ValuedStockMovement(date.clone(), quantity, pricePerUnit);
    }
}
