package rupizza;

/**
 * Factory interface for creating pizza objects.
 * Implemented by ChicagoPizza and NYPizza.
 */
public interface PizzaFactory {
    Pizza createDeluxe();
    Pizza createMeatzza();
    Pizza createBBQChicken();
    Pizza createBuildYourOwn();
}
