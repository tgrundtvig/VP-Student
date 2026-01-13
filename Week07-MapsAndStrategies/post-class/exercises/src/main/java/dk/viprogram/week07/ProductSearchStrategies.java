package dk.viprogram.week07;

/**
 * Pre-built search strategies for Product objects.
 * Study these examples to understand how to create strategies.
 *
 * These are factory methods that create SearchStrategy instances.
 */
public final class ProductSearchStrategies {

    private ProductSearchStrategies() {
        // Utility class - prevent instantiation
    }

    /**
     * Matches products whose name equals the query exactly (case-sensitive).
     */
    public static SearchStrategy<Product> exactNameMatch() {
        return (product, query) -> product.name().equals(query);
    }

    /**
     * Matches products whose name equals the query (case-insensitive).
     */
    public static SearchStrategy<Product> exactNameMatchIgnoreCase() {
        return (product, query) -> product.name().equalsIgnoreCase(query);
    }

    /**
     * Matches products whose name starts with the query (case-insensitive).
     */
    public static SearchStrategy<Product> nameStartsWith() {
        return (product, query) ->
                product.name().toLowerCase().startsWith(query.toLowerCase());
    }

    /**
     * Matches products whose name contains the query (case-insensitive).
     */
    public static SearchStrategy<Product> nameContains() {
        return (product, query) ->
                product.name().toLowerCase().contains(query.toLowerCase());
    }

    /**
     * Matches products whose name OR description contains the query (case-insensitive).
     */
    public static SearchStrategy<Product> nameOrDescriptionContains() {
        return (product, query) -> {
            String lowerQuery = query.toLowerCase();
            return product.name().toLowerCase().contains(lowerQuery)
                    || product.description().toLowerCase().contains(lowerQuery);
        };
    }

    /**
     * Matches products in the specified category (case-insensitive).
     */
    public static SearchStrategy<Product> inCategory() {
        return (product, query) -> product.category().equalsIgnoreCase(query);
    }

    /**
     * Matches all products (useful as a default or when combined with filters).
     */
    public static SearchStrategy<Product> matchAll() {
        return (product, query) -> true;
    }
}
