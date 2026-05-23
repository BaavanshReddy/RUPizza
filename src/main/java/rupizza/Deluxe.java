package rupizza;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Deluxe specialty pizza.
 * Chicago crust: Deep Dish. NY crust: Brooklyn.
 * Toppings: Sausage, Pepperoni, Green Pepper, Onion, Mushroom.
 * Price: Small $18.99, Medium $20.99, Large $22.99.
 */
public class Deluxe extends Pizza {

    /**
     * Creates a Deluxe pizza with the given crust and preset toppings.
     * @param crust the crust for this pizza
     */
    public Deluxe(Crust crust) {
        setToppings(new ArrayList<>(Arrays.asList(
                Topping.SAUSAGE, Topping.PEPPERONI,
                Topping.GREEN_PEPPER, Topping.ONION, Topping.MUSHROOM
        )));
        setCrust(crust);
    }

    /**
     * Returns the price based on size.
     * @return price of the Deluxe pizza
     */
    @Override
    public double price() {
        if (getSize() == Size.SMALL) return 18.99;
        if (getSize() == Size.MEDIUM) return 20.99;
        return 22.99;
    }
}
