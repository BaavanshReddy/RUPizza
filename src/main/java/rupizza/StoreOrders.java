package rupizza;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Keeps track of all placed orders for the store.
 * Uses a singleton so the same list is shared across all views.
 */
public class StoreOrders {
    private ArrayList<Order> orders;

    private static StoreOrders instance;

    /**
     * Private constructor — use getInstance() instead.
     */
    private StoreOrders() {
        orders = new ArrayList<>();
    }

    /**
     * Returns the single instance of StoreOrders.
     * @return the StoreOrders instance
     */
    public static StoreOrders getInstance() {
        if (instance == null) {
            instance = new StoreOrders();
        }
        return instance;
    }

    /**
     * Adds a placed order to the store.
     * @param order the order to add
     */
    public void addOrder(Order order) {
        orders.add(order);
    }

    /**
     * Cancels an order and removes it from the list.
     * @param order the order to cancel
     * @return true if removed, false if not found
     */
    public boolean cancelOrder(Order order) {
        return orders.remove(order);
    }

    /**
     * Returns the list of all placed orders.
     * @return ArrayList of orders
     */
    public ArrayList<Order> getOrders() {
        return orders;
    }

    /**
     * Exports all placed orders to a text file.
     * @param filePath path to the output file
     * @throws IOException if writing fails
     */
    public void exportOrders(String filePath) throws IOException {
        PrintWriter writer = new PrintWriter(new FileWriter(filePath));
        writer.println("RU Pizza - Placed Orders");
        writer.println("========================");
        writer.println();
        for (Order order : orders) {
            writer.println("Order #" + order.getNumber());
            for (Pizza p : order.getPizzas()) {
                writer.println("  " + p.getStyle() + " " + p.getTypeName());
                writer.println("    Crust: " + p.getCrust());
                writer.println("    Size: " + p.getSize());
                writer.println("    Toppings: " + p.getToppings());
                writer.printf("    Subtotal: $%.2f%n", p.price());
            }
            writer.printf("  Subtotal: $%.2f%n", order.subtotal());
            writer.printf("  Tax (6.625%%): $%.2f%n", order.tax());
            writer.printf("  Order Total: $%.2f%n", order.total());
            writer.println("----------------------------");
        }
        writer.close();
    }
}
