package javafxapplication1;

/**
 *
 * @author Aaron Penetrante
 */
public class OrderReport {
    private String riderName;
    private int totalOrders;

    public OrderReport(String riderName, int totalOrders) {
        this.riderName = riderName;
        this.totalOrders = totalOrders;
    }

    public String getRiderName() { return riderName; }
    public int getTotalOrders() { return totalOrders; }
}
