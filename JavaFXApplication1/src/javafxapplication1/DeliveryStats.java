package javafxapplication1;

/**
 *
 * @author Aaron Penetrante
 */
public class DeliveryStats {
    private final String riderName;       // riders.name
    private final int deliveredOrders;    // count of delivered orders

    public DeliveryStats(String riderName, int deliveredOrders) {
        this.riderName = riderName;
        this.deliveredOrders = deliveredOrders;
    }

    public String getRiderName() { return riderName; }
    public int getDeliveredOrders() { return deliveredOrders; }
}

