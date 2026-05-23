package rupizza;

/**
 * Enum for the different crust types offered at RU Pizza.
 * Chicago-style uses Deep Dish, Pan, and Stuffed.
 * New York-style uses Brooklyn, Thin, and Hand Tossed.
 */
public enum Crust {
    DEEP_DISH, PAN, STUFFED, BROOKLYN, THIN, HAND_TOSSED;

    /**
     * Returns a readable crust name.
     * @return formatted crust name
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
