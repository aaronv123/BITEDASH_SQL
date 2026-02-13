package javafxapplication1;

/**
 *
 * @author Aaron Penetrante
 */
public class Locations {
    private final int orderId;
    private final String restaurantName;
    private final String restaurantLocation;
    private final String customerName;
    private final String customerLocation;
    private final String orderStatus;

    public Locations(int orderId, String restaurantName, String restaurantLocation,
                     String customerName, String customerLocation, String orderStatus) {
        this.orderId = orderId;
        this.restaurantName = restaurantName;
        this.restaurantLocation = restaurantLocation;
        this.customerName = customerName;
        this.customerLocation = customerLocation;
        this.orderStatus = orderStatus;
    }

    public int getOrderId() { return orderId; }
    public String getRestaurantName() { return restaurantName; }
    public String getRestaurantLocation() { return restaurantLocation; }
    public String getCustomerName() { return customerName; }
    public String getCustomerLocation() { return customerLocation; }
    public String getOrderStatus() { return orderStatus; }

}
