package stock;

public class HiFoStockList extends FiFoStockList {
    @Override
    public void store(ValuedStockMovement valuedStockMovement) {
        var newNode = new Node(valuedStockMovement.clone());

        if (head == null) {
            head = newNode;
        } else {
            if (head.valuedStockMovement.pricePerUnit < valuedStockMovement.pricePerUnit) {
                newNode.next = head;
                head = newNode;
            } else if (head.next == null) {
                head.next = newNode;
            } else {
                for (var current = head; current.next != null; current = current.next) {
                    if (current.next.valuedStockMovement.pricePerUnit < valuedStockMovement.pricePerUnit) {
                        newNode.next = current.next;
                        current.next = newNode;
                        break;
                    }
                }
            }
        }

        ingoings.put(valuedStockMovement.clone());
    }
}