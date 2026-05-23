package rupizza;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;

/**
 * Controller for the current order view (order-view.fxml).
 * Shows the order details and lets the employee remove pizzas or place the order.
 */
public class OrderViewController {

    @FXML private Label orderNumberLabel;
    @FXML private ListView<String> pizzaListView;
    @FXML private Label subtotalLabel;
    @FXML private Label taxLabel;
    @FXML private Label orderTotalLabel;

    private Order currentOrder;

    /**
     * Initializes the view with the given order.
     * @param order the order to display
     */
    public void initData(Order order) {
        this.currentOrder = order;
        refreshView();
    }

    /**
     * Refreshes all labels and the list view to reflect the current order.
     */
    private void refreshView() {
        orderNumberLabel.setText("Order #" + currentOrder.getNumber());

        ObservableList<String> items = FXCollections.observableArrayList();
        for (Pizza p : currentOrder.getPizzas()) {
            items.add(p.toString());
        }
        pizzaListView.setItems(items);

        subtotalLabel.setText(String.format("$%.2f", currentOrder.subtotal()));
        taxLabel.setText(String.format("$%.2f", currentOrder.tax()));
        orderTotalLabel.setText(String.format("$%.2f", currentOrder.total()));
    }

    /**
     * Removes the selected pizza from the order.
     */
    @FXML
    private void onRemovePizza() {
        int idx = pizzaListView.getSelectionModel().getSelectedIndex();
        if (idx < 0) {
            showAlert("Please select a pizza to remove.");
            return;
        }
        Pizza toRemove = currentOrder.getPizzas().get(idx);
        currentOrder.removePizza(toRemove);
        refreshView();
    }

    /**
     * Clears all pizzas from the order after confirmation.
     */
    @FXML
    private void onClearOrder() {
        if (currentOrder.getPizzas().isEmpty()) {
            showAlert("The order is already empty.");
            return;
        }
        Alert confirm = new Alert(Alert.AlertType.CONFIRMATION);
        confirm.setTitle("Clear Order");
        confirm.setHeaderText(null);
        confirm.setContentText("Remove all pizzas from this order?");
        confirm.showAndWait().ifPresent(response -> {
            if (response == ButtonType.OK) {
                currentOrder.clearPizzas();
                refreshView();
            }
        });
    }

    /**
     * Places the order by adding it to StoreOrders, then goes back to the main view.
     */
    @FXML
    private void onPlaceOrder() {
        if (currentOrder.getPizzas().isEmpty()) {
            showAlert("Cannot place an empty order.");
            return;
        }
        StoreOrders.getInstance().addOrder(currentOrder);
        showAlert("Order #" + currentOrder.getNumber() + " placed! Total: "
                + String.format("$%.2f", currentOrder.total()));
        try {
            Main.showMainView();
        } catch (IOException e) {
            showAlert("Error returning to main view.");
        }
    }

    /**
     * Goes back to the main ordering view without placing the order.
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
