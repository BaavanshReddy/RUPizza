package rupizza;

import java.util.ArrayList;

/**
 * Represents a customer order. Each order has a unique auto-generated number.
 */
public class Order {
    private int number;
    private ArrayList<Pizza> pizzas;

    private static int nextOrderNumber = 1;
    public static final double TAX_RATE = 0.06625;

    /**
     * Creates a new order with a unique number and an empty pizza list.
     */
    public Order() {
        this.number = nextOrderNumber++;
        this.pizzas = new ArrayList<>();
    }

    /**
     * Gets the order number.
     * @return the order number
     */
    public int getNumber() {
        return number;
    }

    /**
     * Gets the list of pizzas in this order.
     * @return ArrayList of pizzas
     */
    public ArrayList<Pizza> getPizzas() {
        return pizzas;
    }

    /**
     * Adds a pizza to the order.
     * @param pizza the pizza to add
     */
    public void addPizza(Pizza pizza) {
        pizzas.add(pizza);
    }

    /**
     * Removes a specific pizza from the order.
     * @param pizza the pizza to remove
     * @return true if removed successfully
     */
    public boolean removePizza(Pizza pizza) {
        return pizzas.remove(pizza);
    }

    /**
     * Removes all pizzas from the order.
     */
    public void clearPizzas() {
        pizzas.clear();
    }

    /**
     * Calculates the subtotal (sum of all pizza prices).
     * @return the subtotal
     */
    public double subtotal() {
        double total = 0;
        for (Pizza p : pizzas) {
            total += p.price();
        }
        return total;
    }

    /**
     * Calculates the sales tax at 6.625%.
     * @return the tax amount
     */
    public double tax() {
        return subtotal() * TAX_RATE;
    }

    /**
     * Calculates the order total (subtotal + tax).
     * @return the total amount due
     */
    public double total() {
        return subtotal() + tax();
    }

    /**
     * Returns a string listing the order number, each pizza, and the total.
     * @return formatted order string
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Order #").append(number).append("\n");
        for (Pizza p : pizzas) {
            sb.append("  ").append(p.toString()).append("\n");
        }
        sb.append(String.format("  Order Total: $%.2f", total()));
        return sb.toString();
    }
}
