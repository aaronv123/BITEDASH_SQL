package javafxapplication1;

/**
 *
 * @author Aaron Penetrante
 */
public class OrderDetails {
private final int id;               // orders.id
    private final String orderType;     // orders.order_type
    private final String orderStatus;   // orders.order_status
    private final String riderName;     // riders.name
    private final String vehicleType;   // riders.vehicle_type

    public OrderDetails(int id, String orderType, String orderStatus, String riderName, String vehicleType) {
        this.id = id;
        this.orderType = orderType;
        this.orderStatus = orderStatus;
        this.riderName = riderName;
        this.vehicleType = vehicleType;
    }

    public int getId() { return id; }
    public String getOrderType() { return orderType; }
    public String getOrderStatus() { return orderStatus; }
    public String getRiderName() { return riderName; }
    public String getVehicleType() { return vehicleType; }

}
