package rupizza;

import java.util.ArrayList;

/**
 * Abstract class representing a pizza.
 * All pizza types extend this class.
 * The price() method is abstract and implemented by each subclass.
 */
public abstract class Pizza {
    private ArrayList<Topping> toppings;
    private Crust crust;
    private Size size;

    /**
     * Returns the price of the pizza based on size and toppings.
     * @return the price as a double
     */
    public abstract double price();

    /**
     * Gets the list of toppings on the pizza.
     * @return ArrayList of toppings
     */
    public ArrayList<Topping> getToppings() {
        return toppings;
    }

    /**
     * Sets the toppings list.
     * @param toppings the toppings to set
     */
    public void setToppings(ArrayList<Topping> toppings) {
        this.toppings = toppings;
    }

    /**
     * Gets the crust type.
     * @return the crust
     */
    public Crust getCrust() {
        return crust;
    }

    /**
     * Sets the crust type.
     * @param crust the crust to set
     */
    public void setCrust(Crust crust) {
        this.crust = crust;
    }

    /**
     * Gets the pizza size.
     * @return the size
     */
    public Size getSize() {
        return size;
    }

    /**
     * Sets the pizza size.
     * @param size the size to set
     */
    public void setSize(Size size) {
        this.size = size;
    }

    /**
     * Adds a topping to the pizza. Maximum of 5 toppings allowed.
     * @param topping the topping to add
     * @return true if added, false if already at 5 toppings
     */
    public boolean addTopping(Topping topping) {
        if (toppings.size() < 5) {
            toppings.add(topping);
            return true;
        }
        return false;
    }

    /**
     * Removes a topping from the pizza.
     * @param topping the topping to remove
     * @return true if removed, false if not found
     */
    public boolean removeTopping(Topping topping) {
        return toppings.remove(topping);
    }

    /**
     * Determines the pizza style based on the crust type.
     * @return "Chicago Style" or "New York Style"
     */
    public String getStyle() {
        if (crust == Crust.DEEP_DISH || crust == Crust.PAN || crust == Crust.STUFFED) {
            return "Chicago Style";
        }
        return "New York Style";
    }

    /**
     * Gets the pizza type name in a readable format.
     * @return the pizza type name
     */
    public String getTypeName() {
        String name = getClass().getSimpleName();
        if (name.equals("BBQChicken")) return "BBQ Chicken";
        if (name.equals("BuildYourOwn")) return "Build Your Own";
        return name;
    }

    /**
     * Returns a string with the pizza's style, type, crust, size, toppings, and price.
     * @return formatted pizza description
     */
    @Override
    public String toString() {
        return getStyle() + " " + getTypeName()
                + " | Crust: " + crust
                + " | Size: " + size
                + " | Toppings: " + toppings
                + String.format(" | $%.2f", price());
    }
}
