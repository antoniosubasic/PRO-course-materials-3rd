package stock;

import date.Date;

public class StockMovement implements Cloneable {
    Date date;
    double quantity;

    public StockMovement(Date date, double quantity) {
        this.date = date;
        this.quantity = quantity;
    }

    @Override
    public StockMovement clone() {
        return new StockMovement(date.clone(), quantity);
    }
}
