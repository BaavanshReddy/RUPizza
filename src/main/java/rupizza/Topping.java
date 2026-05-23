package rupizza;

/**
 * Enum for the available pizza toppings.
 * The store keeps at least 13 toppings available every day.
 */
public enum Topping {
    SAUSAGE, PEPPERONI, GREEN_PEPPER, ONION, MUSHROOM,
    BBQ_CHICKEN, PROVOLONE, CHEDDAR, BEEF, HAM,
    SHRIMP, SQUID, CRAB_MEAT, ANCHOVY, EXTRA_CHEESE;

    /**
     * Returns a readable version of the topping name.
     * @return formatted topping name
     */
    @Override
    public String toString() {
        String name = this.name().replace("_", " ");
        String[] words = name.split(" ");
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            sb.append(word.charAt(0)).append(word.substring(1).toLowerCase()).append(" ");
        }
        return sb.toString().trim();
    }
}
