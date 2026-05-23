package rupizza;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Main entry point for the RU Pizza application.
 */
public class Main extends Application {

    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage;
        primaryStage.setTitle("RU Pizza");
        showMainView();
        primaryStage.show();
    }

    /**
     * Loads and shows the main pizza ordering view.
     * @throws IOException if the FXML cannot be loaded
     */
    public static void showMainView() throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("main-view.fxml"));
        Scene scene = new Scene(loader.load(), 900, 620);
        primaryStage.setScene(scene);
    }

    /**
     * Loads and shows the current order view, passing in the current order.
     * @param order the order to display
     * @throws IOException if the FXML cannot be loaded
     */
    public static void showOrderView(Order order) throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("order-view.fxml"));
        Scene scene = new Scene(loader.load(), 900, 620);
        OrderViewController controller = loader.getController();
        controller.initData(order);
        primaryStage.setScene(scene);
    }

    /**
     * Loads and shows the placed orders view.
     * @throws IOException if the FXML cannot be loaded
     */
    public static void showStoreOrdersView() throws IOException {
        FXMLLoader loader = new FXMLLoader(Main.class.getResource("storeorders-view.fxml"));
        Scene scene = new Scene(loader.load(), 900, 620);
        StoreOrdersController controller = loader.getController();
        controller.initData();
        primaryStage.setScene(scene);
    }

    /**
     * Returns the primary stage.
     * @return the application stage
     */
    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch();
    }
}
