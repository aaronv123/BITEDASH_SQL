package javafxapplication1;

/**
 *
 * @author Aaron Penetrante
 */
public class Order {
        private final int id;
    private final String orderType;
    private final String orderStatus;
    private final int riderId;

    public Order(int id, String orderType, String orderStatus, int riderId) {
        this.id = id;
        this.orderType = orderType;
        this.orderStatus = orderStatus;
        this.riderId = riderId;
    }

    public int getId() { return id; }
    public String getOrderType() { return orderType; }
    public String getOrderStatus() { return orderStatus; }
    public int getRiderId() { return riderId; }
}
