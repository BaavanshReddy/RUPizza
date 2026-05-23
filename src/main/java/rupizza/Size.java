package rupizza;

/**
 * Enum for pizza sizes.
 */
public enum Size {
    SMALL, MEDIUM, LARGE;

    /**
     * Returns a capitalized size name.
     * @return formatted size name
     */
    @Override
    public String toString() {
        return name().charAt(0) + name().substring(1).toLowerCase();
    }
}
