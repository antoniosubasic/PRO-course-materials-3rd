package stock;

public class StockMovementList {
    private Node head;
    private Node tail;

    public StockMovementList() {
        head = null;
        tail = null;
    }

    public void put(ValuedStockMovement valuedStockMovement) {
        var newNode = new Node(valuedStockMovement.clone());

        if (head == null) {
            head = newNode;
        } else {
            tail.next = newNode;
        }
        tail = newNode;
    }

    @Override
    public String toString() {
        var sb = new StringBuilder();

        for (var current = head; current != null; current = current.next) {
            sb.append(current.valuedStockMovement.toString());
            if (current.next != null) { sb.append(" -> "); }
        }

        return sb.toString();
    }

    private class Node {
        ValuedStockMovement valuedStockMovement;
        Node next;

        Node(ValuedStockMovement valuedStockMovement) {
            this.valuedStockMovement = valuedStockMovement;
            this.next = null;
        }
    }
}
