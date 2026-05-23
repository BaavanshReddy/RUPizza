package rupizza;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit test class for the BuildYourOwn pizza class.
 * Tests the price() method using Black-Box testing.
 * Covers all three sizes and multiple topping counts.
 */
public class BuildYourOwnTest {

    private static final double DELTA = 0.001;
    private static final double TOPPING_PRICE = 1.69;

    private Pizza chicagoBYO;
    private Pizza nyBYO;

    /**
     * Creates fresh pizza objects before each test.
     */
    @BeforeEach
    public void setUp() {
        PizzaFactory chicagoFactory = new ChicagoPizza();
        PizzaFactory nyFactory = new NYPizza();
        chicagoBYO = chicagoFactory.createBuildYourOwn();
        nyBYO = nyFactory.createBuildYourOwn();
    }

    /**
     * Small pizza, no toppings. Expected: $10.99.
     */
    @Test
    public void testSmallNoToppings() {
        chicagoBYO.setSize(Size.SMALL);
        assertEquals(10.99, chicagoBYO.price(), DELTA);
    }

    /**
     * Small pizza, 1 topping. Expected: $10.99 + $1.69 = $12.68.
     */
    @Test
    public void testSmallOneTopping() {
        chicagoBYO.setSize(Size.SMALL);
        chicagoBYO.addTopping(Topping.PEPPERONI);
        assertEquals(10.99 + TOPPING_PRICE, chicagoBYO.price(), DELTA);
    }

    /**
     * Small pizza, 5 toppings (max). Expected: $10.99 + 5 * $1.69 = $19.44.
     */
    @Test
    public void testSmallFiveToppings() {
        chicagoBYO.setSize(Size.SMALL);
        chicagoBYO.addTopping(Topping.SAUSAGE);
        chicagoBYO.addTopping(Topping.PEPPERONI);
        chicagoBYO.addTopping(Topping.MUSHROOM);
        chicagoBYO.addTopping(Topping.ONION);
        chicagoBYO.addTopping(Topping.GREEN_PEPPER);
        assertEquals(10.99 + 5 * TOPPING_PRICE, chicagoBYO.price(), DELTA);
    }

    /**
     * Medium pizza, no toppings. Expected: $12.99.
     */
    @Test
    public void testMediumNoToppings() {
        nyBYO.setSize(Size.MEDIUM);
        assertEquals(12.99, nyBYO.price(), DELTA);
    }

    /**
     * Medium pizza, 3 toppings. Expected: $12.99 + 3 * $1.69 = $18.06.
     */
    @Test
    public void testMediumThreeToppings() {
        nyBYO.setSize(Size.MEDIUM);
        nyBYO.addTopping(Topping.HAM);
        nyBYO.addTopping(Topping.BEEF);
        nyBYO.addTopping(Topping.CHEDDAR);
        assertEquals(12.99 + 3 * TOPPING_PRICE, nyBYO.price(), DELTA);
    }

    /**
     * Large pizza, no toppings. Expected: $14.99.
     */
    @Test
    public void testLargeNoToppings() {
        nyBYO.setSize(Size.LARGE);
        assertEquals(14.99, nyBYO.price(), DELTA);
    }

    /**
     * Large pizza, 5 toppings (max). Expected: $14.99 + 5 * $1.69 = $23.44.
     */
    @Test
    public void testLargeFiveToppings() {
        nyBYO.setSize(Size.LARGE);
        nyBYO.addTopping(Topping.BBQ_CHICKEN);
        nyBYO.addTopping(Topping.PROVOLONE);
        nyBYO.addTopping(Topping.SHRIMP);
        nyBYO.addTopping(Topping.SQUID);
        nyBYO.addTopping(Topping.CRAB_MEAT);
        assertEquals(14.99 + 5 * TOPPING_PRICE, nyBYO.price(), DELTA);
    }

    /**
     * Trying to add a 6th topping should return false and not change the price.
     */
    @Test
    public void testSixthToppingRejected() {
        chicagoBYO.setSize(Size.MEDIUM);
        chicagoBYO.addTopping(Topping.SAUSAGE);
        chicagoBYO.addTopping(Topping.PEPPERONI);
        chicagoBYO.addTopping(Topping.MUSHROOM);
        chicagoBYO.addTopping(Topping.ONION);
        chicagoBYO.addTopping(Topping.GREEN_PEPPER);
        boolean result = chicagoBYO.addTopping(Topping.HAM);
        assertFalse(result);
        assertEquals(5, chicagoBYO.getToppings().size());
        assertEquals(12.99 + 5 * TOPPING_PRICE, chicagoBYO.price(), DELTA);
    }
}
