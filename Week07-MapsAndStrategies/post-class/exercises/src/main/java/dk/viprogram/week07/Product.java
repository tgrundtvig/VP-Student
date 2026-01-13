package dk.viprogram.week07;

/**
 * A product record for demonstrating search strategies.
 *
 * @param id          unique product identifier
 * @param name        product name
 * @param description product description
 * @param price       product price in cents (to avoid floating point issues)
 * @param category    product category
 */
public record Product(
        String id,
        String name,
        String description,
        int price,
        String category
) {
    /**
     * Returns the price formatted as a string with currency.
     */
    public String formattedPrice() {
        return String.format("$%d.%02d", price / 100, price % 100);
    }
}
