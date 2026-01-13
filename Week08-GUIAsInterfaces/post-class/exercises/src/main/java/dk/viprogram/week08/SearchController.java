package dk.viprogram.week08;

import java.util.List;

/**
 * The Controller for a search application.
 *
 * Coordinates the Model (data/logic) and View (display/input).
 * Uses the SearchView INTERFACE, not a concrete implementation.
 */
public class SearchController {

    private final SearchModel model;
    private final SearchView view;

    /**
     * Creates a controller with the given model and view.
     */
    public SearchController(SearchModel model, SearchView view) {
        this.model = model;
        this.view = view;

        // Wire up the view's search handler to this controller
        view.setSearchHandler(this::handleSearch);
    }

    /**
     * Initializes the view with all products.
     */
    public void initialize() {
        view.displayMessage("Welcome! Search for products or browse all items.");
        showAllProducts();
    }

    /**
     * Handles a search request from the view.
     *
     * @param query the search query
     */
    public void handleSearch(String query) {
        view.showLoading(true);

        try {
            List<Product> results = model.search(query);

            if (results.isEmpty()) {
                view.displayNoResults();
            } else {
                view.displayResultCount(results.size());
                view.displayResults(results);
            }
        } catch (Exception e) {
            view.displayError("Search failed: " + e.getMessage());
        } finally {
            view.showLoading(false);
        }
    }

    /**
     * Shows all products without filtering.
     */
    public void showAllProducts() {
        List<Product> all = model.getAllProducts();
        view.displayResultCount(all.size());
        view.displayResults(all);
    }
}
