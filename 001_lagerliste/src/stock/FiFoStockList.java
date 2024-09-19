package stock;

public class FiFoStockList extends StockListImpl {
    protected Node head;
    private Node tail;

    public FiFoStockList() {
        head = null;
        tail = null;
    }

    @Override
    public void store(ValuedStockMovement valuedStockMovement) {
        var newNode = new Node(valuedStockMovement.clone());

        if (head == null) {
            head = newNode;
        } else {
            tail.next = newNode;
        }
        tail = newNode;

        ingoings.put(valuedStockMovement.clone());
    }

    @Override
    public void remove(StockMovement stockMovement) {
        if (head != null) {
            for (var current = head; current != null && stockMovement.quantity != 0; current = current.next) {
                var outgoing = new ValuedStockMovement(stockMovement.date.clone(), 0, current.valuedStockMovement.pricePerUnit);
                
                if (current.valuedStockMovement.quantity >= stockMovement.quantity) {
                    current.valuedStockMovement.quantity -= stockMovement.quantity;
                    outgoing.quantity = stockMovement.quantity;
                    stockMovement.quantity = 0;
                } else {
                    stockMovement.quantity -= current.valuedStockMovement.quantity;
                    outgoing.quantity = current.valuedStockMovement.quantity;
                    head = head.next;
                    if (head == null) {
                        tail = null;
                    }
                }

                outgoings.put(outgoing);
            }
        }
    }

    @Override
    public String getStockStatus() {
        var sb = new StringBuilder();

        for (var current = head; current != null; current = current.next) {
            sb.append(current.valuedStockMovement.toString());
            if (current.next != null) {
                sb.append("\n");
            }
        }

        return sb.toString();
    }

    protected class Node {
        ValuedStockMovement valuedStockMovement;
        Node next;

        Node(ValuedStockMovement valuedStockMovement) {
            this.valuedStockMovement = valuedStockMovement;
            this.next = null;
        }
    }
}