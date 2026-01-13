package dk.viprogram.week08;

/**
 * A product record for the search application.
 */
public record Product(
        String id,
        String name,
        String description,
        int priceCents,
        String category
) {
    /**
     * Returns the price formatted as currency.
     */
    public String formattedPrice() {
        return String.format("$%d.%02d", priceCents / 100, priceCents % 100);
    }

    @Override
    public String toString() {
        return String.format("%s - %s (%s)", name, formattedPrice(), category);
    }
}
