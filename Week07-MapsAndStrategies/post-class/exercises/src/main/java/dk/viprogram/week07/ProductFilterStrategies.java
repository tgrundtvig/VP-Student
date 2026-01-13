package dk.viprogram.week07;

/**
 * Pre-built filter strategies for Product objects.
 * Study these examples to understand how to create filter strategies.
 *
 * These are factory methods that create FilterStrategy instances.
 */
public final class ProductFilterStrategies {

    private ProductFilterStrategies() {
        // Utility class - prevent instantiation
    }

    /**
     * Accepts products with price at or below the maximum.
     *
     * @param maxPriceCents maximum price in cents
     */
    public static FilterStrategy<Product> maxPrice(int maxPriceCents) {
        return product -> product.price() <= maxPriceCents;
    }

    /**
     * Accepts products with price at or above the minimum.
     *
     * @param minPriceCents minimum price in cents
     */
    public static FilterStrategy<Product> minPrice(int minPriceCents) {
        return product -> product.price() >= minPriceCents;
    }

    /**
     * Accepts products within the price range (inclusive).
     *
     * @param minPriceCents minimum price in cents
     * @param maxPriceCents maximum price in cents
     */
    public static FilterStrategy<Product> priceRange(int minPriceCents, int maxPriceCents) {
        return minPrice(minPriceCents).and(maxPrice(maxPriceCents));
    }

    /**
     * Accepts products in the specified category.
     *
     * @param category the category to accept
     */
    public static FilterStrategy<Product> category(String category) {
        return product -> product.category().equalsIgnoreCase(category);
    }

    /**
     * Accepts products in any of the specified categories.
     *
     * @param categories the categories to accept
     */
    public static FilterStrategy<Product> categories(String... categories) {
        return product -> {
            for (String cat : categories) {
                if (product.category().equalsIgnoreCase(cat)) {
                    return true;
                }
            }
            return false;
        };
    }

    /**
     * Accepts products whose name contains the specified text.
     *
     * @param text the text to search for
     */
    public static FilterStrategy<Product> nameContains(String text) {
        return product -> product.name().toLowerCase().contains(text.toLowerCase());
    }

    /**
     * Accepts all products.
     */
    public static FilterStrategy<Product> acceptAll() {
        return FilterStrategy.acceptAll();
    }
}
