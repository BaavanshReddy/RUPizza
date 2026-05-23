package rupizza;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * Meatzza specialty pizza.
 * Chicago crust: Stuffed. NY crust: Hand Tossed.
 * Toppings: Sausage, Pepperoni, Beef, Ham.
 * Price: Small $19.99, Medium $21.99, Large $23.99.
 */
public class Meatzza extends Pizza {

    /**
     * Creates a Meatzza pizza with the given crust and preset toppings.
     * @param crust the crust for this pizza
     */
    public Meatzza(Crust crust) {
        setToppings(new ArrayList<>(Arrays.asList(
                Topping.SAUSAGE, Topping.PEPPERONI,
                Topping.BEEF, Topping.HAM
        )));
        setCrust(crust);
    }

    /**
     * Returns the price based on size.
     * @return price of the Meatzza pizza
     */
    @Override
    public double price() {
        if (getSize() == Size.SMALL) return 19.99;
        if (getSize() == Size.MEDIUM) return 21.99;
        return 23.99;
    }
}
