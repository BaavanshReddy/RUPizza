package rupizza;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * BBQ Chicken specialty pizza.
 * Chicago crust: Pan. NY crust: Thin.
 * Toppings: BBQ Chicken, Green Pepper, Provolone, Cheddar.
 * Price: Small $16.99, Medium $18.99, Large $20.99.
 */
public class BBQChicken extends Pizza {

    /**
     * Creates a BBQ Chicken pizza with the given crust and preset toppings.
     * @param crust the crust for this pizza
     */
    public BBQChicken(Crust crust) {
        setToppings(new ArrayList<>(Arrays.asList(
                Topping.BBQ_CHICKEN, Topping.GREEN_PEPPER,
                Topping.PROVOLONE, Topping.CHEDDAR
        )));
        setCrust(crust);
    }

    /**
     * Returns the price based on size.
     * @return price of the BBQ Chicken pizza
     */
    @Override
    public double price() {
        if (getSize() == Size.SMALL) return 16.99;
        if (getSize() == Size.MEDIUM) return 18.99;
        return 20.99;
    }
}
