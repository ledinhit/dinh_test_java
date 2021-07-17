import java.util.List;

public class Order {
    int id;
    List<LineItem> lineItems;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<LineItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<LineItem> lineItems) {
        this.lineItems = lineItems;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", lineItems=" + lineItems +
                '}';
    }
}
