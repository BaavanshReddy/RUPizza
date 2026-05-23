package rupizza;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.IOException;

/**
 * Controller for the main pizza ordering view (main-view.fxml).
 * Handles pizza selection, customizing toppings for Build Your Own,
 * and adding pizzas to the current order.
 */
public class MainController {

    @FXML private ComboBox<String> styleComboBox;
    @FXML private ComboBox<String> typeComboBox;
    @FXML private ComboBox<String> sizeComboBox;
    @FXML private Label crustLabel;
    @FXML private ListView<String> selectedToppingsListView;
    @FXML private ListView<Topping> availableToppingsListView;
    @FXML private Button addToppingButton;
    @FXML private Button removeToppingButton;
    @FXML private Button pizzaImageButton;
    @FXML private Label totalLabel;
    @FXML private ImageView pizzaImageView;
    @FXML private Label pizzaInfoLabel;

    private Order currentOrder;
    private Pizza currentPizza;

    /**
     * Sets up combo boxes and default values when the view loads.
     */
    @FXML
    public void initialize() {
        currentOrder = new Order();

        styleComboBox.setItems(FXCollections.observableArrayList("Chicago Style", "New York Style"));
        typeComboBox.setItems(FXCollections.observableArrayList("Deluxe", "BBQ Chicken", "Meatzza", "Build Your Own"));
        sizeComboBox.setItems(FXCollections.observableArrayList("Small", "Medium", "Large"));
        availableToppingsListView.setItems(FXCollections.observableArrayList(Topping.values()));

        styleComboBox.getSelectionModel().select(0);
        typeComboBox.getSelectionModel().select(0);
        sizeComboBox.getSelectionModel().select(0);

        updatePizzaDisplay();
    }

    /**
     * Called when style or type combo box changes.
     * Rebuilds the current pizza object and updates the display.
     */
    @FXML
    private void onStyleOrTypeChanged() {
        updatePizzaDisplay();
    }

    /**
     * Called when the size changes. Updates the price label.
     */
    @FXML
    private void onSizeChanged() {
        if (currentPizza != null && sizeComboBox.getValue() != null) {
            currentPizza.setSize(getSize(sizeComboBox.getValue()));
            updateTotal();
        }
    }

    /**
     * Handles clicking the pizza image button. Refreshes the pizza display.
     */
    @FXML
    private void onPizzaImageClicked() {
        updatePizzaDisplay();
    }

    /**
     * Rebuilds the pizza based on the current combo box selections
     * and refreshes all relevant display elements.
     */
    private void updatePizzaDisplay() {
        String style = styleComboBox.getValue();
        String type = typeComboBox.getValue();
        if (style == null || type == null) return;

        PizzaFactory factory;
        if (style.equals("Chicago Style")) {
            factory = new ChicagoPizza();
        } else {
            factory = new NYPizza();
        }

        if (type.equals("Deluxe")) currentPizza = factory.createDeluxe();
        else if (type.equals("BBQ Chicken")) currentPizza = factory.createBBQChicken();
        else if (type.equals("Meatzza")) currentPizza = factory.createMeatzza();
        else currentPizza = factory.createBuildYourOwn();

        String sizeVal = sizeComboBox.getValue() != null ? sizeComboBox.getValue() : "Small";
        currentPizza.setSize(getSize(sizeVal));

        crustLabel.setText(currentPizza.getCrust().toString());

        boolean isBYO = type.equals("Build Your Own");
        availableToppingsListView.setVisible(isBYO);
        addToppingButton.setVisible(isBYO);
        removeToppingButton.setVisible(isBYO);

        updateToppingsList();
        updateTotal();
        loadImage(type);
        pizzaInfoLabel.setText(currentPizza.getStyle() + " - " + currentPizza.getTypeName()
                + "\nCrust: " + currentPizza.getCrust());
    }

    /**
     * Refreshes the selected toppings list view.
     */
    private void updateToppingsList() {
        ObservableList<String> list = FXCollections.observableArrayList();
        if (currentPizza != null) {
            for (Topping t : currentPizza.getToppings()) {
                list.add(t.toString());
            }
        }
        selectedToppingsListView.setItems(list);
    }

    /**
     * Updates the running price label.
     */
    private void updateTotal() {
        if (currentPizza != null) {
            totalLabel.setText(String.format("$%.2f", currentPizza.price()));
        }
    }

    /**
     * Loads the image for the selected pizza type.
     * @param type the pizza type string
     */
    private void loadImage(String type) {
        try {
            String file;
            if (type.equals("Deluxe")) file = "images/deluxe.png";
            else if (type.equals("BBQ Chicken")) file = "images/bbqchicken.png";
            else if (type.equals("Meatzza")) file = "images/meatzza.png";
            else file = "images/buildyourown.png";

            Image img = new Image(Main.class.getResourceAsStream(file));
            pizzaImageView.setImage(img);
        } catch (Exception e) {
            pizzaImageView.setImage(null);
        }
    }

    /**
     * Adds the selected topping from the available list to the Build Your Own pizza.
     */
    @FXML
    private void onAddTopping() {
        Topping selected = availableToppingsListView.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("Select a topping from the list to add.");
            return;
        }
        if (currentPizza.getToppings().contains(selected)) {
            showAlert("That topping is already on your pizza.");
            return;
        }
        if (!currentPizza.addTopping(selected)) {
            showAlert("You can only add up to 5 toppings.");
            return;
        }
        updateToppingsList();
        updateTotal();
    }

    /**
     * Removes the selected topping from the Build Your Own pizza.
     */
    @FXML
    private void onRemoveTopping() {
        String selected = selectedToppingsListView.getSelectionModel().getSelectedItem();
        if (selected == null) {
            showAlert("Select a topping from your pizza to remove.");
            return;
        }
        for (Topping t : currentPizza.getToppings()) {
            if (t.toString().equals(selected)) {
                currentPizza.removeTopping(t);
                break;
            }
        }
        updateToppingsList();
        updateTotal();
    }

    /**
     * Adds the current pizza to the current order.
     */
    @FXML
    private void onAddToOrder() {
        if (currentPizza == null) {
            showAlert("Please select a pizza first.");
            return;
        }
        currentOrder.addPizza(currentPizza);
        showAlert(currentPizza.getStyle() + " " + currentPizza.getTypeName() + " added to order.");
        updatePizzaDisplay(); // reset for next pizza
    }

    /**
     * Opens the current order view.
     */
    @FXML
    private void onViewOrder() {
        if (currentOrder.getPizzas().isEmpty()) {
            showAlert("Your order is empty. Add a pizza first.");
            return;
        }
        try {
            Main.showOrderView(currentOrder);
        } catch (IOException e) {
            showAlert("Error opening order view.");
        }
    }

    /**
     * Opens the placed orders view.
     */
    @FXML
    private void onViewStoreOrders() {
        try {
            Main.showStoreOrdersView();
        } catch (IOException e) {
            showAlert("Error opening store orders view.");
        }
    }

    /**
     * Converts a size string to the Size enum.
     * @param s the size string
     * @return corresponding Size value
     */
    private Size getSize(String s) {
        if (s.equals("Small")) return Size.SMALL;
        if (s.equals("Medium")) return Size.MEDIUM;
        return Size.LARGE;
    }

    /**
     * Sets the current order (used when returning from order view).
     * @param order the order to continue building
     */
    public void setCurrentOrder(Order order) {
        this.currentOrder = order;
    }

    /**
     * Shows a simple information alert.
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
