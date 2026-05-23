package rupizza;

/**
 * Creates New York-style pizza objects.
 * Crusts: Deluxe=Brooklyn, BBQ Chicken=Thin, Meatzza=Hand Tossed, BYO=Hand Tossed.
 */
public class NYPizza implements PizzaFactory {

    /**
     * Creates a New York-style Deluxe pizza.
     * @return Deluxe pizza with Brooklyn crust
     */
    @Override
    public Pizza createDeluxe() {
        return new Deluxe(Crust.BROOKLYN);
    }

    /**
     * Creates a New York-style Meatzza pizza.
     * @return Meatzza pizza with Hand Tossed crust
     */
    @Override
    public Pizza createMeatzza() {
        return new Meatzza(Crust.HAND_TOSSED);
    }

    /**
     * Creates a New York-style BBQ Chicken pizza.
     * @return BBQChicken pizza with Thin crust
     */
    @Override
    public Pizza createBBQChicken() {
        return new BBQChicken(Crust.THIN);
    }

    /**
     * Creates a New York-style Build Your Own pizza.
     * @return BuildYourOwn pizza with Hand Tossed crust
     */
    @Override
    public Pizza createBuildYourOwn() {
        return new BuildYourOwn(Crust.HAND_TOSSED);
    }
}
