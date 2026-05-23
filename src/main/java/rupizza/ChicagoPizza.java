package rupizza;

/**
 * Creates Chicago-style pizza objects.
 * Crusts: Deluxe=Deep Dish, BBQ Chicken=Pan, Meatzza=Stuffed, BYO=Pan.
 */
public class ChicagoPizza implements PizzaFactory {

    /**
     * Creates a Chicago-style Deluxe pizza.
     * @return Deluxe pizza with Deep Dish crust
     */
    @Override
    public Pizza createDeluxe() {
        return new Deluxe(Crust.DEEP_DISH);
    }

    /**
     * Creates a Chicago-style Meatzza pizza.
     * @return Meatzza pizza with Stuffed crust
     */
    @Override
    public Pizza createMeatzza() {
        return new Meatzza(Crust.STUFFED);
    }

    /**
     * Creates a Chicago-style BBQ Chicken pizza.
     * @return BBQChicken pizza with Pan crust
     */
    @Override
    public Pizza createBBQChicken() {
        return new BBQChicken(Crust.PAN);
    }

    /**
     * Creates a Chicago-style Build Your Own pizza.
     * @return BuildYourOwn pizza with Pan crust
     */
    @Override
    public Pizza createBuildYourOwn() {
        return new BuildYourOwn(Crust.PAN);
    }
}
