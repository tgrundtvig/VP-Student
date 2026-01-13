package dk.viprogram.week07;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for the SearchEngine implementation.
 * Run these tests to verify your SearchEngine implementation.
 */
@DisplayName("SearchEngine")
class SearchEngineTest {

    private SearchEngine<String, Product> engine;
    private Map<String, Product> testProducts;

    @BeforeEach
    void setUp() {
        testProducts = new HashMap<>();
        testProducts.put("p1", new Product("p1", "Laptop", "Powerful gaming laptop", 99999, "Electronics"));
        testProducts.put("p2", new Product("p2", "Laptop Stand", "Ergonomic stand", 4999, "Accessories"));
        testProducts.put("p3", new Product("p3", "Mouse", "Wireless mouse", 2999, "Electronics"));
        testProducts.put("p4", new Product("p4", "Keyboard", "Mechanical keyboard", 7999, "Electronics"));
        testProducts.put("p5", new Product("p5", "Desk Lamp", "LED desk lamp", 3499, "Home"));

        engine = new SearchEngine<>(testProducts);
    }

    @Nested
    @DisplayName("Basic Operations")
    class BasicOperations {

        @Test
        @DisplayName("engine starts with initial items")
        void engineStartsWithInitialItems() {
            assertEquals(5, engine.size());
        }

        @Test
        @DisplayName("getAllItems returns all items with default strategies")
        void getAllItemsReturnsAll() {
            List<Product> all = engine.getAllItems();
            assertEquals(5, all.size());
        }

        @Test
        @DisplayName("addItem increases size")
        void addItemIncreasesSize() {
            engine.addItem("p6", new Product("p6", "Monitor", "4K Monitor", 29999, "Electronics"));
            assertEquals(6, engine.size());
        }

        @Test
        @DisplayName("removeItem decreases size")
        void removeItemDecreasesSize() {
            Optional<Product> removed = engine.removeItem("p1");
            assertTrue(removed.isPresent());
            assertEquals("Laptop", removed.get().name());
            assertEquals(4, engine.size());
        }

        @Test
        @DisplayName("removeItem returns empty for missing key")
        void removeItemReturnsEmptyForMissingKey() {
            Optional<Product> removed = engine.removeItem("nonexistent");
            assertTrue(removed.isEmpty());
        }
    }

    @Nested
    @DisplayName("Search Strategy")
    class SearchStrategyTests {

        @Test
        @DisplayName("search with name contains strategy")
        void searchWithNameContains() {
            engine.setSearchStrategy(ProductSearchStrategies.nameContains());

            List<Product> results = engine.search("Laptop");

            assertEquals(2, results.size());
            assertTrue(results.stream().anyMatch(p -> p.name().equals("Laptop")));
            assertTrue(results.stream().anyMatch(p -> p.name().equals("Laptop Stand")));
        }

        @Test
        @DisplayName("search with exact name match")
        void searchWithExactNameMatch() {
            engine.setSearchStrategy(ProductSearchStrategies.exactNameMatchIgnoreCase());

            List<Product> results = engine.search("laptop");

            assertEquals(1, results.size());
            assertEquals("Laptop", results.get(0).name());
        }

        @Test
        @DisplayName("search with name starts with")
        void searchWithNameStartsWith() {
            engine.setSearchStrategy(ProductSearchStrategies.nameStartsWith());

            List<Product> results = engine.search("Desk");

            assertEquals(1, results.size());
            assertEquals("Desk Lamp", results.get(0).name());
        }

        @Test
        @DisplayName("search with category strategy")
        void searchWithCategoryStrategy() {
            engine.setSearchStrategy(ProductSearchStrategies.inCategory());

            List<Product> results = engine.search("Electronics");

            assertEquals(3, results.size());
        }

        @Test
        @DisplayName("search returns empty list when no matches")
        void searchReturnsEmptyWhenNoMatches() {
            engine.setSearchStrategy(ProductSearchStrategies.exactNameMatch());

            List<Product> results = engine.search("NonexistentProduct");

            assertTrue(results.isEmpty());
        }
    }

    @Nested
    @DisplayName("Filter Strategy")
    class FilterStrategyTests {

        @Test
        @DisplayName("filter by max price")
        void filterByMaxPrice() {
            engine.setFilterStrategy(ProductFilterStrategies.maxPrice(5000));

            List<Product> results = engine.getAllItems();

            // Laptop Stand (4999), Mouse (2999), Desk Lamp (3499) are all <= 5000
            assertEquals(3, results.size());
            assertTrue(results.stream().allMatch(p -> p.price() <= 5000));
        }

        @Test
        @DisplayName("filter by price range")
        void filterByPriceRange() {
            engine.setFilterStrategy(ProductFilterStrategies.priceRange(3000, 8000));

            List<Product> results = engine.getAllItems();

            assertEquals(3, results.size());
            assertTrue(results.stream().allMatch(p -> p.price() >= 3000 && p.price() <= 8000));
        }

        @Test
        @DisplayName("filter by category")
        void filterByCategory() {
            engine.setFilterStrategy(ProductFilterStrategies.category("Electronics"));

            List<Product> results = engine.getAllItems();

            assertEquals(3, results.size());
            assertTrue(results.stream().allMatch(p -> p.category().equals("Electronics")));
        }

        @Test
        @DisplayName("combined filters with and")
        void combinedFiltersWithAnd() {
            FilterStrategy<Product> electronics = ProductFilterStrategies.category("Electronics");
            FilterStrategy<Product> cheap = ProductFilterStrategies.maxPrice(5000);

            engine.setFilterStrategy(electronics.and(cheap));

            List<Product> results = engine.getAllItems();

            assertEquals(1, results.size());
            assertEquals("Mouse", results.get(0).name());
        }
    }

    @Nested
    @DisplayName("Sorting Strategy")
    class SortingStrategyTests {

        @Test
        @DisplayName("sort by price ascending")
        void sortByPriceAscending() {
            engine.setSortingStrategy(Comparator.comparingInt(Product::price));

            List<Product> results = engine.getAllItems();

            assertEquals("Mouse", results.get(0).name());      // 2999
            assertEquals("Desk Lamp", results.get(1).name());  // 3499
        }

        @Test
        @DisplayName("sort by price descending")
        void sortByPriceDescending() {
            engine.setSortingStrategy(Comparator.comparingInt(Product::price).reversed());

            List<Product> results = engine.getAllItems();

            assertEquals("Laptop", results.get(0).name());  // 99999
        }

        @Test
        @DisplayName("sort by name alphabetically")
        void sortByNameAlphabetically() {
            engine.setSortingStrategy(Comparator.comparing(Product::name));

            List<Product> results = engine.getAllItems();

            assertEquals("Desk Lamp", results.get(0).name());
            assertEquals("Keyboard", results.get(1).name());
            assertEquals("Laptop", results.get(2).name());
        }
    }

    @Nested
    @DisplayName("Combined Strategies")
    class CombinedStrategies {

        @Test
        @DisplayName("search + filter + sort together")
        void searchFilterSortTogether() {
            // Search for items with "a" in name
            engine.setSearchStrategy(ProductSearchStrategies.nameContains());
            // Filter to Electronics only
            engine.setFilterStrategy(ProductFilterStrategies.category("Electronics"));
            // Sort by price ascending
            engine.setSortingStrategy(Comparator.comparingInt(Product::price));

            List<Product> results = engine.search("a");

            // "Laptop" and "Keyboard" contain "a" and are Electronics
            assertEquals(2, results.size());
            assertEquals("Keyboard", results.get(0).name());  // 7999, lower price
            assertEquals("Laptop", results.get(1).name());    // 99999, higher price
        }

        @Test
        @DisplayName("filter affects search results")
        void filterAffectsSearchResults() {
            engine.setSearchStrategy(ProductSearchStrategies.nameContains());
            engine.setFilterStrategy(ProductFilterStrategies.maxPrice(5000));

            List<Product> results = engine.search("Laptop");

            // "Laptop Stand" contains "Laptop" and is under 5000
            // "Laptop" contains "Laptop" but is over 5000
            assertEquals(1, results.size());
            assertEquals("Laptop Stand", results.get(0).name());
        }
    }

    @Nested
    @DisplayName("Edge Cases")
    class EdgeCases {

        @Test
        @DisplayName("search with empty query")
        void searchWithEmptyQuery() {
            engine.setSearchStrategy(ProductSearchStrategies.nameContains());

            List<Product> results = engine.search("");

            // Empty string is contained in all strings
            assertEquals(5, results.size());
        }

        @Test
        @DisplayName("empty engine returns empty results")
        void emptyEngineReturnsEmptyResults() {
            SearchEngine<String, Product> emptyEngine = new SearchEngine<>(new HashMap<>());

            List<Product> results = emptyEngine.getAllItems();

            assertTrue(results.isEmpty());
        }
    }
}
