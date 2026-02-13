package dao;
import dao.ConnectionProvider;
import java.sql.*;
import java.util.*;
import javafxapplication1.DeliveryStats;
import javafxapplication1.Locations;
import javafxapplication1.Order;
import javafxapplication1.OrderDetails;

/**
 *
 * @author Aaron Penetrante
 */
public class daoOrder {
    
    //Order per rider
        public Map<String, Integer> getOrdersPerRider() {
        Map<String, Integer> report = new HashMap<>();
        
        String sql = "SELECT r.name, COUNT(*) AS total_orders " +
                     "FROM orders o INNER JOIN riders r ON o.rider_id = r.rider_id " +
                     "GROUP BY r.name";

        try (Connection conn = ConnectionProvider.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                report.put(rs.getString("name"), rs.getInt("total_orders"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return report;
    }
        
// Active Orders
public List<OrderDetails> getActiveOrders() {
    List<OrderDetails> list = new ArrayList<>();
    String sql = "SELECT o.id, o.order_type, o.order_status, r.name, r.vehicle_type " +
                 "FROM orders o INNER JOIN riders r ON o.rider_id = r.rider_id " +
                 "WHERE o.order_status = 'Delivering'";
    
    try (Connection conn = ConnectionProvider.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {
        
        while (rs.next()) {
            list.add(new OrderDetails(
                rs.getInt("id"),
                rs.getString("order_type"),
                rs.getString("order_status"),
                rs.getString("name"),
                rs.getString("vehicle_type")
            ));
        }
        
    } catch (Exception e) { e.printStackTrace(); }
    return list;
 }


// Delivery Stats (counts only)
public List<DeliveryStats> getDeliveryStats() {
    List<DeliveryStats> list = new ArrayList<>();
    String sql = "SELECT r.name, COUNT(*) AS delivered_orders " +
                 "FROM orders o INNER JOIN riders r ON o.rider_id = r.rider_id " +
                 "WHERE o.order_status = 'Delivered' GROUP BY r.name";
    try (Connection conn = ConnectionProvider.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {
        while (rs.next()) {
            list.add(new DeliveryStats(
                rs.getString("name"),
                rs.getInt("delivered_orders")
            ));
        }
    } catch (Exception e) { e.printStackTrace(); }
    return list;
}

// view order

public List<Order> getAllOrders() {
    List<Order> list = new ArrayList<>();
    String sql = "SELECT * FROM orders";

    try (Connection conn = ConnectionProvider.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            list.add(new Order(
                rs.getInt("id"),
                rs.getString("order_type"),
                rs.getString("order_status"),
                rs.getInt("rider_id")
            ));
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;

    }

// Restaurant and Customer Location with status and order id
public List<Locations> getOrdersWithDetails() {
    List<Locations> list = new ArrayList<>();
    String sql = "SELECT o.id, r.name, r.location, c.name, c.location, o.order_status " +
                 "FROM orders o " +
                 "INNER JOIN restaurants r ON o.restaurant_id = r.restaurant_id " +
                 "INNER JOIN customers c ON o.customer_id = c.customer_id";

    try (Connection conn = ConnectionProvider.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            list.add(new Locations(
                rs.getInt("id"),
                rs.getString("name"),       // restaurant name
                rs.getString("location"),   // restaurant location
                rs.getString(4),            // customer name
                rs.getString(5),            // customer location
                rs.getString("order_status")
            ));
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return list;
}
}
