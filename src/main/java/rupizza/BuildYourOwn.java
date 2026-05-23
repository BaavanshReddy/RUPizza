package rupizza;

import java.util.ArrayList;

/**
 * Build Your Own pizza — customers can pick up to 5 toppings.
 * Chicago crust: Pan. NY crust: Hand Tossed.
 * Base price: Small $10.99, Medium $12.99, Large $14.99.
 * Each topping adds $1.69 to the price.
 */
public class BuildYourOwn extends Pizza {

    private static final double TOPPING_PRICE = 1.69;

    /**
     * Creates a Build Your Own pizza with the given crust and no toppings.
     * @param crust the crust for this pizza
     */
    public BuildYourOwn(Crust crust) {
        setToppings(new ArrayList<>());
        setCrust(crust);
    }

    /**
     * Calculates the price: base price + $1.69 per topping.
     * @return total price of the pizza
     */
    @Override
    public double price() {
        double base = 0;
        if (getSize() == Size.SMALL) base = 10.99;
        else if (getSize() == Size.MEDIUM) base = 12.99;
        else base = 14.99;
        return base + getToppings().size() * TOPPING_PRICE;
    }
}
