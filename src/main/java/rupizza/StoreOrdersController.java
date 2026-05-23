package rupizza;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;

/**
 * Controller for the placed orders view (storeorders-view.fxml).
 * Lets the employee browse all orders, cancel an order, and export to a file.
 */
public class StoreOrdersController {

    @FXML private ComboBox<String> orderComboBox;
    @FXML private ListView<String> orderDetailsListView;
    @FXML private Label orderTotalLabel;

    private StoreOrders storeOrders;

    /**
     * Loads the store orders data into the view.
     */
    public void initData() {
        storeOrders = StoreOrders.getInstance();
        refreshComboBox();
    }

    /**
     * Refreshes the combo box with all currently placed orders.
     */
    private void refreshComboBox() {
        ObservableList<String> labels = FXCollections.observableArrayList();
        for (Order o : storeOrders.getOrders()) {
            labels.add("Order #" + o.getNumber());
        }
        orderComboBox.setItems(labels);
        orderDetailsListView.setItems(FXCollections.observableArrayList());
        orderTotalLabel.setText("$0.00");
    }

    /**
     * Shows the details of the selected order.
     */
    @FXML
    private void onOrderSelected() {
        int idx = orderComboBox.getSelectionModel().getSelectedIndex();
        if (idx < 0 || idx >= storeOrders.getOrders().size()) return;

        Order selected = storeOrders.getOrders().get(idx);
        ObservableList<String> details = FXCollections.observableArrayList();
        for (Pizza p : selected.getPizzas()) {
            details.add(p.toString());
        }
        orderDetailsListView.setItems(details);
        orderTotalLabel.setText(String.format("$%.2f", selected.total()));
    }

    /**
     * Cancels the selected order after confirmation.
     */
    @FXML
    private void onCancelOrder() {
        int idx = orderComboBox.getSelectionModel().getSelectedIndex();
        if (idx < 0) {
            showAlert("Please select an order to cancel.");
            return;
        }
        Order toCancel = storeOrders.getOrders().get(idx);
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Cancel Order");
        confirm.setHeaderText(null);
        confirm.setContentText("Cancel Order #" + toCancel.getNumber() + "?");
        confirm.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                storeOrders.cancelOrder(toCancel);
                showAlert("Order #" + toCancel.getNumber() + " has been cancelled.");
                refreshComboBox();
            }
        });
    }

    /**
     * Exports all placed orders to a text file chosen by the user.
     */
    @FXML
    private void onExportOrders() {
        if (storeOrders.getOrders().isEmpty()) {
            showAlert("No orders to export.");
            return;
        }
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Save Orders");
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        chooser.setInitialFileName("orders.txt");
        File file = chooser.showSaveDialog(Main.getPrimaryStage());
        if (file != null) {
            try {
                storeOrders.exportOrders(file.getAbsolutePath());
                showAlert("Orders saved to: " + file.getName());
            } catch (IOException e) {
                showAlert("Failed to export: " + e.getMessage());
            }
        }
    }

    /**
     * Goes back to the main ordering view.
     */
    @FXML
    private void onBack() {
        try {
            Main.showMainView();
        } catch (IOException e) {
            showAlert("Error returning to main view.");
        }
    }

    /**
     * Shows an information alert with the given message.
     * @param message the message to display
     */
    private void showAlert(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("RU Pizza");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
