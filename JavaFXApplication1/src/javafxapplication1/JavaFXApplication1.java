package javafxapplication1;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;

/**
 *
 * @author Aaron Penetrante
 */
public class JavaFXApplication1 extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("FDDA.fxml"));
        Parent root = loader.load();
    
        Scene scene = new Scene(root);
        JavaFXApplication1Controller controller = loader.getController();
        primaryStage.setTitle("Food Delivery Dispatch Terminal");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false); 
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
