package javafxapplication1;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import java.sql.Connection;
import java.sql.PreparedStatement;
import dao.ConnectionProvider;
import javafx.scene.control.Label;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import dao.daoOrder;
import javafx.concurrent.ScheduledService;
import javafx.concurrent.Task;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.util.Duration;


/**
 *
 * @author Aaron Penetrante
 */


public class JavaFXApplication1Controller {

//CALLERS Individual and Array

    @FXML
    private ChoiceBox<String> choicebox;
    
    @FXML private Label a1, a2, a3, a4;
    @FXML private Label b1, b2, b3, b4;
    @FXML private Label c1,c2, c3, c4;
    @FXML private Label d1, d2, d3, d4;
    @FXML private Label e1, e2, e3, e4;
    @FXML private Label f1, f2, f3, f4;
    @FXML private Label g1, g2, g3, g4;
    @FXML private Label h1, h2, h3, h4;
    @FXML private Label i1, i2, i3, i4;
    @FXML private Label j1, j2, j3, j4; 
    
private Label[] riderNames;
private Label[] riderAvailability;
private Label[] riderOrders;
private Label[] riderStatuses;


// FUNCTIONS
    
// Hot > Cold > Normal prioritizer
    @FXML
    public void initialize() {
        
        System.out.println("Controller initialized!");
        choicebox.getItems().addAll("Hot", "Cold", "Normal");
        choicebox.setValue("Normal");
        
        choicebox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
        System.out.println("User selected: " + newVal);
    });
    riderNames = new Label[]{a1,b1,c1,d1,e1,f1,g1,h1,i1,j1};
    riderAvailability = new Label[]{a2,b2,c2,d2,e2,f2,g2,h2,i2,j2};
    riderOrders = new Label[]{a3,b3,c3,d3,e3,f3,g3,h3,i3,j3};
    riderStatuses = new Label[]{a4,b4,c4,d4,e4,f4,g4,h4,i4,j4};

    }

//Insert Statements
    
@FXML
private void handleSubmitOrder() {
    String selected = choicebox.getValue();

    if (selected == null) {
        System.out.println("No order selected.");
        return;
    }
    
      int assignedRiderId = -1;


    // Example: assign to the first available rider
    if ("Available".equalsIgnoreCase(a2.getText()) && "(Empty)".equals(a3.getText())) {
        a3.setText(selected);
        a3.setTextFill(javafx.scene.paint.Color.BLACK);
        a4.setText("Pending...");
        assignedRiderId = 1;
        
    } else if ("Available".equalsIgnoreCase(b2.getText()) && "(Empty)".equals(b3.getText())) {
        b3.setText(selected);
        b3.setTextFill(javafx.scene.paint.Color.BLACK);
        b4.setText("Pending...");
        assignedRiderId = 2;
        
    } else if ("Available".equalsIgnoreCase(c2.getText()) && "(Empty)".equals(c3.getText())) {
        c3.setText(selected);
        c3.setTextFill(javafx.scene.paint.Color.BLACK);
        c4.setText("Pending...");
        assignedRiderId = 3;

    } else if ("Available".equalsIgnoreCase(d2.getText()) && "(Empty)".equals(d3.getText())) {
        d3.setText(selected);
        d3.setTextFill(javafx.scene.paint.Color.BLACK);
        d4.setText("Pending...");
        assignedRiderId = 4;

    } else if ("Available".equalsIgnoreCase(e2.getText()) && "(Empty)".equals(e3.getText())) {
        e3.setText(selected);
        e3.setTextFill(javafx.scene.paint.Color.BLACK);
        e4.setText("Pending...");
        assignedRiderId = 5;

    } else if ("Available".equalsIgnoreCase(f2.getText()) && "(Empty)".equals(f3.getText())) {
        f3.setText(selected);
        f3.setTextFill(javafx.scene.paint.Color.BLACK);
        f4.setText("Pending...");
        assignedRiderId = 6;

    } else if ("Available".equalsIgnoreCase(g2.getText()) && "(Empty)".equals(g3.getText())) {
        g3.setText(selected);
        g3.setTextFill(javafx.scene.paint.Color.BLACK);
        g4.setText("Pending...");
        assignedRiderId = 7;

    } else if ("Available".equalsIgnoreCase(h2.getText()) && "(Empty)".equals(h3.getText())) {
        h3.setText(selected);
        h3.setTextFill(javafx.scene.paint.Color.BLACK);
        h4.setText("Pending...");
        assignedRiderId = 8;

    } else if ("Available".equalsIgnoreCase(i2.getText()) && "(Empty)".equals(i3.getText())) {
        i3.setText(selected);
        i3.setTextFill(javafx.scene.paint.Color.BLACK);
        i4.setText("Pending...");
        assignedRiderId = 9;

    } else if ("Available".equalsIgnoreCase(j2.getText()) && "(Empty)".equals(j3.getText())) {
        j3.setText(selected);
        j3.setTextFill(javafx.scene.paint.Color.BLACK);
        j4.setText("Pending...");
        assignedRiderId = 10;

    } if (assignedRiderId == -1) {
            System.out.println("No available rider found.");
            return;
        }

    // Insert into all four columns
    String sql = "INSERT INTO orders (order_type, rider_id, is_available, order_status) VALUES (?, ?, ?, ?)";

    try (Connection conn = ConnectionProvider.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setString(1, selected);          // order_type
        stmt.setInt(2, assignedRiderId);     // rider_id 
        stmt.setBoolean(3, true);             // is_available defaults to TRUE
        stmt.setString(4, "Pending");         // status starts as Pending

        int rows = stmt.executeUpdate();
        System.out.println("Order submitted for Rider " + assignedRiderId + ": " + selected + " (rows affected: " + rows + ")");

    
} catch (Exception e) {
        e.printStackTrace();
    }
}



//Delete Statements    
    
    @FXML
    private void handleClearOrder() {
    String sql = "TRUNCATE TABLE orders"; // clears the entire table

    try (Connection conn = ConnectionProvider.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        int rows = stmt.executeUpdate();
        System.out.println("All orders cleared from database. Rows affected: " + rows);

        // Reset ChoiceBox selection
        choicebox.setValue("Normal");

    } catch (Exception e) {
        e.printStackTrace();
    }
    
    // reset labels
    a3.setText("(Empty)"); a3.setTextFill(javafx.scene.paint.Color.LIGHTGRAY); a4.setText("Pending...");
    b3.setText("(Empty)"); b3.setTextFill(javafx.scene.paint.Color.LIGHTGRAY); b4.setText("Pending...");
    c3.setText("(Empty)"); c3.setTextFill(javafx.scene.paint.Color.LIGHTGRAY); c4.setText("Pending...");
    d3.setText("(Empty)"); d3.setTextFill(javafx.scene.paint.Color.LIGHTGRAY); d4.setText("Pending...");
    e3.setText("(Empty)"); e3.setTextFill(javafx.scene.paint.Color.LIGHTGRAY); e4.setText("Pending...");
    f3.setText("(Empty)"); f3.setTextFill(javafx.scene.paint.Color.LIGHTGRAY); f4.setText("Pending...");
    g3.setText("(Empty)"); g3.setTextFill(javafx.scene.paint.Color.LIGHTGRAY); g4.setText("Pending...");
    h3.setText("(Empty)"); h3.setTextFill(javafx.scene.paint.Color.LIGHTGRAY); h4.setText("Pending...");
    i3.setText("(Empty)"); i3.setTextFill(javafx.scene.paint.Color.LIGHTGRAY); i4.setText("Pending...");
    j3.setText("(Empty)"); j3.setTextFill(javafx.scene.paint.Color.LIGHTGRAY); j4.setText("Pending...");
}
   
//Update Statement
    
@FXML
private void handleAssignOrder() {
    for (int i = 0; i < riderOrders.length; i++) {
        Label orderLabel = riderOrders[i];
        Label statusLabel = riderStatuses[i];
        Label availabilityLabel = riderAvailability[i];
        int riderId = i + 1; // Rider IDs 1–10

        if (!"(Empty)".equals(orderLabel.getText())) {
            updateOrderStatusInDB(riderId, "Delivering");
            statusLabel.setText("Delivering");
            statusLabel.setTextFill(javafx.scene.paint.Color.DARKGREEN);
            
            availabilityLabel.setText("Not Available");
            availabilityLabel.setTextFill(javafx.scene.paint.Color.RED);

           // duration of delivery
            javafx.animation.PauseTransition deliveringTimer =
                    new javafx.animation.PauseTransition(javafx.util.Duration.seconds(15));
            deliveringTimer.setOnFinished(e -> {
                updateOrderStatusInDB(riderId, "Delivered");
                statusLabel.setText("Delivered");
                statusLabel.setTextFill(javafx.scene.paint.Color.YELLOWGREEN);
                availabilityLabel.setText("Available");
                availabilityLabel.setTextFill(javafx.scene.paint.Color.GREEN);

           // delivered pause
                javafx.animation.PauseTransition deliveredTimer =
                        new javafx.animation.PauseTransition(javafx.util.Duration.seconds(5));
                deliveredTimer.setOnFinished(ev -> {
                    deleteOrderFromDB(riderId);
                    orderLabel.setText("(Empty)");
                    orderLabel.setTextFill(javafx.scene.paint.Color.LIGHTGRAY);
                    statusLabel.setText("Pending...");
                    statusLabel.setTextFill(javafx.scene.paint.Color.LIGHTGRAY);
                });
                deliveredTimer.play();
            });
            deliveringTimer.play();
        }
    }
}


private void updateOrderStatusInDB(int riderId, String status) {
    String sql = "UPDATE orders SET order_status = ? WHERE rider_id = ?";
    try (Connection conn = ConnectionProvider.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setString(1, status);
        stmt.setInt(2, riderId);
        stmt.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    }
}

private void deleteOrderFromDB(int riderId) {
    String sql = "DELETE FROM orders WHERE rider_id = ?";
    try (Connection conn = ConnectionProvider.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, riderId);
        stmt.executeUpdate();
    } catch (Exception e) {
        e.printStackTrace();
    }
}
    
// Advance Queries

    daoOrder dao = new daoOrder();
    @FXML
    private void showOrdersPerRiderReport() {
        Stage popupStage = new Stage();
        popupStage.setTitle("Advanced Reports Dashboard");

        TabPane tabPane = new TabPane();
            

        // Tab 1: Orders per Rider
        TableView<OrderReport> ordersTable = new TableView<>();
        ordersTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        TableColumn<OrderReport, String> riderCol = new TableColumn<>("Rider");
        TableColumn<OrderReport, Integer> totalCol = new TableColumn<>("Total Orders");
        riderCol.setCellValueFactory(new PropertyValueFactory<>("riderName"));
        totalCol.setCellValueFactory(new PropertyValueFactory<>("totalOrders"));
        ordersTable.getColumns().addAll(riderCol, totalCol);
        autoRefreshOrdersPerRider(ordersTable);
        Tab ordersTab = new Tab("Orders per Rider", ordersTable);

        // Tab 2: Active Orders
        TableView<OrderDetails> activeTable = new TableView<>();
        activeTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        TableColumn<OrderDetails, Integer> idCol = new TableColumn<>("Order ID");
        TableColumn<OrderDetails, String> typeCol = new TableColumn<>("Type");
        TableColumn<OrderDetails, String> statusCol = new TableColumn<>("Status");
        TableColumn<OrderDetails, String> riderNameCol = new TableColumn<>("Rider");
        TableColumn<OrderDetails, String> vehicleCol = new TableColumn<>("Vehicle");     
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        typeCol.setCellValueFactory(new PropertyValueFactory<>("orderType"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("orderStatus"));
        riderNameCol.setCellValueFactory(new PropertyValueFactory<>("riderName"));
        vehicleCol.setCellValueFactory(new PropertyValueFactory<>("vehicleType"));
        activeTable.getColumns().addAll(idCol, typeCol, statusCol, riderNameCol, vehicleCol);
        autoRefreshActiveOrders(activeTable);
        Tab activeTab = new Tab("Active Orders", activeTable);

        // Tab 3: Delivery Stats
        TableView<DeliveryStats> statsTable = new TableView<>();
        statsTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        TableColumn<DeliveryStats, String> riderStatCol = new TableColumn<>("Rider");
        TableColumn<DeliveryStats, Double> avgCol = new TableColumn<>("Avg Delivery Time (s)");
        riderStatCol.setCellValueFactory(new PropertyValueFactory<>("riderName"));
        avgCol.setCellValueFactory(new PropertyValueFactory<>("deliveredOrders"));
        statsTable.getColumns().addAll(riderStatCol, avgCol);
        autoRefreshDeliveryStats(statsTable);
        Tab statsTab = new Tab("Delivery Stats", statsTable);

        tabPane.getTabs().addAll(ordersTab, activeTab, statsTab);

      Scene scene = new Scene(tabPane, 700, 400);
 popupStage.setScene(scene);
 popupStage.setResizable(false); 
 popupStage.show();


    }

    // 🔄 Auto-refresh every 1 second
    private void autoRefreshOrdersPerRider(TableView<OrderReport> table) {
        ScheduledService<ObservableList<OrderReport>> service = new ScheduledService<>() {
            @Override
            protected Task<ObservableList<OrderReport>> createTask() {
                return new Task<>() {
                    @Override
                    protected ObservableList<OrderReport> call() {
                        Map<String, Integer> report = dao.getOrdersPerRider();
                        ObservableList<OrderReport> data = FXCollections.observableArrayList();
                        report.forEach((name, count) -> data.add(new OrderReport(name, count)));
                        return data;
                    }
                };
            }
        };
        service.setPeriod(Duration.seconds(1));
        service.setOnSucceeded(e -> table.setItems(service.getValue()));
        service.start();
    }

    private void autoRefreshActiveOrders(TableView<OrderDetails> table) {
        ScheduledService<ObservableList<OrderDetails>> service = new ScheduledService<>() {
            @Override
            protected Task<ObservableList<OrderDetails>> createTask() {
                return new Task<>() {
                    @Override
                    protected ObservableList<OrderDetails> call() {
                        return FXCollections.observableArrayList(dao.getActiveOrders());
                    }
                };
            }
        };
        service.setPeriod(Duration.seconds(1));
        service.setOnSucceeded(e -> table.setItems(service.getValue()));
        service.start();
    }

    private void autoRefreshDeliveryStats(TableView<DeliveryStats> table) {
        ScheduledService<ObservableList<DeliveryStats>> service = new ScheduledService<>() {
            @Override
            protected Task<ObservableList<DeliveryStats>> createTask() {
                return new Task<>() {
                    @Override
                    protected ObservableList<DeliveryStats> call() {
                        return FXCollections.observableArrayList(dao.getDeliveryStats());
                    }
                };
            }
        };
        service.setPeriod(Duration.seconds(1));
        service.setOnSucceeded(e -> table.setItems(service.getValue()));
        service.start();
    }
}


    
    //END

