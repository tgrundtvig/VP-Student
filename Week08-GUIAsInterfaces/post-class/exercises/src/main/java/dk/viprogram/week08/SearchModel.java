package dk.viprogram.week08;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The Model for a search application.
 *
 * Contains the data (products) and search logic.
 * Knows nothing about the UI.
 */
public class SearchModel {

    private final Map<String, Product> products;

    /**
     * Creates a new search model with sample products.
     */
    public SearchModel() {
        this.products = new HashMap<>();
        loadSampleProducts();
    }

    /**
     * Creates a search model with the given products.
     */
    public SearchModel(Map<String, Product> products) {
        this.products = new HashMap<>(products);
    }

    private void loadSampleProducts() {
        addProduct(new Product("p1", "Laptop", "Powerful gaming laptop with RGB", 99999, "Electronics"));
        addProduct(new Product("p2", "Laptop Stand", "Ergonomic aluminum stand", 4999, "Accessories"));
        addProduct(new Product("p3", "Wireless Mouse", "Bluetooth mouse with long battery", 2999, "Electronics"));
        addProduct(new Product("p4", "Mechanical Keyboard", "Cherry MX switches", 12999, "Electronics"));
        addProduct(new Product("p5", "USB-C Hub", "7-in-1 hub with HDMI", 5999, "Accessories"));
        addProduct(new Product("p6", "Monitor", "27-inch 4K display", 39999, "Electronics"));
        addProduct(new Product("p7", "Desk Lamp", "LED lamp with adjustable brightness", 3499, "Home"));
        addProduct(new Product("p8", "Webcam", "1080p webcam with microphone", 7999, "Electronics"));
        addProduct(new Product("p9", "Headphones", "Noise-cancelling over-ear", 24999, "Electronics"));
        addProduct(new Product("p10", "Mouse Pad", "Large gaming mouse pad", 1999, "Accessories"));
    }

    /**
     * Adds a product to the catalog.
     */
    public void addProduct(Product product) {
        products.put(product.id(), product);
    }

    /**
     * Searches for products matching the query.
     * Searches in name and description (case-insensitive).
     *
     * @param query the search query
     * @return list of matching products
     */
    public List<Product> search(String query) {
        if (query == null || query.isBlank()) {
            return new ArrayList<>(products.values());
        }

        String lowerQuery = query.toLowerCase();
        List<Product> results = new ArrayList<>();

        for (Product product : products.values()) {
            if (product.name().toLowerCase().contains(lowerQuery) ||
                    product.description().toLowerCase().contains(lowerQuery)) {
                results.add(product);
            }
        }

        return results;
    }

    /**
     * Returns all products.
     */
    public List<Product> getAllProducts() {
        return new ArrayList<>(products.values());
    }

    /**
     * Returns the number of products in the catalog.
     */
    public int getProductCount() {
        return products.size();
    }
}
